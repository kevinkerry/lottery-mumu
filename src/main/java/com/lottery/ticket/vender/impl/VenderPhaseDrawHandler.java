package com.lottery.ticket.vender.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lottery.common.contains.lottery.LotteryType;
import com.lottery.common.contains.ticket.LogicMachineStatus;
import com.lottery.core.domain.LotteryPhase;
import com.lottery.core.domain.ticket.LotteryLogicMachine;
import com.lottery.core.handler.PrizeHandler;
import com.lottery.core.service.LotteryLogicMachineService;
import com.lottery.core.service.LotteryPhaseService;
import com.lottery.ticket.phase.thread.IPhaseHandler;
@Service
public class VenderPhaseDrawHandler {
	@Resource(name = "allPhaseHandlerBinder")
	private Map<LotteryType, IPhaseHandler> allPhaseHandlerBinder;
	@Autowired
	protected LotteryPhaseService lotteryPhaseService;
	@Autowired
	protected LotteryLogicMachineService lotteryLogicMachineService;
	@Autowired
	protected PrizeHandler prizeHandler;
	protected final Logger logger=LoggerFactory.getLogger(getClass().getName());
/**
 * 修改新期
 * @param lotteryType 彩种
 * @param phase 新期
 * @param startTime 开始时间
 * @param endTime 官方结束时间
 * @param endTicketTime 平台结束时间
 * */
	
	public List<LotteryPhase> updatePhase(int lotteryType,String phase,Date startTime,Date endTime,Date endTicketTime){
		try{
			LotteryPhase lotteryPhase=lotteryPhaseService.getCurrent(lotteryType);
			if(lotteryPhase==null||!phase.equals(lotteryPhase.getPhase())){//如果当前期不是，
				lotteryPhaseService.closeCurrent(lotteryType);
			}
			
			//更新新期
			lotteryPhaseService.savePhaseEndSaleTime(lotteryType, phase, startTime, endTime,endTicketTime);
			IPhaseHandler phaseHandler = allPhaseHandlerBinder.get(LotteryType.getLotteryType(lotteryType));
			if (phaseHandler != null) {
				phaseHandler.executeReload();
			}
			
			LotteryPhase enablePhase=lotteryPhaseService.getByTypeAndPhase(lotteryType, phase);
			if(enablePhase==null){
				logger.error("彩种:{}，彩期:{}为空",lotteryType,phase);
				return null;
			}
			
			
			
			List<LotteryPhase> phaseList=new ArrayList<LotteryPhase>();
			phaseList.add(enablePhase);
			if(enablePhase.getPhase().endsWith("001")){
				return phaseList;
			}
			long off=endTime.getTime()-enablePhase.getEndSaleTime().getTime();
			List<LotteryPhase> nextPhase=lotteryPhaseService.getNextPhase(lotteryType, phase, 10);
			for(LotteryPhase next:nextPhase){
				if(next.getPhase().endsWith("001")){
					break;
				}
				addOffsetTimeToPhase(next, off);
				phaseList.add(next);
				lotteryPhaseService.update(next);
			}
			return phaseList;
		}catch(Exception e){
			logger.error("修改新期出错",e);
			return null;
		}
		
	}
	/**
	 * 修改逻辑机
	 * @param terminalType 终端类型
	 * @param lotteryType 彩种
	 * */
	public void magicMachine(int terminalType,int lotteryType,String phase){	
		List<LotteryLogicMachine> machineList=lotteryLogicMachineService.getByTerminalAndLotteryType(terminalType, lotteryType);
		if(machineList!=null&&machineList.size()>0){
			for(LotteryLogicMachine lotteryLogicMachine:machineList){
				if(lotteryLogicMachine.getPhase().equals(phase)){
					continue;
				}
				lotteryLogicMachine.setCurrentId(1l);
				lotteryLogicMachine.setPhase(phase);
				lotteryLogicMachine.setSwitchTime(new Date());
				if(lotteryLogicMachine.getStatus()!=LogicMachineStatus.not_use.value)
				lotteryLogicMachine.setStatus(LogicMachineStatus.useing.value);
				lotteryLogicMachineService.update(lotteryLogicMachine);
			}
		}
	}
	/**
	 * 修改开奖号码
	 * @param lotteryType 彩种
	 * @param phase 期号
	 * @param wincode 开奖号码
	 * @throws Exception 
	 * */
	public void saveWinCode(int lotteryType,String phase,String wincode) throws Exception{
		try{
			prizeHandler.prizeOpen(lotteryType, phase, wincode);
		}catch(Exception e){
			logger.error("进行彩种:{},期号:{}的开奖出错",lotteryType,phase,e);
		}
	}
	/**
	 * 计算时间偏移量
	 * */
	protected void addOffsetTimeToPhase(LotteryPhase phase, long offset){
		phase.setUpdateTime(new Date());
		Calendar startSaleCalendar = Calendar.getInstance();
		startSaleCalendar.setTime(phase.getStartSaleTime());
		startSaleCalendar.add(Calendar.MILLISECOND, (int)offset);
		phase.setStartSaleTime(startSaleCalendar.getTime());
		
		Calendar endSaleCalendar = Calendar.getInstance();
		endSaleCalendar.setTime(phase.getEndSaleTime());
		endSaleCalendar.add(Calendar.MILLISECOND, (int)offset);
		phase.setEndSaleTime(endSaleCalendar.getTime());
		
		Calendar endTicketCalendar = Calendar.getInstance();
		endTicketCalendar.setTime(phase.getEndTicketTime());
		endTicketCalendar.add(Calendar.MILLISECOND, (int)offset);
		phase.setEndTicketTime(endTicketCalendar.getTime());
		
		Calendar drawCalendar = Calendar.getInstance();
		drawCalendar.setTime(phase.getDrawTime());
		drawCalendar.add(Calendar.MILLISECOND, (int)offset);
		phase.setDrawTime(drawCalendar.getTime());
	}
	
}
