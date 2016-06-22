package com.lottery.retrymodel.prize.error;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.lottery.common.PageBean;
import com.lottery.common.contains.QueueName;
import com.lottery.common.contains.lottery.LotteryType;
import com.lottery.common.contains.lottery.OrderStatus;
import com.lottery.common.contains.lottery.TicketStatus;
import com.lottery.core.domain.ticket.LotteryOrder;
import com.lottery.core.domain.ticket.Ticket;
import com.lottery.core.service.BeidanService;
import com.lottery.core.service.JingcaiService;
import com.lottery.core.service.LotteryOrderService;
import com.lottery.core.service.TicketService;
import com.lottery.core.service.queue.QueueMessageSendService;
import com.lottery.retrymodel.ApiRetryCallback;

public class ModifyRaceTimeCallback extends ApiRetryCallback<Object> {

	private Integer lotteryType;
	private LotteryOrderService lotteryOrderService;
	private String phase;
	private String matchNum;
	private boolean flag = false;
	private QueueMessageSendService queueMessageSendService;

	private JingcaiService jingcaiService;
	private BeidanService beidanService;

	private TicketService ticketService;

	@Override
	protected LotteryOrder execute() throws Exception {
		if (lotteryType == null ||ticketService==null|| jingcaiService == null || beidanService == null || lotteryOrderService == null || StringUtils.isBlank(matchNum) || queueMessageSendService == null) {
			logger.error("参数不全");
			return null;
		}

		try {
			for (int lottype : LotteryType.getLotteryTypeList(lotteryType)) {
                logger.error("开始执行关于彩种:{},场次:{}的订单赛程修改",lottype,matchNum);
                List<Integer> orderStatusList = new ArrayList<Integer>();
                orderStatusList.add(OrderStatus.PRINTING.value);
                orderStatusList.add(OrderStatus.NOT_SPLIT.value);
                orderStatusList.add(OrderStatus.PRINT_WAITING.value);

                PageBean<LotteryOrder> pageBean = new PageBean<LotteryOrder>();
                int max = 15;
                pageBean.setMaxResult(max);
                int page = 1;
                while (true) {
                    pageBean.setPageIndex(page);
                    List<LotteryOrder> list = null;
                    try {
                        list = lotteryOrderService.getByLotteryPhaseMatchNumAndStatus(lottype, "", matchNum, orderStatusList, null, pageBean);
                    } catch (Exception e) {
                        logger.error("查询出错", e);
                        break;
                    }
                    if (list != null && list.size() > 0) {
                        orderprocess(list,lottype);
                        if (list.size() < max) {
                            logger.info("读取到的方案列表不足一页，已读取结束");
                            break;
                        }
                    } else {
                        break;
                    }
                    page++;
                }


			}
		} catch (Exception e) {
			logger.error("修改赛程出错", e);
		}

		return null;
	}

    protected  void orderprocess(List<LotteryOrder> orderList,int lotteryType){
        if (orderList != null && orderList.size() > 0) {
            logger.error("彩种:{},需要执行订单个数为:{}",lotteryType,orderList.size());
            if (flag) {
                endMatch(orderList);
            } else {
                modify(orderList, lotteryType);
            }
        }else {
            logger.error("彩种:{}没有需要修改的票",lotteryType);
        }
    }

	private void modify(List<LotteryOrder> list, int lottypeType) {
		for (LotteryOrder lotteryOrder : list) {

			logger.error("修改订单:{}的时间", lotteryOrder.getId());
			Date dealDate = null;
			String minteamid = "0";
			String maxteamid = "0";
			LotteryType lotteryType = LotteryType.get(lottypeType);
			String betcode = lotteryOrder.getContent();
			if (LotteryType.getJclq().contains(lotteryType) || LotteryType.getJczq().contains(lotteryType)) {
				minteamid = jingcaiService.getMinTeamid(betcode, lotteryType,lotteryOrder.getPrizeOptimize());
				maxteamid = jingcaiService.getMaxTeamid(betcode, lotteryType,lotteryOrder.getPrizeOptimize());
				dealDate = jingcaiService.getDealTeime(minteamid, lotteryType);
			}
			if (LotteryType.getDc().contains(lotteryType)) {
				String phaseNo = lotteryOrder.getPhase();
				minteamid = beidanService.getMinTeamid(betcode, lotteryType, phaseNo,lotteryOrder.getPrizeOptimize());
				maxteamid = beidanService.getMaxTeamid(betcode, lotteryType, phaseNo,lotteryOrder.getPrizeOptimize());
				dealDate = beidanService.getDealTeime(minteamid, lotteryType, phaseNo);
			}
			Long minid = Long.valueOf(minteamid);
			Long maxid = Long.valueOf(maxteamid);
			if (minid > 0 && maxid > 0 && dealDate != null) {
				lotteryOrder.setFirstMatchNum(minid);
				lotteryOrder.setLastMatchNum(maxid);
				lotteryOrder.setDeadline(dealDate);
				lotteryOrderService.modifyLotteryOrderTime(lotteryOrder);
			} else {
				logger.error("参数存在为空的情况,minid={},maxid={},dealDate={}", new Object[] { minid, maxid, dealDate });
			}

		}
	}

	private void endMatch(List<LotteryOrder> list) {
		for (LotteryOrder lotteryOrder : list) {
            logger.error("订单:{}撤单操作",lotteryOrder.getId());
			String orderId = lotteryOrder.getId();
			List<Ticket> ticketList = ticketService.getByorderId(orderId);

			for (Ticket ticket : ticketList) {
				if (ticket.getStatus() != TicketStatus.PRINT_SUCCESS.value&&ticket.getContent().contains(matchNum)&&ticket.getStatus() != TicketStatus.CANCELLED.value) {
					ticketService.updateTicketStatus(TicketStatus.CANCELLED.value, ticket.getId());
				}
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("orderId", orderId);
			try {
				queueMessageSendService.sendMessage(QueueName.betChercher, map);
			} catch (Exception e) {

			}
		}
	}

	public void setLotteryType(int lotteryType) {
		this.lotteryType = lotteryType;
	}

	public void setLotteryOrderService(LotteryOrderService lotteryOrderService) {
		this.lotteryOrderService = lotteryOrderService;
	}

	public void setPhase(String phase) {
		this.phase = phase;
	}

	public void setMatchNum(String matchNum) {
		this.matchNum = matchNum;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public void setQueueMessageSendService(QueueMessageSendService queueMessageSendService) {
		this.queueMessageSendService = queueMessageSendService;
	}

	public void setLotteryType(Integer lotteryType) {
		this.lotteryType = lotteryType;
	}

	public void setJingcaiService(JingcaiService jingcaiService) {
		this.jingcaiService = jingcaiService;
	}

	public void setBeidanService(BeidanService beidanService) {
		this.beidanService = beidanService;
	}

	public void setTicketService(TicketService ticketService) {
		this.ticketService = ticketService;
	}

}
