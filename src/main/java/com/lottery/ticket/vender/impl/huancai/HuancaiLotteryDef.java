package com.lottery.ticket.vender.impl.huancai;

import java.util.HashMap;
import java.util.Map;

import com.lottery.common.contains.lottery.LotteryType;
import com.lottery.common.contains.lottery.PlayType;
import com.lottery.core.domain.ticket.Ticket;
import com.lottery.ticket.IPhaseConverter;

import com.lottery.ticket.IVenderTicketConverter;

public class HuancaiLotteryDef {
	/** 彩种转换 */
	protected static Map<LotteryType, String> lotteryTypeMap = new HashMap<LotteryType, String>();
	/** 彩种转换 */
	public static Map<Integer,String> toLotteryTypeMap = new HashMap<Integer,String>();
	/** 彩期转换*/
	protected static Map<LotteryType, IPhaseConverter> phaseConverterMap = new HashMap<LotteryType, IPhaseConverter>();
	/** 玩法转换*/
	public static Map<Integer, String> playTypeMap = new HashMap<Integer, String>();
	/** 票内容转换 */
	protected static Map<PlayType, IVenderTicketConverter> playTypeToAdvanceConverterMap = new HashMap<PlayType, IVenderTicketConverter>();



	static{
		
		/**
		 * 彩期转换
		 * */
		//默认的期号转换
		IPhaseConverter defaultPhaseConverter=new IPhaseConverter() {
			@Override
			public String convert(String phase) {
				return phase;
			}
		};
		phaseConverterMap.put(LotteryType.JSK3, defaultPhaseConverter);
		phaseConverterMap.put(LotteryType.SD_11X5, defaultPhaseConverter);
		phaseConverterMap.put(LotteryType.GD_11X5, defaultPhaseConverter);
		//彩种转换
		
		lotteryTypeMap.put(LotteryType.JSK3, "013");
		lotteryTypeMap.put(LotteryType.SD_11X5, "115");
		lotteryTypeMap.put(LotteryType.GD_11X5, "116");
		
		
		//玩法转换
		//江苏快三
		playTypeMap.put(PlayType.jiangsu_ertong_dan.getValue(), "601");
		playTypeMap.put(PlayType.jiangsu_ertong_fu.getValue(), "501");
		playTypeMap.put(PlayType.jiangsu_ertong_zuhe.getValue(), "502");
		playTypeMap.put(PlayType.jiangsu_erbutong_dan.getValue(), "701");
//		playTypeMap.put(PlayType.jiangsu_erbutong_zuhe.getValue(), "702");
//		playTypeMap.put(PlayType.jiangsu_erbutong_dt.getValue(), "01");
		playTypeMap.put(PlayType.jiangsu_santong_dan.getValue(), "301");
		playTypeMap.put(PlayType.jiangsu_santong_tongxuan.getValue(), "201");
		playTypeMap.put(PlayType.jiangsu_sanbutong_dan.getValue(), "801");
//		playTypeMap.put(PlayType.jiangsu_sanbutong_zuhe.getValue(), "01");
//		playTypeMap.put(PlayType.jiangsu_sanbutong_dt.getValue(), "01");
		playTypeMap.put(PlayType.jiangsu_sanlian_tongxuan.getValue(), "401");
		playTypeMap.put(PlayType.jiangsu_hezhi.getValue(), "101");
		
		
		playTypeMap.put(PlayType.gd11c5_sr2.getValue(),"201");
		playTypeMap.put(PlayType.gd11c5_sr3.getValue(),"301");
		playTypeMap.put(PlayType.gd11c5_sr4.getValue(),"401");
		playTypeMap.put(PlayType.gd11c5_sr5.getValue(),"501");
		playTypeMap.put(PlayType.gd11c5_sr6.getValue(),"601");
		playTypeMap.put(PlayType.gd11c5_sr7.getValue(),"701");
		playTypeMap.put(PlayType.gd11c5_sr8.getValue(),"801");
		playTypeMap.put(PlayType.gd11c5_sq1.getValue(),"101");
	    playTypeMap.put(PlayType.gd11c5_sq2.getValue(),"901");
		playTypeMap.put(PlayType.gd11c5_sq3.getValue(),"111");
		playTypeMap.put(PlayType.gd11c5_sz2.getValue(),"903");
		playTypeMap.put(PlayType.gd11c5_sz3.getValue(),"113");
		
		playTypeMap.put(PlayType.gd11c5_mr2.getValue(),"201");
		playTypeMap.put(PlayType.gd11c5_mr3.getValue(),"301");
		playTypeMap.put(PlayType.gd11c5_mr4.getValue(),"401");
		playTypeMap.put(PlayType.gd11c5_mr5.getValue(),"501");
		playTypeMap.put(PlayType.gd11c5_mr6.getValue(),"601");
		playTypeMap.put(PlayType.gd11c5_mr7.getValue(),"701");
//		playTypeMap.put(PlayType.gd11c5_mr8.getValue(),"");
		playTypeMap.put(PlayType.gd11c5_mq1.getValue(),"102");
		playTypeMap.put(PlayType.gd11c5_mq2.getValue(),"902");
		playTypeMap.put(PlayType.gd11c5_mq3.getValue(),"112");
		playTypeMap.put(PlayType.gd11c5_mz2.getValue(),"903");//前二组选单式和复试玩法？
		playTypeMap.put(PlayType.gd11c5_mz3.getValue(),"113");
		
		playTypeMap.put(PlayType.gd11c5_dr2.getValue(),"202");
		playTypeMap.put(PlayType.gd11c5_dr3.getValue(),"302");
		playTypeMap.put(PlayType.gd11c5_dr4.getValue(),"402");
		playTypeMap.put(PlayType.gd11c5_dr5.getValue(),"502");
		playTypeMap.put(PlayType.gd11c5_dr6.getValue(),"602");
		playTypeMap.put(PlayType.gd11c5_dr7.getValue(),"702");
//		playTypeMap.put(PlayType.gd11c5_dr8.getValue(),"802");
		playTypeMap.put(PlayType.gd11c5_dz2.getValue(),"904");
		playTypeMap.put(PlayType.gd11c5_dz3.getValue(),"114");
		
	
		// 单式
		IVenderTicketConverter erth_ds = new IVenderTicketConverter() {
			@Override
			public String convert(Ticket ticket) {
				String contentStr = ticket.getContent().split("-")[1];
				StringBuffer ticketContent = new StringBuffer();
				String[] oneCode = contentStr.split("\\^");
				for (int j=0; j < oneCode.length; j++) {
					String content = oneCode[j];
					String []cons=content.split("\\,");
				    if(cons[0].equals(cons[1])){
					   ticketContent.append(cons[0]).append("#").append(cons[2]);
					}else{
                       ticketContent.append(cons[2]).append("#").append(cons[0]);
					}
					if(j!=oneCode.length-1){
						ticketContent.append(";");
					}
				}
				return ticketContent.toString();
			}
		};
		IVenderTicketConverter erth_fs = new IVenderTicketConverter() {
			@Override
			public String convert(Ticket ticket) {
				String contentStr = ticket.getContent().split("-")[1];
				String oneCode = contentStr.replace("^","");
				return oneCode;
			}
		};
		IVenderTicketConverter erbt_ds = new IVenderTicketConverter() {
			@Override
			public String convert(Ticket ticket) {
				String contentStr = ticket.getContent().split("-")[1];
				StringBuffer ticketContent = new StringBuffer();
				String[] oneCode = contentStr.split("\\^");
				int j=0;
				for (String code:oneCode) {
					ticketContent.append(code);
					if(j!=oneCode.length-1){
						ticketContent.append(";");
					}
					j++;
				}
				return ticketContent.toString();
			}
		};
		IVenderTicketConverter sth_ds = new IVenderTicketConverter() {
			@Override
			public String convert(Ticket ticket) {
				String contentStr = ticket.getContent().split("-")[1];
				StringBuffer ticketContent = new StringBuffer();
				String[] oneCode = contentStr.split("\\^");
				for (int j=0; j < oneCode.length; j++) {
					String content = oneCode[j];
					String []cons=content.split("\\,");
					ticketContent.append(cons[0]);
					if(j!=oneCode.length-1){
						ticketContent.append(";");
					}
				}
				return ticketContent.toString();
			}
		};
		IVenderTicketConverter k3hz = new IVenderTicketConverter() {
			@Override
			public String convert(Ticket ticket) {
				String contentStr = ticket.getContent().split("-")[1];
				String oneCode = contentStr.replace("^","");
				if(Integer.parseInt(oneCode)<=9){
					oneCode="0"+oneCode;
				}
				return oneCode;
			}
		};

		
		// 单式
		IVenderTicketConverter sdrx_qxds = new IVenderTicketConverter() {
			@Override
			public String convert(Ticket ticket) {
				String contentStr = ticket.getContent().split("-")[1];
				String oneCode = contentStr.replace("^", "");
				oneCode=oneCode.replace(",","#");
				return oneCode;
			}
		};
		IVenderTicketConverter qerfs = new IVenderTicketConverter() {
			@Override
			public String convert(Ticket ticket) {
				String contentStr = ticket.getContent().split("-")[1];
				String oneCode = contentStr.replace("^", "");
				oneCode=oneCode.replace(";","#");
				return oneCode;
			}
		};
		
		IVenderTicketConverter gddt = new IVenderTicketConverter() {
			@Override
			public String convert(Ticket ticket) {
				String contentStr = ticket.getContent().split("-")[1];
				String oneCode = contentStr.replace("^", "");
				oneCode=oneCode.replace("#","@");
				return oneCode;
			}
		};
		
		
			playTypeToAdvanceConverterMap.put(PlayType.jiangsu_ertong_dan, erth_ds);
			playTypeToAdvanceConverterMap.put(PlayType.jiangsu_ertong_fu, erth_fs);
			playTypeToAdvanceConverterMap.put(PlayType.jiangsu_erbutong_dan, erbt_ds);
//			playTypeToAdvanceConverterMap.put(PlayType.jiangsu_erbutong_dt, qlc_ds);
			playTypeToAdvanceConverterMap.put(PlayType.jiangsu_santong_dan, sth_ds);
			playTypeToAdvanceConverterMap.put(PlayType.jiangsu_santong_tongxuan, erth_fs);
			playTypeToAdvanceConverterMap.put(PlayType.jiangsu_sanbutong_dan, erbt_ds);
//			playTypeToAdvanceConverterMap.put(PlayType.jiangsu_sanbutong_zuhe, qlc_ds);
//			playTypeToAdvanceConverterMap.put(PlayType.jiangsu_sanbutong_dt, qlc_ds);
			playTypeToAdvanceConverterMap.put(PlayType.jiangsu_sanlian_tongxuan, erth_fs);
			playTypeToAdvanceConverterMap.put(PlayType.jiangsu_hezhi, k3hz);
			
			
			

			playTypeToAdvanceConverterMap.put(PlayType.gd11c5_sr2, erth_fs);
			playTypeToAdvanceConverterMap.put(PlayType.gd11c5_sr3, erth_fs);
			playTypeToAdvanceConverterMap.put(PlayType.gd11c5_sr4, erth_fs);
			playTypeToAdvanceConverterMap.put(PlayType.gd11c5_sr5, erth_fs);
			playTypeToAdvanceConverterMap.put(PlayType.gd11c5_sr6, erth_fs);
			playTypeToAdvanceConverterMap.put(PlayType.gd11c5_sr7, erth_fs);
			playTypeToAdvanceConverterMap.put(PlayType.gd11c5_sr8, erth_fs);
			playTypeToAdvanceConverterMap.put(PlayType.gd11c5_sq1, erth_fs);
			playTypeToAdvanceConverterMap.put(PlayType.gd11c5_sq2, sdrx_qxds);
			playTypeToAdvanceConverterMap.put(PlayType.gd11c5_sq3, sdrx_qxds);
			playTypeToAdvanceConverterMap.put(PlayType.gd11c5_sz2, erth_fs);
			playTypeToAdvanceConverterMap.put(PlayType.gd11c5_sz3, erth_fs);
			
			playTypeToAdvanceConverterMap.put(PlayType.gd11c5_mr2, erth_fs);
			playTypeToAdvanceConverterMap.put(PlayType.gd11c5_mr3, erth_fs);
			playTypeToAdvanceConverterMap.put(PlayType.gd11c5_mr4, erth_fs);
			playTypeToAdvanceConverterMap.put(PlayType.gd11c5_mr5, erth_fs);
			playTypeToAdvanceConverterMap.put(PlayType.gd11c5_mr6, erth_fs);
			playTypeToAdvanceConverterMap.put(PlayType.gd11c5_mr7, erth_fs);
//			playTypeToAdvanceConverterMap.put(PlayType.gd11c5_mr8, k3hz);
			playTypeToAdvanceConverterMap.put(PlayType.gd11c5_mq1, erth_fs);
			playTypeToAdvanceConverterMap.put(PlayType.gd11c5_mq2, qerfs);
			playTypeToAdvanceConverterMap.put(PlayType.gd11c5_mq3, qerfs);
		    playTypeToAdvanceConverterMap.put(PlayType.gd11c5_mz2, erth_fs);
			playTypeToAdvanceConverterMap.put(PlayType.gd11c5_mz3, erth_fs);
			
		    playTypeToAdvanceConverterMap.put(PlayType.gd11c5_dr2, gddt);
			playTypeToAdvanceConverterMap.put(PlayType.gd11c5_dr3, gddt);
			playTypeToAdvanceConverterMap.put(PlayType.gd11c5_dr4, gddt);
			playTypeToAdvanceConverterMap.put(PlayType.gd11c5_dr5, gddt);
			playTypeToAdvanceConverterMap.put(PlayType.gd11c5_dr6, gddt);
			playTypeToAdvanceConverterMap.put(PlayType.gd11c5_dr7, gddt);
//			playTypeToAdvanceConverterMap.put(PlayType.gd11c5_dr8, gddt);
			playTypeToAdvanceConverterMap.put(PlayType.gd11c5_dz2, gddt);
			playTypeToAdvanceConverterMap.put(PlayType.gd11c5_dz3, gddt);
			
			
	}
	
	
	public static String getPlayType(Ticket ticket){
		if(PlayType.jiangsu_ertong_fu.getValue()==ticket.getPlayType()){
			if(ticket.getContent().split("\\-")[1].contains(",")){
				return "502";
			}else{
				return "501";
			}
		}
		return playTypeMap.get(ticket.getPlayType());
	}

     
	
}
