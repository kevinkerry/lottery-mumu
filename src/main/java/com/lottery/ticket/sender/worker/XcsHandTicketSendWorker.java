package com.lottery.ticket.sender.worker;

import com.lottery.common.contains.QueueName;
import com.lottery.common.contains.lottery.HighFrequencyLottery;
import com.lottery.common.contains.lottery.LotteryType;
import com.lottery.common.contains.lottery.TicketStatus;
import com.lottery.common.contains.ticket.TicketSendResult;
import com.lottery.common.contains.ticket.TicketSendResultStatus;
import com.lottery.core.domain.ticket.Ticket;
import com.lottery.core.domain.ticket.TicketBatch;
import com.lottery.core.service.TicketService;
import com.lottery.core.service.queue.QueueMessageSendService;
import com.lottery.ticket.sender.worker.vender.XCSVenerTicketSendWorder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by fengqinyun on 15/7/16.
 */
@Service
public class XcsHandTicketSendWorker  {
    @Autowired
   protected XCSVenerTicketSendWorder xcsVenerTicketSendWorder;
    protected final Logger logger= LoggerFactory.getLogger(getClass());
    @Autowired
    protected TicketService ticketService;

    @Autowired
    protected QueueMessageSendService queueMessageSendService;
    public  void send(TicketBatch ticketBatch,LotteryType lotteryType){
// 取批次中状态为未送票的票

        try {
            List<Ticket>  ticketList = ticketService.getByBatchIdAndStatus(ticketBatch.getId(), TicketStatus.UNSENT.getValue());
            List<TicketSendResult>  ticketresult=xcsVenerTicketSendWorder.executeSend(ticketBatch, ticketList, lotteryType);

            if (ticketresult!=null){
                for (TicketSendResult ticketsend:ticketresult){
                    Ticket ticket=ticketService.getTicket(ticketsend.getId());
                    if (ticket==null){
                        logger.error("票id:{}不存在",ticketsend.getId());
                        continue;
                    }
                     if (ticketsend.getStatus()== TicketSendResultStatus.printed){
                         ticket.setPrintTime(new Date());
                         ticket.setPeilv(ticketsend.getPeiLv());
                         ticket.setStatus(TicketStatus.PRINT_SUCCESS.value);
                         ticket.setTerminalId(ticketBatch.getTerminalId());
                         ticketService.updateTicketSuccessInfo(ticket,true);
                         sendJms(ticket);
                     }else {
                         ticket.setStatus(TicketStatus.UNALLOTTED.value);
                         ticket.setTerminalId(0l);
                         ticketService.update(ticket);
                     }

                }
            }
        } catch (Exception e) {
            logger.error("查找批次{}中的票出错", ticketBatch.getId());

        }



    }
    protected void sendJms(Ticket ticket){

        try {
            String queuename= QueueName.betChercher.value;
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
}
