package com.lottery.ticket.sender.processor;

import com.lottery.common.contains.ticket.TicketBatchSendResultProcessType;
import com.lottery.common.contains.ticket.TicketSendResult;
import com.lottery.common.contains.ticket.TicketSendResultStatus;
import com.lottery.common.util.CoreDateUtils;
import com.lottery.core.domain.ticket.Ticket;
import com.lottery.core.domain.ticket.TicketBatch;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
public class CommonFailedTicketSendVenderProcessor extends AbstractTicketSendVenderProcessor{

	@Override
	public TicketBatchSendResultProcessType process(TicketSendResult ticketSendResult, Ticket ticket, TicketBatch ticketBatch) {
		Date sendtime=ticketSendResult.getSendTime();
		String dateStr=null;
		if(sendtime!=null){
			dateStr=CoreDateUtils.formatDate(sendtime, DATE_STR);
		}
		String message = String.format("票(%s)在终端(%s)送票失败,我方状态是(%s),对方返回状态是(%s),错误描述:(%s)",
                ticket.getId(), ticketBatch.getTerminalId(),ticketSendResult.getStatus().toString(),ticketSendResult.getStatusCode(),ticketSendResult.getMessage());
        logger.error(message);
        
        logger.error("票[{}]在[终端{}],id={}),送票的详情,送票时间:[{}],内容:{},返回:{}",ticket.getId(),ticketSendResult.getTerminalType().getName(),ticketBatch.getTerminalId(),dateStr,ticketSendResult.getSendMessage(),ticketSendResult.getResponseMessage());

		if (ticketSendResult.getFailureType() != null){
			
		}
		return TicketBatchSendResultProcessType.reallot;
	}

	@Override
	protected void init() {
		sendResultMap.put(TicketSendResultStatus.failed, this);
		sendResultMap.put(TicketSendResultStatus.sys_busy, this);
		sendResultMap.put(TicketSendResultStatus.http_502, this);
		sendResultMap.put(TicketSendResultStatus.http_504, this);
		sendResultMap.put(TicketSendResultStatus.unkown, this);
	}

	
}
