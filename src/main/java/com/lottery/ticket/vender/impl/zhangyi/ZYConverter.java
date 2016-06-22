package com.lottery.ticket.vender.impl.zhangyi;

import com.lottery.common.contains.lottery.LotteryType;
import com.lottery.common.contains.lottery.PlayType;
import com.lottery.common.contains.lottery.TerminalType;
import com.lottery.core.domain.ticket.Ticket;
import com.lottery.ticket.IPhaseConverter;
import com.lottery.ticket.ITicketContentConverter;
import com.lottery.ticket.IVenderTicketConverter;
import com.lottery.ticket.vender.AbstractVenderConverter;
import org.springframework.stereotype.Service;

import java.util.Map;
@Service
public class ZYConverter extends AbstractVenderConverter {

	


	@Override
	protected Map<LotteryType, String> getLotteryTypeMap() {
		
		return ZYLotteryDef.lotteryTypeMap;
	}

	@Override
	public Map<Integer, String> getPlayTypeMap() {
		return ZYLotteryDef.playTypeMap;
	}

	
	@Override
	public LotteryType getLotteryMap(String lotteryId) {
		return findLotteryType(lotteryId);
	}

	@Override
	protected Map<LotteryType, IPhaseConverter> getPhaseConverterMap() {
		return ZYLotteryDef.phaseConverterMap;
	}


	@Override
	protected Map<LotteryType, IPhaseConverter> getPhaseReverseConverterMap() {
		return ZYLotteryDef.phaseConverterMap;
	}

	@Override
	protected Map<PlayType, ITicketContentConverter> getTicketContentConverterMap() {
		return null;
	}

	@Override
	public String convertContent(Ticket ticket) {
		PlayType playType=PlayType.get(ticket.getPlayType());
		IVenderTicketConverter converter=null;
		if(ZYLotteryDef.playTypeToAdvanceConverterMap.containsKey(playType)){
			converter= ZYLotteryDef.playTypeToAdvanceConverterMap.get(playType);
		}
		if(converter!=null){
			return converter.convert(ticket);
		}
		return null;
	}
	/**
	 * 赔率
	 * @param odds
	 * @return
	 */
	@Override
	protected String venderSpConvert(Ticket tickt, String venderSp) {
		Integer lotteryType=tickt.getLotteryType();
			StringBuilder strBuilder=new StringBuilder();
			String []oddss=venderSp.split("\\;");
			int i=0;
			for(String odd:oddss){
				if (LotteryType.getJczq().contains(LotteryType.get(lotteryType))&&lotteryType!=LotteryType.JCZQ_HHGG.getValue()){
					strBuilder.append("20").append(odd.replace("-",""));
				}else if(lotteryType==LotteryType.JCZQ_HHGG.getValue()){
					strBuilder.append("20").append(odd.replace("-","").split("\\^")[1].split("\\(")[0]).append("*")
					.append(ZYLotteryDef.toLotteryMap.get(odd.split("\\^")[0])).append("(").append(odd.split("\\(")[1]);
				}else if(LotteryType.getJclq().contains(LotteryType.get(lotteryType))&&lotteryType!=LotteryType.JCLQ_HHGG.getValue()){
					if(lotteryType==LotteryType.JCLQ_SF.getValue()){
						strBuilder.append(odd.replace("-",""));
					}else if(lotteryType==LotteryType.JCLQ_SFC.getValue()){ 
						strBuilder.append(odd.replace("-","").split("\\(")[0]).append("(").append(odd.replace("-","").split("\\(")[1].split("\\)")[0].replace("6", "06")
						.replace("5", "05").replace("4", "04").replace("3", "03").replace("2", "02").replace("1","01").replace("7", "11").replace("8", "12")
						.replace("9","13").replace("10","14").replace("11","15").replace("12", "16")).append(")");
					}else{
						String []cons=odd.replace("-","").replace(")", "").split("\\(")[1].split("\\,");
						strBuilder.append(odd.replace("-","").split("\\(")[0]).append("(");
						int j=0;
						strBuilder.append(cons[0].split("\\_")[1]).append(":");
						for(String con:cons){
							strBuilder.append(con.split("\\_")[0]).append("_").append(con.split("\\_")[2]);
							if(j!=cons.length-1){
								strBuilder.append(",");
							}
							j++;
						}
						strBuilder.append(")");
					}
				}else if(lotteryType==LotteryType.JCLQ_HHGG.getValue()){
					int  lottery=ZYLotteryDef.toLotteryMap.get(odd.split("\\^")[0]);
					if(lottery==LotteryType.JCLQ_DXF.getValue()||lottery==LotteryType.JCLQ_RFSF.getValue()){
						strBuilder.append(odd.replace("-","").split("\\^")[1].split("\\(")[0]).append("*")
						.append(ZYLotteryDef.toLotteryMap.get(odd.split("\\^")[0])).append("(");
						String []cons=odd.replace(")", "").split("\\(")[1].split("\\,");
						int j=0;
						for(String con:cons){
							strBuilder.append(con.split("\\_")[0]).append("_").append(con.split("\\_")[2]);
							if(j!=cons.length-1){
								strBuilder.append(",");
							}
							j++;
						}
						strBuilder.append(")");
					}else if(lottery==LotteryType.JCLQ_SFC.getValue()){
						strBuilder.append(odd.replace("-","").split("\\^")[1].split("\\(")[0]).append("*")
						.append(ZYLotteryDef.toLotteryMap.get(odd.split("\\^")[0])).append("(").append(odd.split("\\(")[1].split("\\_")[0].replace("6", "06")
						.replace("5", "05").replace("4", "04").replace("3", "03").replace("2", "02").replace("1","01").replace("7", "11").replace("8", "12")
						.replace("9","13").replace("10","14").replace("11","15").replace("12", "16"))
						.append("_").append(odd.split("\\(")[1].split("\\_")[1]);
					}else{
						strBuilder.append(odd.replace("-","").split("\\^")[1].split("\\(")[0]).append("*")
						.append(ZYLotteryDef.toLotteryMap.get(odd.split("\\^")[0])).append("(").append(odd.split("\\(")[1]);
					}
					
				}
				
				if(i!=oddss.length-1){
					strBuilder.append("|");
				}
				i++;
			}
			return strBuilder.toString();
	}
	@Override
	protected Map<String, LotteryType> getReverseLotteryTypeMap() {
		return null;
	}

	@Override
	protected void init() {
		venderConverterBinder.put(TerminalType.T_ZY, this);
	}

}
