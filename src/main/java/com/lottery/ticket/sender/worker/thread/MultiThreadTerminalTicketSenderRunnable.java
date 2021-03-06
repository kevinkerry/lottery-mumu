/**
 * 
 */
package com.lottery.ticket.sender.worker.thread;

import com.lottery.common.contains.TicketBatchStatus;
import com.lottery.common.contains.lottery.LotteryType;
import com.lottery.common.thread.ThreadContainer;
import com.lottery.core.domain.ticket.TicketBatch;
import com.lottery.log.LotteryLog;


import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import java.util.concurrent.LinkedTransferQueue;

/**
 * 每个终端一个送票线程的多线程送票实现
 * 每个线程在该终端初次送票时动态创建
 * @author fengqinyun
 *
 */
public class MultiThreadTerminalTicketSenderRunnable extends CommonTicketSenderRunnable {
	private long instanceInterval = 1500l;//间隔时间(毫秒)
	private Map<Long, MultiThreadInstanceTicketSenderRunnable> terminalRunnableMap = new ConcurrentHashMap<Long, MultiThreadInstanceTicketSenderRunnable>();

	/**
	 * 初始化送票子线程并启动
	 * 如果指定终端号的子线程已存在，直接返回，否则创建
	 * @throws Exception
	 */
	protected MultiThreadInstanceTicketSenderRunnable initInstance(Long terminalId) {
		if (terminalRunnableMap.containsKey(terminalId)) {
			return terminalRunnableMap.get(terminalId);
		}

        synchronized (this) {
            if (terminalRunnableMap.containsKey(terminalId)) {
                return terminalRunnableMap.get(terminalId);
            }
            MultiThreadInstanceTicketSenderRunnable runnable =new MultiThreadInstanceTicketSenderRunnable();
            runnable.setLotteryList(this.getLotteryList());
            runnable.setTicketSendWorker(ticketSendWorker);
            runnable.setTerminalSelector(this.getTerminalSelector());
            runnable.setTicketBatchService(this.getTicketBatchService());
            runnable.setInterval(this.getInstanceInterval());
            ThreadContainer threadContainer = new ThreadContainer(runnable, "终端(" + terminalId.longValue() + ")送票子线程");
            threadContainer.setBeforeRunWaitTime(100);
            threadContainer.start();
            terminalRunnableMap.put(terminalId, runnable);
            return runnable;
        }
    }
	
	/**
	 * 销毁送票子线程
	 * @throws Exception
	 */
	protected void destroyInstance()  throws Exception {
		if (terminalRunnableMap != null) {
			Set<Long> terminalIdSet = terminalRunnableMap.keySet();
			for (Long terminalId : terminalIdSet) {
				MultiThreadInstanceTicketSenderRunnable runnable = terminalRunnableMap.get(terminalId);
				runnable.executeStop();
			}
			terminalRunnableMap.clear();
		}
	}
	
	protected void dispatch(List<TicketBatch> ticketBatchList) {


			for (TicketBatch ticketBatch : ticketBatchList) {
				Long terminalId = ticketBatch.getTerminalId();
				MultiThreadInstanceTicketSenderRunnable runnable = this.initInstance(terminalId);
				runnable.addQueue(ticketBatch);
				runnable.executeNotify();
			}
	}
	
	protected void beforeDispatch(List<TicketBatch> ticketBatchList) throws Exception {
		for (TicketBatch ticketBatch : ticketBatchList) {
			ticketBatch.setStatus(TicketBatchStatus.SEND_QUEUED.value);
			ticketBatchService.updateTicketBatchStatus(ticketBatch);
		}
	}

	@Override
	public void execute() {
		// 回收上次队列中未处理完的批次
		for (LotteryType lotteryType : this.getLotteryList()) {
			// 获取当前期待送票的批次
			List<TicketBatch> ticketBatchList = null;
			try {
				ticketBatchList = ticketBatchService.findForSendRecycle(lotteryType.value);
			} catch (Exception ex) {
				logger.error("查询({})要送票的批次出错!",lotteryType.getName(), ex);
			}
			if (ticketBatchList == null || ticketBatchList.isEmpty()) {

				continue;
			}

            ticketBatchList = this.filterTerminalSingletonDispatch(ticketBatchList);
			
			// 分派到子线程
			dispatch(ticketBatchList);
		}
		
		while (running) {
			for (LotteryType lotteryType : this.getLotteryList()) {


				if (this.isDuringGlobalSendForbidPeriod(lotteryType)) {
					LotteryLog.getLotterWarnLog().info("此彩种处于全局停售期，不做送票处理, {}", lotteryType);
					continue;
				}

				List<Long> allExcludeTerminalIdList = new ArrayList<Long>();
				
				// 获取此彩种暂停送票的终端ID列表
                List<Long> pausedTerminalConfigList = this.getTerminalSelector().getPausedTerminalIdList(lotteryType);

                if (pausedTerminalConfigList != null) {
                    allExcludeTerminalIdList.addAll(pausedTerminalConfigList);
                }

				

				// 获取当前期待送票的批次
				List<TicketBatch> ticketBatchList = null;
				try {
					ticketBatchList = this.getTicketBatchService().findForSend(lotteryType, allExcludeTerminalIdList, this.getMaxProcessBatchCount());
				} catch (Exception ex) {
					logger.error("查询({})要送票的批次出错!",lotteryType.getName(), ex);
				}
				if (ticketBatchList == null || ticketBatchList.isEmpty()) {
					logger.info("彩种({})没有未送票的批次", lotteryType.getName());
					continue;
				}
				
				try {
					ticketBatchList = this.filterSendPaused(ticketBatchList);

					ticketBatchList = this.filterTerminalSingletonDispatch(ticketBatchList);
					
					// 处理成中间态
					beforeDispatch(ticketBatchList);
					
					// 分派到子线程
					dispatch(ticketBatchList);
				} catch (Exception e) {
					logger.error("彩种{}, 批次送票处理失败", lotteryType.getName());
					logger.error(e.getMessage(),e);
				}
			}
			
			synchronized (this) {
				try {
					wait(this.getInterval());
				} catch (InterruptedException e) {
					logger.error(e.getMessage(), e);
				}
			}
		}
		
		logger.info("线程退出，销毁送票子线程");
		try {
			destroyInstance();
		} catch (Exception e) {
			logger.error("送票子线程销毁失败", e);
		}
	}

	public long getInstanceInterval() {
		return instanceInterval;
	}

	public void setInstanceInterval(long instanceInterval) {
		this.instanceInterval = instanceInterval;
	}

	
}
