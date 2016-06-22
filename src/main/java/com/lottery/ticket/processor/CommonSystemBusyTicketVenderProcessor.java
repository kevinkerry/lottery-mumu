package com.lottery.ticket.processor;

import org.springframework.stereotype.Service;

import com.lottery.common.contains.lottery.TicketVenderStatus;
import com.lottery.common.contains.ticket.TicketVender;
import com.lottery.core.domain.ticket.Ticket;

@Service
public class CommonSystemBusyTicketVenderProcessor extends AbstractTicketVenderProcessor {

	@Override
	protected void execute(Ticket ticket, TicketVender ticketVender) {
		// 对于系统繁忙的票不做任何处理
	}

	@Override
	protected void init() {
		ticketVenderProcessorMap.put(TicketVenderStatus.system_busy, this);
	}
}
