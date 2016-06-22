package com.lottery.ticket.sender.processor;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.lottery.common.contains.QueueName;
import com.lottery.common.contains.lottery.HighFrequencyLottery;
import com.lottery.common.contains.lottery.LotteryType;
import com.lottery.common.contains.lottery.TicketStatus;
import com.lottery.common.contains.ticket.TicketBatchSendResultProcessType;
import com.lottery.common.contains.ticket.TicketSendResult;
import com.lottery.common.contains.ticket.TicketSendResultStatus;
import com.lottery.core.domain.ticket.Ticket;
import com.lottery.core.domain.ticket.TicketBatch;
import com.lottery.core.service.TicketService;
import com.lottery.core.service.queue.QueueMessageSendService;

public abstract class AbstractTicketSendVenderProcessor implements ITicketSendVenderProcessor {
	protected final transient Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	protected static String DATE_STR = "yyyy-MM-dd HH:mm:ss.SSS";
	@Autowired
	protected TicketService ticketService;
	@Autowired
	protected QueueMessageSendService queueMessageSendService;
	@Resource(name = "sendResultMap")
	protected Map<TicketSendResultStatus, ITicketSendVenderProcessor> sendResultMap;

	protected TicketBatchSendResultProcessType processSuccessful(TicketSendResult ticketSendResult, Ticket ticket, TicketBatch ticketBatch) {
		ticket.setStatus(TicketStatus.PRINTING.value);
		Date sendTime = ticketSendResult.getSendTime();
		if (sendTime == null) {
			sendTime = new Date();
		}
		ticket.setSendTime(sendTime);
		ticket.setTerminalId(ticketBatch.getTerminalId());
		if (ticketSendResult.getTerminalType() != null) {
			ticket.setTerminalType(ticketSendResult.getTerminalType().value);
		}
		ticketService.updateTicketPrintingInfo(ticket);
		return TicketBatchSendResultProcessType.success;
	}

	@PostConstruct
	abstract protected void init();

	protected void sendJms(Ticket ticket) {
		try {
			String queuename = QueueName.betChercher.value;
			LotteryType lotteryType = LotteryType.get(ticket.getLotteryType());
			if (HighFrequencyLottery.contains(lotteryType)) {
				queuename = QueueName.gaopinChercher.value;
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("orderId", ticket.getOrderId());
			this.queueMessageSendService.sendMessage(queuename, map);
		} catch (Exception e) {
			logger.error("发送检票消息出错", e);
		}
	}
}
