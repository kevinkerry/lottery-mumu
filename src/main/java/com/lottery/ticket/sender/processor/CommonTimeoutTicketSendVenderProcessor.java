package com.lottery.ticket.sender.processor;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.lottery.common.contains.lottery.HighFrequencyLottery;
import com.lottery.common.contains.lottery.LotteryType;
import com.lottery.common.contains.ticket.TicketBatchSendResultProcessType;
import com.lottery.common.contains.ticket.TicketSendResult;
import com.lottery.common.contains.ticket.TicketSendResultStatus;
import com.lottery.common.util.CoreDateUtils;
import com.lottery.core.domain.ticket.Ticket;
import com.lottery.core.domain.ticket.TicketBatch;
@Service
public class CommonTimeoutTicketSendVenderProcessor extends AbstractTicketSendVenderProcessor{
	  /**
     * 截止期最后一小时以内的超时才重分
     */
    private long reallotDeadlineForward = 1800000;//大盘半个小时
    private long highFrequencyUrgentProcessTime=180000;//高频3分钟
	@Override
	public TicketBatchSendResultProcessType process(TicketSendResult ticketSendResult, Ticket ticket, TicketBatch ticketBatch) {
		Date sendtime=ticketSendResult.getSendTime();
		String dateStr=null;
		if(sendtime!=null){
			dateStr=CoreDateUtils.formatDate(sendtime, DATE_STR);
		}
		String timemsg = String.format("票(%s)在终端(%s),id=(%s)送票超时,发送的时间是:[%s]内容是:(%s)",
                ticket.getId(), ticketSendResult.getTerminalType().name,ticketBatch.getTerminalId(),dateStr,ticketSendResult.getSendMessage());
		logger.error(timemsg);
		if (ticket.getTerminateTime() != null && ticket.getTerminateTime().getTime() - System.currentTimeMillis() > this.getReallotDeadlineForward(ticket.getLotteryType())) {
            String message = String.format("票(%s)在终端(%s)送票超时,  但还未进入重分截止期, 继续重试",
                    ticket.getId(), ticketBatch.getTerminalId());
            logger.error(message);
            return TicketBatchSendResultProcessType.retry;
        } else {
        	 String message = String.format("票(%s)在终端(%s)送票超时,进入截止期，重新分票",
                     ticket.getId(), ticketBatch.getTerminalId());
             logger.error(message);
           return TicketBatchSendResultProcessType.reallot;
        }
	}

	private long getReallotDeadlineForward(int lotteryType) {
		if (HighFrequencyLottery.contains(LotteryType.get(lotteryType))) {
			reallotDeadlineForward = highFrequencyUrgentProcessTime;
		}
		return reallotDeadlineForward;
	}
	@Override
	protected void init() {
		sendResultMap.put(TicketSendResultStatus.timeout, this);
		
	}
	
	

}
