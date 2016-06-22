package com.lottery.ticket.processor;

import com.lottery.common.contains.QueueName;
import com.lottery.common.contains.lottery.HighFrequencyLottery;
import com.lottery.common.contains.lottery.LotteryType;
import com.lottery.common.contains.lottery.TerminalType;
import com.lottery.common.contains.lottery.TicketVenderStatus;
import com.lottery.common.contains.ticket.TicketVender;
import com.lottery.core.domain.ticket.Ticket;
import com.lottery.core.handler.LotteryPhaseHandler;
import com.lottery.core.service.TicketBatchService;
import com.lottery.core.service.TicketService;
import com.lottery.core.service.TicketServiceCache;
import com.lottery.core.service.queue.QueueMessageSendService;
import com.lottery.ticket.IVenderConverter;
import com.lottery.ticket.allotter.ITicketAllotWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractTicketVenderProcessor implements ITicketVenderProcessor {

    protected final transient Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    @Autowired
    protected TicketService ticketService;
    @Autowired
    protected TicketServiceCache ticketServiceCache;
    @Autowired
    protected TicketBatchService ticketBatchService;
	@Resource(name="executorBinder")
	protected Map<LotteryType, ITicketAllotWorker> executorBinder;
   
    @Resource(name="venderConverterBinder")
    private Map<TerminalType, IVenderConverter> venderConverterBinder;
    
    @Resource(name="ticketVenderProcessorMap")
    protected Map<TicketVenderStatus,ITicketVenderProcessor> ticketVenderProcessorMap;
    @Autowired
    protected LotteryPhaseHandler phaseHandler;
  
    @Autowired
    protected QueueMessageSendService queueMessageSendService;
	protected long getTicketTimeout(Integer lotteryType) {
		
		return phaseHandler.getEndTicketTimeout(lotteryType);
	}
    protected IVenderConverter getVenderConverter(TerminalType terminalType) {
        return venderConverterBinder.get(terminalType);
    }

    protected ITicketAllotWorker getAllotExecutor(LotteryType lotteryType) {
		ITicketAllotWorker worker=this.executorBinder.get(lotteryType);
		if(worker==null){
			worker=this.executorBinder.get(LotteryType.ALL);
		}
		return worker;
	}

    public void process(Ticket ticket, TicketVender ticketVender){//往后好扩展
     this.execute(ticket, ticketVender);
    }

    protected abstract  void execute(Ticket ticket, TicketVender ticketVender);


    protected void sendJms(Ticket ticket){
    	
    	try {
    		String queuename=QueueName.betChercher.value;
    		LotteryType lotteryType=LotteryType.get(ticket.getLotteryType());
    		if(HighFrequencyLottery.contains(lotteryType)){
    			queuename=QueueName.gaopinChercher.value;
    		}
    		Map<String,Object> map=new HashMap<String,Object>();
    		map.put("orderId", ticket.getOrderId());
			this.queueMessageSendService.sendMessage(queuename, map);
		} catch (Exception e) {
		   logger.error("发送检票消息出错",e);
		}
    }
    @PostConstruct
    abstract protected void init();

  
}
