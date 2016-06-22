 package com.lottery.ticket.vender.impl.shcp;

import java.util.HashMap;
import java.util.Map;

import com.lottery.common.contains.lottery.LotteryType;
import com.lottery.common.contains.lottery.PlayType;
import com.lottery.core.domain.ticket.Ticket;
import com.lottery.ticket.IPhaseConverter;
import com.lottery.ticket.IVenderTicketConverter;

public class SHCPLotteryDef {
	 /** 彩种转换 */
    protected static Map<LotteryType, String> lotteryTypeMap = new HashMap<LotteryType, String>();
    /** 彩种转换 */
    protected static Map<String,String> toLotteryTypeMap = new HashMap<String,String>();
    /** 彩期转换*/
    protected static Map<LotteryType, IPhaseConverter> phaseConverterMap = new HashMap<LotteryType, IPhaseConverter>();
    /** 玩法转换*/
    public static Map<Integer, String> playTypeMap = new HashMap<Integer, String>();
    /** 玩法转换*/
    public static Map<Integer, String> playMap = new HashMap<Integer, String>();
    public static Map<String, String> WEEKSTR = new HashMap<String, String>();
    /** 票内容转换 */
    protected static Map<PlayType, IVenderTicketConverter> playTypeToAdvanceConverterMap = new HashMap<PlayType, IVenderTicketConverter>();
    static {	
    	
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
		
		
		phaseConverterMap.put(LotteryType.ZC_SFC, defaultPhaseConverter);
		phaseConverterMap.put(LotteryType.ZC_RJC, defaultPhaseConverter);
		phaseConverterMap.put(LotteryType.ZC_JQC, defaultPhaseConverter);
		phaseConverterMap.put(LotteryType.ZC_BQC, defaultPhaseConverter);
		phaseConverterMap.put(LotteryType.CJDLT, defaultPhaseConverter);
		phaseConverterMap.put(LotteryType.PL5, defaultPhaseConverter);
		phaseConverterMap.put(LotteryType.PL3, defaultPhaseConverter);
		phaseConverterMap.put(LotteryType.QXC, defaultPhaseConverter);
		phaseConverterMap.put(LotteryType.SSQ, defaultPhaseConverter);
		phaseConverterMap.put(LotteryType.F3D, defaultPhaseConverter);
		
		//彩种转换
		lotteryTypeMap.put(LotteryType.SSQ, "01");
		lotteryTypeMap.put(LotteryType.QLC, "07");
		lotteryTypeMap.put(LotteryType.F3D, "03");
	
		lotteryTypeMap.put(LotteryType.JCLQ_SF,"31");
		lotteryTypeMap.put(LotteryType.JCLQ_RFSF,"31");
		lotteryTypeMap.put(LotteryType.JCLQ_SFC,"31"); 
		lotteryTypeMap.put(LotteryType.JCLQ_DXF,"31");
		lotteryTypeMap.put(LotteryType.JCLQ_HHGG,"31");
		lotteryTypeMap.put(LotteryType.JCZQ_RQ_SPF,"30");
		lotteryTypeMap.put(LotteryType.JCZQ_BF,"30");
		lotteryTypeMap.put(LotteryType.JCZQ_JQS,"30");
		lotteryTypeMap.put(LotteryType.JCZQ_BQC,"30");
		lotteryTypeMap.put(LotteryType.JCZQ_SPF_WRQ,"30");
		lotteryTypeMap.put(LotteryType.JCZQ_HHGG,"30");
		
		
		toLotteryTypeMap.put("3010", "SPF");
		toLotteryTypeMap.put("3007", "CBF");
		toLotteryTypeMap.put("3009", "BQC");
		toLotteryTypeMap.put("3008", "JQS");
		toLotteryTypeMap.put("3006", "RSPF");
		
		toLotteryTypeMap.put("3001", "SF");
		toLotteryTypeMap.put("3002", "RFSF");
		toLotteryTypeMap.put("3003", "SFC");
		toLotteryTypeMap.put("3004", "DXF");
		
		//玩法转换
		playTypeMap.put(PlayType.ssq_dan.getValue(), "1");
		playTypeMap.put(PlayType.ssq_fs.getValue(), "2");
		playTypeMap.put(PlayType.ssq_dt.getValue(), "5");
		
		playTypeMap.put(PlayType.qlc_dan.getValue(), "1");
		playTypeMap.put(PlayType.qlc_fs.getValue(), "2");
		playTypeMap.put(PlayType.qlc_dt.getValue(), "5");
		
		playTypeMap.put(PlayType.dlt_dan.getValue(), "1");
		playTypeMap.put(PlayType.dlt_fu.getValue(), "2");
		playTypeMap.put(PlayType.dlt_dantuo.getValue(), "5");

		// 3D
		playTypeMap.put(PlayType.d3_zhi_dan.getValue(), "1");
		playTypeMap.put(PlayType.d3_z3_dan.getValue(), "1");
		playTypeMap.put(PlayType.d3_z6_dan.getValue(), "1");
		playTypeMap.put(PlayType.d3_zhi_fs.getValue(), "2");
		playTypeMap.put(PlayType.d3_z3_fs.getValue(), "3");
		playTypeMap.put(PlayType.d3_z6_fs.getValue(), "3");
		playTypeMap.put(PlayType.d3_zhi_hz.getValue(), "4");
		playTypeMap.put(PlayType.d3_z3_hz.getValue(), "4");
		playTypeMap.put(PlayType.d3_z6_hz.getValue(), "4");
		
		
		
		playTypeMap.put(PlayType.jclq_sf_1_1.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_2_1.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_3_1.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_4_1.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_5_1.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_6_1.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_7_1.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_8_1.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_3_3.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_3_4.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_4_4.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_4_5.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_4_6.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_4_11.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_5_5.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_5_6.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_5_10.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_5_16.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_5_20.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_5_26.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_6_6.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_6_7.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_6_15.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_6_20.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_6_22.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_6_35.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_6_42.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_6_50.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_6_57.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_7_7.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_7_8.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_7_21.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_7_35.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_7_120.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_8_8.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_8_9.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_8_28.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_8_56.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_8_70.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_8_247.getValue(), "SF");
		
		
		playTypeMap.put(PlayType.jclq_sf_2_1_dan.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_3_1_dan.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_4_1_dan.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_5_1_dan.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_6_1_dan.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_7_1_dan.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_8_1_dan.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_3_3_dan.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_3_4_dan.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_4_4_dan.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_4_5_dan.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_4_6_dan.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_4_11_dan.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_5_5_dan.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_5_6_dan.getValue(),"SF");             
		playTypeMap.put(PlayType.jclq_sf_5_10_dan.getValue(),"SF");
		playTypeMap.put(PlayType.jclq_sf_5_16_dan.getValue(),"SF");
		playTypeMap.put(PlayType.jclq_sf_5_20_dan.getValue(),"SF");
		playTypeMap.put(PlayType.jclq_sf_5_26_dan.getValue(),"SF");
		playTypeMap.put(PlayType.jclq_sf_6_6_dan.getValue(),"SF");
		playTypeMap.put(PlayType.jclq_sf_6_7_dan.getValue(),"SF");
		playTypeMap.put(PlayType.jclq_sf_6_15_dan.getValue(),"SF");
		playTypeMap.put(PlayType.jclq_sf_6_20_dan.getValue(),"SF");
		playTypeMap.put(PlayType.jclq_sf_6_22_dan.getValue(),"SF");
		playTypeMap.put(PlayType.jclq_sf_6_35_dan.getValue(),"SF");
		playTypeMap.put(PlayType.jclq_sf_6_42_dan.getValue(),"SF");
		playTypeMap.put(PlayType.jclq_sf_6_50_dan.getValue(),"SF");
		playTypeMap.put(PlayType.jclq_sf_6_57_dan.getValue(),"SF");
		playTypeMap.put(PlayType.jclq_sf_7_7_dan.getValue(),"SF");
		playTypeMap.put(PlayType.jclq_sf_7_8_dan.getValue(),"SF");
		playTypeMap.put(PlayType.jclq_sf_7_21_dan.getValue(),"SF");
		playTypeMap.put(PlayType.jclq_sf_7_35_dan.getValue(),"SF");
		playTypeMap.put(PlayType.jclq_sf_7_120_dan.getValue(),"SF");             
		playTypeMap.put(PlayType.jclq_sf_8_8_dan.getValue(),"SF");
		playTypeMap.put(PlayType.jclq_sf_8_9_dan.getValue(),"SF");
		playTypeMap.put(PlayType.jclq_sf_8_28_dan.getValue(),"SF");
		playTypeMap.put(PlayType.jclq_sf_8_56_dan.getValue(),"SF");
		playTypeMap.put(PlayType.jclq_sf_8_70_dan.getValue(),"SF");
		playTypeMap.put(PlayType.jclq_sf_8_247_dan.getValue(),"SF");             
		
		
		
		playTypeMap.put(PlayType.jclq_rfsf_1_1.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_2_1.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_3_1.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_4_1.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_5_1.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_6_1.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_7_1.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_8_1.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_3_3.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_3_4.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_4_4.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_4_5.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_4_6.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_4_11.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_5_5.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_5_6.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_5_10.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_5_16.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_5_20.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_5_26.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_6_6.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_6_7.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_6_15.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_6_20.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_6_22.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_6_35.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_6_42.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_6_50.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_6_57.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_7_7.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_7_8.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_7_21.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_7_35.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_7_120.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_8_8.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_8_9.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_8_28.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_8_56.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_8_70.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_8_247.getValue(),"RFSF");
		
		
		
		playTypeMap.put(PlayType.jclq_rfsf_1_1_dan.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_2_1_dan.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_3_1_dan.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_4_1_dan.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_5_1_dan.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_6_1_dan.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_7_1_dan.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_8_1_dan.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_3_3_dan.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_3_4_dan.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_4_4_dan.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_4_5_dan.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_4_6_dan.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_4_11_dan.getValue(),"RFSF");           
		playTypeMap.put(PlayType.jclq_rfsf_5_5_dan.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_5_6_dan.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_5_10_dan.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_5_16_dan.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_5_20_dan.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_5_26_dan.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_6_6_dan.getValue(),"RFSF");           
		playTypeMap.put(PlayType.jclq_rfsf_6_7_dan.getValue(),"RFSF");           
		playTypeMap.put(PlayType.jclq_rfsf_6_15_dan.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_6_20_dan.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_6_22_dan.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_6_35_dan.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_6_42_dan.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_6_50_dan.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_6_57_dan.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_7_7_dan.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_7_8_dan.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_7_21_dan.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_7_35_dan.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_7_120_dan.getValue(),"RFSF");           
		playTypeMap.put(PlayType.jclq_rfsf_8_8_dan.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_8_9_dan.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_8_28_dan.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_8_56_dan.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_8_70_dan.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_8_247_dan.getValue(),"RFSF");           
	
		playTypeMap.put(PlayType.jclq_sfc_1_1.getValue(),"SFC");
		playTypeMap.put(PlayType.jclq_sfc_2_1.getValue(),"SFC");
		playTypeMap.put(PlayType.jclq_sfc_3_1.getValue(),"SFC");
		playTypeMap.put(PlayType.jclq_sfc_4_1.getValue(),"SFC");
		playTypeMap.put(PlayType.jclq_sfc_5_1.getValue(),"SFC");
		playTypeMap.put(PlayType.jclq_sfc_6_1.getValue(),"SFC");
		playTypeMap.put(PlayType.jclq_sfc_7_1.getValue(),"SFC");
		playTypeMap.put(PlayType.jclq_sfc_8_1.getValue(),"SFC");
		playTypeMap.put(PlayType.jclq_sfc_3_3.getValue(),"SFC");
		playTypeMap.put(PlayType.jclq_sfc_3_4.getValue(),"SFC");
		playTypeMap.put(PlayType.jclq_sfc_4_4.getValue(),"SFC");
		playTypeMap.put(PlayType.jclq_sfc_4_5.getValue(),"SFC");
		playTypeMap.put(PlayType.jclq_sfc_4_6.getValue(),"SFC");
		playTypeMap.put(PlayType.jclq_sfc_4_11.getValue(),"SFC");
		
		
		playTypeMap.put(PlayType.jclq_sfc_1_1_dan.getValue(),"SFC");
		playTypeMap.put(PlayType.jclq_sfc_2_1_dan.getValue(),"SFC");
		playTypeMap.put(PlayType.jclq_sfc_3_1_dan.getValue(),"SFC");
		playTypeMap.put(PlayType.jclq_sfc_4_1_dan.getValue(),"SFC");
		playTypeMap.put(PlayType.jclq_sfc_5_1_dan.getValue(),"SFC");
		playTypeMap.put(PlayType.jclq_sfc_6_1_dan.getValue(),"SFC");
		playTypeMap.put(PlayType.jclq_sfc_7_1_dan.getValue(),"SFC");
		playTypeMap.put(PlayType.jclq_sfc_8_1_dan.getValue(),"SFC");
		playTypeMap.put(PlayType.jclq_sfc_3_3_dan.getValue(),"SFC");
		playTypeMap.put(PlayType.jclq_sfc_3_4_dan.getValue(),"SFC");
		playTypeMap.put(PlayType.jclq_sfc_4_4_dan.getValue(),"SFC");
		playTypeMap.put(PlayType.jclq_sfc_4_5_dan.getValue(),"SFC");
		playTypeMap.put(PlayType.jclq_sfc_4_6_dan.getValue(),"SFC");
		playTypeMap.put(PlayType.jclq_sfc_4_11_dan.getValue(),"SFC");
    
		playTypeMap.put(PlayType.jclq_dxf_1_1.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_2_1.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_3_1.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_4_1.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_5_1.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_6_1.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_7_1.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_8_1.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_3_3.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_3_4.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_4_4.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_4_5.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_4_6.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_4_11.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_5_5.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_5_6.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_5_10.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_5_16.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_5_20.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_5_26.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_6_6.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_6_7.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_6_15.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_6_20.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_6_22.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_6_35.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_6_42.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_6_50.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_6_57.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_7_7.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_7_8.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_7_21.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_7_35.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_7_120.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_8_8.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_8_9.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_8_28.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_8_56.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_8_70.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_8_247.getValue(),"DXF");
		
		
		
		playTypeMap.put(PlayType.jclq_dxf_1_1_dan.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_2_1_dan.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_3_1_dan.getValue(),"DXF");
	    playTypeMap.put(PlayType.jclq_dxf_4_1_dan.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_5_1_dan.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_6_1_dan.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_7_1_dan.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_8_1_dan.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_3_3_dan.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_3_4_dan.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_4_4_dan.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_4_5_dan.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_4_6_dan.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_4_11_dan.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_5_5_dan.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_5_6_dan.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_5_10_dan.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_5_16_dan.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_5_20_dan.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_5_26_dan.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_6_6_dan.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_6_7_dan.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_6_15_dan.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_6_20_dan.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_6_22_dan.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_6_35_dan.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_6_42_dan.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_6_50_dan.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_6_57_dan.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_7_7_dan.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_7_8_dan.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_7_21_dan.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_7_35_dan.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_7_120_dan.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_8_8_dan.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_8_9_dan.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_8_28_dan.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_8_56_dan.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_8_70_dan.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_8_247_dan.getValue(),"DXF");

		playTypeMap.put(PlayType.jclq_mix_1_1.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_2_1.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_3_1.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_4_1.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_5_1.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_6_1.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_7_1.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_8_1.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_3_3.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_3_4.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_4_4.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_4_5.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_4_6.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_4_11.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_5_5.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_5_6.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_5_10.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_5_16.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_5_20.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_5_26.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_6_6.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_6_7.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_6_15.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_6_20.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_6_22.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_6_35.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_6_42.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_6_50.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_6_57.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_7_7.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_7_8.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_7_21.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_7_35.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_7_120.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_8_8.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_8_9.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_8_28.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_8_56.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_8_70.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_8_247.getValue(),"LQHH");
	
		playTypeMap.put(PlayType.jclq_mix_2_1_dan.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_3_1_dan.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_4_1_dan.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_5_1_dan.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_6_1_dan.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_7_1_dan.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_8_1_dan.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_3_3_dan.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_3_4_dan.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_4_4_dan.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_4_5_dan.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_4_6_dan.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_4_11_dan.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_5_5_dan.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_5_6_dan.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_5_10_dan.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_5_16_dan.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_5_20_dan.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_5_26_dan.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_6_6_dan.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_6_7_dan.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_6_15_dan.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_6_20_dan.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_6_22_dan.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_6_35_dan.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_6_42_dan.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_6_50_dan.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_6_57_dan.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_7_7_dan.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_7_8_dan.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_7_21_dan.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_7_35_dan.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_7_120_dan.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_8_8_dan.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_8_9_dan.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_8_28_dan.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_8_56_dan.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_8_70_dan.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_8_247_dan.getValue(),"LQHH");

		playTypeMap.put(PlayType.jczq_spf_1_1.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_2_1.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_3_1.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_4_1.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_5_1.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_6_1.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_7_1.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_8_1.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_3_3.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_3_4.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_4_4.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_4_5.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_4_6.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_4_11.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_5_5.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_5_6.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_5_10.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_5_16.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_5_20.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_5_26.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_6_6.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_6_7.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_6_15.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_6_20.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_6_22.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_6_35.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_6_42.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_6_50.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_6_57.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_7_7.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_7_8.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_7_21.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_7_35.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_7_120.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_8_8.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_8_9.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_8_28.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_8_56.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_8_70.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_8_247.getValue(),"RSPF");
		
		playTypeMap.put(PlayType.jczq_spf_2_1_dan.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_3_1_dan.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_4_1_dan.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_5_1_dan.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_6_1_dan.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_7_1_dan.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_8_1_dan.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_3_3_dan.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_3_4_dan.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_4_4_dan.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_4_5_dan.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_4_6_dan.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_4_11_dan.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_5_5_dan.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_5_6_dan.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_5_10_dan.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_5_16_dan.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_5_20_dan.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_5_26_dan.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_6_6_dan.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_6_7_dan.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_6_15_dan.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_6_20_dan.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_6_22_dan.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_6_35_dan.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_6_42_dan.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_6_50_dan.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_6_57_dan.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_7_7_dan.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_7_8_dan.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_7_21_dan.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_7_35_dan.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_7_120_dan.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_8_8_dan.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_8_9_dan.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_8_28_dan.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_8_56_dan.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_8_70_dan.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_8_247_dan.getValue(),"RSPF");
		
		playTypeMap.put(PlayType.jczq_bf_1_1.getValue(),"CBF");
		playTypeMap.put(PlayType.jczq_bf_2_1.getValue(),"CBF");
		playTypeMap.put(PlayType.jczq_bf_3_1.getValue(),"CBF");
		playTypeMap.put(PlayType.jczq_bf_4_1.getValue(),"CBF");
		playTypeMap.put(PlayType.jczq_bf_5_1.getValue(),"CBF");
		playTypeMap.put(PlayType.jczq_bf_6_1.getValue(),"CBF");
		playTypeMap.put(PlayType.jczq_bf_7_1.getValue(),"CBF");
		playTypeMap.put(PlayType.jczq_bf_8_1.getValue(),"CBF");
		playTypeMap.put(PlayType.jczq_bf_3_3.getValue(),"CBF");
		playTypeMap.put(PlayType.jczq_bf_3_4.getValue(),"CBF");
		playTypeMap.put(PlayType.jczq_bf_4_4.getValue(),"CBF");
		playTypeMap.put(PlayType.jczq_bf_4_5.getValue(),"CBF");
		playTypeMap.put(PlayType.jczq_bf_4_6.getValue(),"CBF");
		playTypeMap.put(PlayType.jczq_bf_4_11.getValue(),"CBF");
		
		
		playTypeMap.put(PlayType.jczq_bf_2_1_dan.getValue(),"CBF");
		playTypeMap.put(PlayType.jczq_bf_3_1_dan.getValue(),"CBF");
		playTypeMap.put(PlayType.jczq_bf_4_1_dan.getValue(),"CBF");
		playTypeMap.put(PlayType.jczq_bf_5_1_dan.getValue(),"CBF");
		playTypeMap.put(PlayType.jczq_bf_6_1_dan.getValue(),"CBF");
		playTypeMap.put(PlayType.jczq_bf_7_1_dan.getValue(),"CBF");
		playTypeMap.put(PlayType.jczq_bf_8_1_dan.getValue(),"CBF");
		playTypeMap.put(PlayType.jczq_bf_3_3_dan.getValue(),"CBF");
		playTypeMap.put(PlayType.jczq_bf_3_4_dan.getValue(),"CBF");
		playTypeMap.put(PlayType.jczq_bf_4_4_dan.getValue(),"CBF");
		playTypeMap.put(PlayType.jczq_bf_4_5_dan.getValue(),"CBF");
		playTypeMap.put(PlayType.jczq_bf_4_6_dan.getValue(),"CBF");
		playTypeMap.put(PlayType.jczq_bf_4_11_dan.getValue(),"CBF");
		
		playTypeMap.put(PlayType.jczq_jqs_1_1.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_2_1.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_3_1.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_4_1.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_5_1.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_6_1.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_7_1.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_8_1.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_3_3.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_3_4.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_4_4.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_4_5.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_4_6.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_4_11.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_5_5.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_5_6.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_5_10.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_5_16.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_5_20.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_5_26.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_6_6.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_6_7.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_6_15.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_6_20.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_6_22.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_6_35.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_6_42.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_6_50.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_6_57.getValue(),"JQS");
	
		playTypeMap.put(PlayType.jczq_jqs_2_1_dan.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_3_1_dan.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_4_1_dan.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_5_1_dan.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_6_1_dan.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_7_1_dan.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_8_1_dan.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_3_3_dan.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_3_4_dan.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_4_4_dan.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_4_5_dan.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_4_6_dan.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_4_11_dan.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_5_5_dan.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_5_6_dan.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_5_10_dan.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_5_16_dan.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_5_20_dan.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_5_26_dan.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_6_6_dan.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_6_7_dan.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_6_15_dan.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_6_20_dan.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_6_22_dan.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_6_35_dan.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_6_42_dan.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_6_50_dan.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_6_57_dan.getValue(),"JQS");
	
		playTypeMap.put(PlayType.jczq_bqc_1_1.getValue(),"BQC");
		playTypeMap.put(PlayType.jczq_bqc_2_1.getValue(),"BQC");
		playTypeMap.put(PlayType.jczq_bqc_3_1.getValue(),"BQC");
		playTypeMap.put(PlayType.jczq_bqc_4_1.getValue(),"BQC");
		playTypeMap.put(PlayType.jczq_bqc_5_1.getValue(),"BQC");
		playTypeMap.put(PlayType.jczq_bqc_6_1.getValue(),"BQC");
		playTypeMap.put(PlayType.jczq_bqc_7_1.getValue(),"BQC");
		playTypeMap.put(PlayType.jczq_bqc_8_1.getValue(),"BQC");
		playTypeMap.put(PlayType.jczq_bqc_3_3.getValue(),"BQC");
		playTypeMap.put(PlayType.jczq_bqc_3_4.getValue(),"BQC");
		playTypeMap.put(PlayType.jczq_bqc_4_4.getValue(),"BQC");
		playTypeMap.put(PlayType.jczq_bqc_4_5.getValue(),"BQC");
		playTypeMap.put(PlayType.jczq_bqc_4_6.getValue(),"BQC");
		playTypeMap.put(PlayType.jczq_bqc_4_11.getValue(),"BQC");
		
		
		playTypeMap.put(PlayType.jczq_bqc_2_1_dan.getValue(),"BQC");
		playTypeMap.put(PlayType.jczq_bqc_3_1_dan.getValue(),"BQC");
		playTypeMap.put(PlayType.jczq_bqc_4_1_dan.getValue(),"BQC");
		playTypeMap.put(PlayType.jczq_bqc_5_1_dan.getValue(),"BQC");
		playTypeMap.put(PlayType.jczq_bqc_6_1_dan.getValue(),"BQC");
		playTypeMap.put(PlayType.jczq_bqc_7_1_dan.getValue(),"BQC");
		playTypeMap.put(PlayType.jczq_bqc_8_1_dan.getValue(),"BQC");
		playTypeMap.put(PlayType.jczq_bqc_3_3_dan.getValue(),"BQC");
		playTypeMap.put(PlayType.jczq_bqc_3_4_dan.getValue(),"BQC");
		playTypeMap.put(PlayType.jczq_bqc_4_4_dan.getValue(),"BQC");
		playTypeMap.put(PlayType.jczq_bqc_4_5_dan.getValue(),"BQC");
		playTypeMap.put(PlayType.jczq_bqc_4_6_dan.getValue(),"BQC");
		playTypeMap.put(PlayType.jczq_bqc_4_11_dan.getValue(),"BQC");

		playTypeMap.put(PlayType.jczq_spfwrq_1_1.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_2_1.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_3_1.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_4_1.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_5_1.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_6_1.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_7_1.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_8_1.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_3_3.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_3_4.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_4_4.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_4_5.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_4_6.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_4_11.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_5_5.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_5_6.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_5_10.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_5_16.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_5_20.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_5_26.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_6_6.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_6_7.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_6_15.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_6_20.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_6_22.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_6_35.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_6_42.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_6_50.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_6_57.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_7_7.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_7_8.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_7_21.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_7_35.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_7_120.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_8_8.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_8_9.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_8_28.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_8_56.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_8_70.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_8_247.getValue(),"SPF");
		
		playTypeMap.put(PlayType.jczq_spfwrq_2_1_dan.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_3_1_dan.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_4_1_dan.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_5_1_dan.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_6_1_dan.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_7_1_dan.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_8_1_dan.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_3_3_dan.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_3_4_dan.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_4_4_dan.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_4_5_dan.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_4_6_dan.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_4_11_dan.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_5_5_dan.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_5_6_dan.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_5_10_dan.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_5_16_dan.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_5_20_dan.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_5_26_dan.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_6_6_dan.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_6_7_dan.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_6_15_dan.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_6_20_dan.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_6_22_dan.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_6_35_dan.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_6_42_dan.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_6_50_dan.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_6_57_dan.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_7_7_dan.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_7_8_dan.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_7_21_dan.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_7_35_dan.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_7_120_dan.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_8_8_dan.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_8_9_dan.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_8_28_dan.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_8_56_dan.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_8_70_dan.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_8_247_dan.getValue(),"SPF");
		
		playTypeMap.put(PlayType.jczq_mix_1_1.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_2_1.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_3_1.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_4_1.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_5_1.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_6_1.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_7_1.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_8_1.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_3_3.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_3_4.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_4_4.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_4_5.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_4_6.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_4_11.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_5_5.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_5_6.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_5_10.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_5_16.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_5_20.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_5_26.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_6_6.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_6_7.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_6_15.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_6_20.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_6_22.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_6_35.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_6_42.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_6_50.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_6_57.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_7_7.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_7_8.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_7_21.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_7_35.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_7_120.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_8_8.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_8_9.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_8_28.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_8_56.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_8_70.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_8_247.getValue(),"ZQHH");
	
		playTypeMap.put(PlayType.jczq_mix_2_1_dan.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_3_1_dan.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_4_1_dan.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_5_1_dan.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_6_1_dan.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_7_1_dan.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_8_1_dan.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_3_3_dan.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_3_4_dan.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_4_4_dan.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_4_5_dan.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_4_6_dan.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_4_11_dan.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_5_5_dan.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_5_6_dan.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_5_10_dan.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_5_16_dan.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_5_20_dan.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_5_26_dan.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_6_6_dan.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_6_7_dan.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_6_15_dan.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_6_20_dan.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_6_22_dan.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_6_35_dan.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_6_42_dan.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_6_50_dan.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_6_57_dan.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_7_7_dan.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_7_8_dan.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_7_21_dan.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_7_35_dan.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_7_120_dan.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_8_8_dan.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_8_9_dan.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_8_28_dan.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_8_56_dan.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_8_70_dan.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_8_247_dan.getValue(),"ZQHH");
		
		
		playMap.put(PlayType.d3_zhi_dan.getValue(), "1");
		playMap.put(PlayType.d3_z3_dan.getValue(), "2");
		playMap.put(PlayType.d3_z6_dan.getValue(), "3");
		playMap.put(PlayType.d3_zhi_fs.getValue(), "1");
		playMap.put(PlayType.d3_z3_fs.getValue(), "2");
		playMap.put(PlayType.d3_z6_fs.getValue(), "3");
		playMap.put(PlayType.d3_zhi_hz.getValue(), "1");
		playMap.put(PlayType.d3_z3_hz.getValue(), "2");
		playMap.put(PlayType.d3_z6_hz.getValue(), "3");
		
		
		playTypeMap.put(PlayType.jclq_sf_1_1.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_2_1.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_3_1.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_4_1.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_5_1.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_6_1.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_7_1.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_8_1.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_3_3.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_3_4.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_4_4.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_4_5.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_4_6.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_4_11.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_5_5.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_5_6.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_5_10.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_5_16.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_5_20.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_5_26.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_6_6.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_6_7.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_6_15.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_6_20.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_6_22.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_6_35.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_6_42.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_6_50.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_6_57.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_7_7.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_7_8.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_7_21.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_7_35.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_7_120.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_8_8.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_8_9.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_8_28.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_8_56.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_8_70.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_8_247.getValue(), "SF");
		
		
		playTypeMap.put(PlayType.jclq_sf_2_1_dan.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_3_1_dan.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_4_1_dan.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_5_1_dan.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_6_1_dan.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_7_1_dan.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_8_1_dan.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_3_3_dan.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_3_4_dan.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_4_4_dan.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_4_5_dan.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_4_6_dan.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_4_11_dan.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_5_5_dan.getValue(), "SF");
		playTypeMap.put(PlayType.jclq_sf_5_6_dan.getValue(),"SF");             
		playTypeMap.put(PlayType.jclq_sf_5_10_dan.getValue(),"SF");
		playTypeMap.put(PlayType.jclq_sf_5_16_dan.getValue(),"SF");
		playTypeMap.put(PlayType.jclq_sf_5_20_dan.getValue(),"SF");
		playTypeMap.put(PlayType.jclq_sf_5_26_dan.getValue(),"SF");
		playTypeMap.put(PlayType.jclq_sf_6_6_dan.getValue(),"SF");
		playTypeMap.put(PlayType.jclq_sf_6_7_dan.getValue(),"SF");
		playTypeMap.put(PlayType.jclq_sf_6_15_dan.getValue(),"SF");
		playTypeMap.put(PlayType.jclq_sf_6_20_dan.getValue(),"SF");
		playTypeMap.put(PlayType.jclq_sf_6_22_dan.getValue(),"SF");
		playTypeMap.put(PlayType.jclq_sf_6_35_dan.getValue(),"SF");
		playTypeMap.put(PlayType.jclq_sf_6_42_dan.getValue(),"SF");
		playTypeMap.put(PlayType.jclq_sf_6_50_dan.getValue(),"SF");
		playTypeMap.put(PlayType.jclq_sf_6_57_dan.getValue(),"SF");
		playTypeMap.put(PlayType.jclq_sf_7_7_dan.getValue(),"SF");
		playTypeMap.put(PlayType.jclq_sf_7_8_dan.getValue(),"SF");
		playTypeMap.put(PlayType.jclq_sf_7_21_dan.getValue(),"SF");
		playTypeMap.put(PlayType.jclq_sf_7_35_dan.getValue(),"SF");
		playTypeMap.put(PlayType.jclq_sf_7_120_dan.getValue(),"SF");             
		playTypeMap.put(PlayType.jclq_sf_8_8_dan.getValue(),"SF");
		playTypeMap.put(PlayType.jclq_sf_8_9_dan.getValue(),"SF");
		playTypeMap.put(PlayType.jclq_sf_8_28_dan.getValue(),"SF");
		playTypeMap.put(PlayType.jclq_sf_8_56_dan.getValue(),"SF");
		playTypeMap.put(PlayType.jclq_sf_8_70_dan.getValue(),"SF");
		playTypeMap.put(PlayType.jclq_sf_8_247_dan.getValue(),"SF");             
		
		
		
		playTypeMap.put(PlayType.jclq_rfsf_1_1.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_2_1.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_3_1.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_4_1.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_5_1.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_6_1.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_7_1.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_8_1.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_3_3.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_3_4.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_4_4.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_4_5.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_4_6.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_4_11.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_5_5.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_5_6.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_5_10.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_5_16.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_5_20.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_5_26.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_6_6.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_6_7.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_6_15.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_6_20.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_6_22.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_6_35.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_6_42.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_6_50.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_6_57.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_7_7.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_7_8.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_7_21.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_7_35.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_7_120.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_8_8.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_8_9.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_8_28.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_8_56.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_8_70.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_8_247.getValue(),"RFSF");
		
		
		
		playTypeMap.put(PlayType.jclq_rfsf_1_1_dan.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_2_1_dan.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_3_1_dan.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_4_1_dan.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_5_1_dan.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_6_1_dan.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_7_1_dan.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_8_1_dan.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_3_3_dan.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_3_4_dan.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_4_4_dan.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_4_5_dan.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_4_6_dan.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_4_11_dan.getValue(),"RFSF");           
		playTypeMap.put(PlayType.jclq_rfsf_5_5_dan.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_5_6_dan.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_5_10_dan.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_5_16_dan.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_5_20_dan.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_5_26_dan.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_6_6_dan.getValue(),"RFSF");           
		playTypeMap.put(PlayType.jclq_rfsf_6_7_dan.getValue(),"RFSF");           
		playTypeMap.put(PlayType.jclq_rfsf_6_15_dan.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_6_20_dan.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_6_22_dan.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_6_35_dan.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_6_42_dan.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_6_50_dan.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_6_57_dan.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_7_7_dan.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_7_8_dan.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_7_21_dan.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_7_35_dan.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_7_120_dan.getValue(),"RFSF");           
		playTypeMap.put(PlayType.jclq_rfsf_8_8_dan.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_8_9_dan.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_8_28_dan.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_8_56_dan.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_8_70_dan.getValue(),"RFSF");
		playTypeMap.put(PlayType.jclq_rfsf_8_247_dan.getValue(),"RFSF");           
	
		playTypeMap.put(PlayType.jclq_sfc_1_1.getValue(),"SFC");
		playTypeMap.put(PlayType.jclq_sfc_2_1.getValue(),"SFC");
		playTypeMap.put(PlayType.jclq_sfc_3_1.getValue(),"SFC");
		playTypeMap.put(PlayType.jclq_sfc_4_1.getValue(),"SFC");
		playTypeMap.put(PlayType.jclq_sfc_5_1.getValue(),"SFC");
		playTypeMap.put(PlayType.jclq_sfc_6_1.getValue(),"SFC");
		playTypeMap.put(PlayType.jclq_sfc_7_1.getValue(),"SFC");
		playTypeMap.put(PlayType.jclq_sfc_8_1.getValue(),"SFC");
		playTypeMap.put(PlayType.jclq_sfc_3_3.getValue(),"SFC");
		playTypeMap.put(PlayType.jclq_sfc_3_4.getValue(),"SFC");
		playTypeMap.put(PlayType.jclq_sfc_4_4.getValue(),"SFC");
		playTypeMap.put(PlayType.jclq_sfc_4_5.getValue(),"SFC");
		playTypeMap.put(PlayType.jclq_sfc_4_6.getValue(),"SFC");
		playTypeMap.put(PlayType.jclq_sfc_4_11.getValue(),"SFC");
		
		
		playTypeMap.put(PlayType.jclq_sfc_1_1_dan.getValue(),"SFC");
		playTypeMap.put(PlayType.jclq_sfc_2_1_dan.getValue(),"SFC");
		playTypeMap.put(PlayType.jclq_sfc_3_1_dan.getValue(),"SFC");
		playTypeMap.put(PlayType.jclq_sfc_4_1_dan.getValue(),"SFC");
		playTypeMap.put(PlayType.jclq_sfc_5_1_dan.getValue(),"SFC");
		playTypeMap.put(PlayType.jclq_sfc_6_1_dan.getValue(),"SFC");
		playTypeMap.put(PlayType.jclq_sfc_7_1_dan.getValue(),"SFC");
		playTypeMap.put(PlayType.jclq_sfc_8_1_dan.getValue(),"SFC");
		playTypeMap.put(PlayType.jclq_sfc_3_3_dan.getValue(),"SFC");
		playTypeMap.put(PlayType.jclq_sfc_3_4_dan.getValue(),"SFC");
		playTypeMap.put(PlayType.jclq_sfc_4_4_dan.getValue(),"SFC");
		playTypeMap.put(PlayType.jclq_sfc_4_5_dan.getValue(),"SFC");
		playTypeMap.put(PlayType.jclq_sfc_4_6_dan.getValue(),"SFC");
		playTypeMap.put(PlayType.jclq_sfc_4_11_dan.getValue(),"SFC");
    
		playTypeMap.put(PlayType.jclq_dxf_1_1.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_2_1.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_3_1.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_4_1.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_5_1.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_6_1.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_7_1.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_8_1.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_3_3.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_3_4.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_4_4.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_4_5.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_4_6.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_4_11.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_5_5.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_5_6.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_5_10.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_5_16.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_5_20.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_5_26.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_6_6.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_6_7.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_6_15.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_6_20.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_6_22.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_6_35.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_6_42.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_6_50.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_6_57.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_7_7.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_7_8.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_7_21.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_7_35.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_7_120.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_8_8.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_8_9.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_8_28.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_8_56.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_8_70.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_8_247.getValue(),"DXF");
		
		
		
		playTypeMap.put(PlayType.jclq_dxf_1_1_dan.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_2_1_dan.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_3_1_dan.getValue(),"DXF");
	    playTypeMap.put(PlayType.jclq_dxf_4_1_dan.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_5_1_dan.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_6_1_dan.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_7_1_dan.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_8_1_dan.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_3_3_dan.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_3_4_dan.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_4_4_dan.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_4_5_dan.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_4_6_dan.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_4_11_dan.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_5_5_dan.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_5_6_dan.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_5_10_dan.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_5_16_dan.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_5_20_dan.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_5_26_dan.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_6_6_dan.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_6_7_dan.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_6_15_dan.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_6_20_dan.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_6_22_dan.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_6_35_dan.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_6_42_dan.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_6_50_dan.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_6_57_dan.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_7_7_dan.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_7_8_dan.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_7_21_dan.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_7_35_dan.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_7_120_dan.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_8_8_dan.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_8_9_dan.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_8_28_dan.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_8_56_dan.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_8_70_dan.getValue(),"DXF");
		playTypeMap.put(PlayType.jclq_dxf_8_247_dan.getValue(),"DXF");

		playTypeMap.put(PlayType.jclq_mix_1_1.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_2_1.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_3_1.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_4_1.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_5_1.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_6_1.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_7_1.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_8_1.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_3_3.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_3_4.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_4_4.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_4_5.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_4_6.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_4_11.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_5_5.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_5_6.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_5_10.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_5_16.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_5_20.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_5_26.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_6_6.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_6_7.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_6_15.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_6_20.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_6_22.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_6_35.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_6_42.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_6_50.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_6_57.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_7_7.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_7_8.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_7_21.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_7_35.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_7_120.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_8_8.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_8_9.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_8_28.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_8_56.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_8_70.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_8_247.getValue(),"LQHH");
	
		playTypeMap.put(PlayType.jclq_mix_2_1_dan.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_3_1_dan.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_4_1_dan.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_5_1_dan.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_6_1_dan.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_7_1_dan.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_8_1_dan.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_3_3_dan.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_3_4_dan.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_4_4_dan.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_4_5_dan.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_4_6_dan.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_4_11_dan.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_5_5_dan.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_5_6_dan.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_5_10_dan.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_5_16_dan.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_5_20_dan.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_5_26_dan.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_6_6_dan.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_6_7_dan.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_6_15_dan.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_6_20_dan.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_6_22_dan.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_6_35_dan.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_6_42_dan.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_6_50_dan.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_6_57_dan.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_7_7_dan.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_7_8_dan.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_7_21_dan.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_7_35_dan.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_7_120_dan.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_8_8_dan.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_8_9_dan.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_8_28_dan.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_8_56_dan.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_8_70_dan.getValue(),"LQHH");
		playTypeMap.put(PlayType.jclq_mix_8_247_dan.getValue(),"LQHH");

		playTypeMap.put(PlayType.jczq_spf_1_1.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_2_1.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_3_1.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_4_1.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_5_1.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_6_1.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_7_1.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_8_1.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_3_3.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_3_4.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_4_4.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_4_5.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_4_6.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_4_11.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_5_5.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_5_6.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_5_10.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_5_16.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_5_20.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_5_26.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_6_6.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_6_7.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_6_15.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_6_20.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_6_22.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_6_35.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_6_42.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_6_50.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_6_57.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_7_7.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_7_8.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_7_21.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_7_35.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_7_120.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_8_8.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_8_9.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_8_28.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_8_56.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_8_70.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_8_247.getValue(),"RSPF");
		
		playTypeMap.put(PlayType.jczq_spf_2_1_dan.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_3_1_dan.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_4_1_dan.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_5_1_dan.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_6_1_dan.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_7_1_dan.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_8_1_dan.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_3_3_dan.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_3_4_dan.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_4_4_dan.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_4_5_dan.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_4_6_dan.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_4_11_dan.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_5_5_dan.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_5_6_dan.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_5_10_dan.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_5_16_dan.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_5_20_dan.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_5_26_dan.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_6_6_dan.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_6_7_dan.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_6_15_dan.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_6_20_dan.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_6_22_dan.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_6_35_dan.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_6_42_dan.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_6_50_dan.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_6_57_dan.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_7_7_dan.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_7_8_dan.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_7_21_dan.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_7_35_dan.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_7_120_dan.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_8_8_dan.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_8_9_dan.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_8_28_dan.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_8_56_dan.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_8_70_dan.getValue(),"RSPF");
		playTypeMap.put(PlayType.jczq_spf_8_247_dan.getValue(),"RSPF");
		
		playTypeMap.put(PlayType.jczq_bf_1_1.getValue(),"CBF");
		playTypeMap.put(PlayType.jczq_bf_2_1.getValue(),"CBF");
		playTypeMap.put(PlayType.jczq_bf_3_1.getValue(),"CBF");
		playTypeMap.put(PlayType.jczq_bf_4_1.getValue(),"CBF");
		playTypeMap.put(PlayType.jczq_bf_5_1.getValue(),"CBF");
		playTypeMap.put(PlayType.jczq_bf_6_1.getValue(),"CBF");
		playTypeMap.put(PlayType.jczq_bf_7_1.getValue(),"CBF");
		playTypeMap.put(PlayType.jczq_bf_8_1.getValue(),"CBF");
		playTypeMap.put(PlayType.jczq_bf_3_3.getValue(),"CBF");
		playTypeMap.put(PlayType.jczq_bf_3_4.getValue(),"CBF");
		playTypeMap.put(PlayType.jczq_bf_4_4.getValue(),"CBF");
		playTypeMap.put(PlayType.jczq_bf_4_5.getValue(),"CBF");
		playTypeMap.put(PlayType.jczq_bf_4_6.getValue(),"CBF");
		playTypeMap.put(PlayType.jczq_bf_4_11.getValue(),"CBF");
		
		
		playTypeMap.put(PlayType.jczq_bf_2_1_dan.getValue(),"CBF");
		playTypeMap.put(PlayType.jczq_bf_3_1_dan.getValue(),"CBF");
		playTypeMap.put(PlayType.jczq_bf_4_1_dan.getValue(),"CBF");
		playTypeMap.put(PlayType.jczq_bf_5_1_dan.getValue(),"CBF");
		playTypeMap.put(PlayType.jczq_bf_6_1_dan.getValue(),"CBF");
		playTypeMap.put(PlayType.jczq_bf_7_1_dan.getValue(),"CBF");
		playTypeMap.put(PlayType.jczq_bf_8_1_dan.getValue(),"CBF");
		playTypeMap.put(PlayType.jczq_bf_3_3_dan.getValue(),"CBF");
		playTypeMap.put(PlayType.jczq_bf_3_4_dan.getValue(),"CBF");
		playTypeMap.put(PlayType.jczq_bf_4_4_dan.getValue(),"CBF");
		playTypeMap.put(PlayType.jczq_bf_4_5_dan.getValue(),"CBF");
		playTypeMap.put(PlayType.jczq_bf_4_6_dan.getValue(),"CBF");
		playTypeMap.put(PlayType.jczq_bf_4_11_dan.getValue(),"CBF");
		
		playTypeMap.put(PlayType.jczq_jqs_1_1.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_2_1.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_3_1.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_4_1.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_5_1.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_6_1.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_7_1.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_8_1.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_3_3.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_3_4.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_4_4.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_4_5.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_4_6.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_4_11.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_5_5.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_5_6.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_5_10.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_5_16.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_5_20.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_5_26.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_6_6.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_6_7.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_6_15.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_6_20.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_6_22.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_6_35.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_6_42.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_6_50.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_6_57.getValue(),"JQS");
	
		playTypeMap.put(PlayType.jczq_jqs_2_1_dan.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_3_1_dan.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_4_1_dan.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_5_1_dan.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_6_1_dan.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_7_1_dan.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_8_1_dan.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_3_3_dan.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_3_4_dan.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_4_4_dan.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_4_5_dan.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_4_6_dan.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_4_11_dan.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_5_5_dan.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_5_6_dan.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_5_10_dan.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_5_16_dan.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_5_20_dan.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_5_26_dan.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_6_6_dan.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_6_7_dan.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_6_15_dan.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_6_20_dan.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_6_22_dan.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_6_35_dan.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_6_42_dan.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_6_50_dan.getValue(),"JQS");
		playTypeMap.put(PlayType.jczq_jqs_6_57_dan.getValue(),"JQS");
	
		playTypeMap.put(PlayType.jczq_bqc_1_1.getValue(),"BQC");
		playTypeMap.put(PlayType.jczq_bqc_2_1.getValue(),"BQC");
		playTypeMap.put(PlayType.jczq_bqc_3_1.getValue(),"BQC");
		playTypeMap.put(PlayType.jczq_bqc_4_1.getValue(),"BQC");
		playTypeMap.put(PlayType.jczq_bqc_5_1.getValue(),"BQC");
		playTypeMap.put(PlayType.jczq_bqc_6_1.getValue(),"BQC");
		playTypeMap.put(PlayType.jczq_bqc_7_1.getValue(),"BQC");
		playTypeMap.put(PlayType.jczq_bqc_8_1.getValue(),"BQC");
		playTypeMap.put(PlayType.jczq_bqc_3_3.getValue(),"BQC");
		playTypeMap.put(PlayType.jczq_bqc_3_4.getValue(),"BQC");
		playTypeMap.put(PlayType.jczq_bqc_4_4.getValue(),"BQC");
		playTypeMap.put(PlayType.jczq_bqc_4_5.getValue(),"BQC");
		playTypeMap.put(PlayType.jczq_bqc_4_6.getValue(),"BQC");
		playTypeMap.put(PlayType.jczq_bqc_4_11.getValue(),"BQC");
		
		
		playTypeMap.put(PlayType.jczq_bqc_2_1_dan.getValue(),"BQC");
		playTypeMap.put(PlayType.jczq_bqc_3_1_dan.getValue(),"BQC");
		playTypeMap.put(PlayType.jczq_bqc_4_1_dan.getValue(),"BQC");
		playTypeMap.put(PlayType.jczq_bqc_5_1_dan.getValue(),"BQC");
		playTypeMap.put(PlayType.jczq_bqc_6_1_dan.getValue(),"BQC");
		playTypeMap.put(PlayType.jczq_bqc_7_1_dan.getValue(),"BQC");
		playTypeMap.put(PlayType.jczq_bqc_8_1_dan.getValue(),"BQC");
		playTypeMap.put(PlayType.jczq_bqc_3_3_dan.getValue(),"BQC");
		playTypeMap.put(PlayType.jczq_bqc_3_4_dan.getValue(),"BQC");
		playTypeMap.put(PlayType.jczq_bqc_4_4_dan.getValue(),"BQC");
		playTypeMap.put(PlayType.jczq_bqc_4_5_dan.getValue(),"BQC");
		playTypeMap.put(PlayType.jczq_bqc_4_6_dan.getValue(),"BQC");
		playTypeMap.put(PlayType.jczq_bqc_4_11_dan.getValue(),"BQC");

		playTypeMap.put(PlayType.jczq_spfwrq_1_1.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_2_1.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_3_1.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_4_1.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_5_1.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_6_1.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_7_1.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_8_1.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_3_3.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_3_4.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_4_4.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_4_5.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_4_6.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_4_11.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_5_5.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_5_6.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_5_10.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_5_16.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_5_20.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_5_26.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_6_6.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_6_7.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_6_15.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_6_20.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_6_22.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_6_35.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_6_42.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_6_50.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_6_57.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_7_7.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_7_8.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_7_21.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_7_35.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_7_120.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_8_8.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_8_9.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_8_28.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_8_56.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_8_70.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_8_247.getValue(),"SPF");
		
		playTypeMap.put(PlayType.jczq_spfwrq_2_1_dan.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_3_1_dan.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_4_1_dan.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_5_1_dan.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_6_1_dan.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_7_1_dan.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_8_1_dan.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_3_3_dan.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_3_4_dan.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_4_4_dan.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_4_5_dan.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_4_6_dan.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_4_11_dan.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_5_5_dan.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_5_6_dan.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_5_10_dan.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_5_16_dan.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_5_20_dan.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_5_26_dan.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_6_6_dan.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_6_7_dan.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_6_15_dan.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_6_20_dan.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_6_22_dan.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_6_35_dan.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_6_42_dan.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_6_50_dan.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_6_57_dan.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_7_7_dan.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_7_8_dan.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_7_21_dan.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_7_35_dan.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_7_120_dan.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_8_8_dan.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_8_9_dan.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_8_28_dan.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_8_56_dan.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_8_70_dan.getValue(),"SPF");
		playTypeMap.put(PlayType.jczq_spfwrq_8_247_dan.getValue(),"SPF");
		
		playTypeMap.put(PlayType.jczq_mix_1_1.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_2_1.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_3_1.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_4_1.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_5_1.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_6_1.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_7_1.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_8_1.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_3_3.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_3_4.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_4_4.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_4_5.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_4_6.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_4_11.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_5_5.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_5_6.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_5_10.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_5_16.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_5_20.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_5_26.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_6_6.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_6_7.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_6_15.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_6_20.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_6_22.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_6_35.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_6_42.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_6_50.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_6_57.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_7_7.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_7_8.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_7_21.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_7_35.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_7_120.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_8_8.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_8_9.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_8_28.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_8_56.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_8_70.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_8_247.getValue(),"ZQHH");
	
		playTypeMap.put(PlayType.jczq_mix_2_1_dan.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_3_1_dan.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_4_1_dan.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_5_1_dan.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_6_1_dan.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_7_1_dan.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_8_1_dan.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_3_3_dan.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_3_4_dan.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_4_4_dan.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_4_5_dan.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_4_6_dan.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_4_11_dan.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_5_5_dan.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_5_6_dan.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_5_10_dan.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_5_16_dan.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_5_20_dan.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_5_26_dan.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_6_6_dan.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_6_7_dan.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_6_15_dan.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_6_20_dan.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_6_22_dan.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_6_35_dan.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_6_42_dan.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_6_50_dan.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_6_57_dan.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_7_7_dan.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_7_8_dan.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_7_21_dan.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_7_35_dan.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_7_120_dan.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_8_8_dan.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_8_9_dan.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_8_28_dan.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_8_56_dan.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_8_70_dan.getValue(),"ZQHH");
		playTypeMap.put(PlayType.jczq_mix_8_247_dan.getValue(),"ZQHH");
		
		
		  playMap.put(PlayType.jclq_sf_1_1.getValue(), "1*1");
			playMap.put(PlayType.jclq_sf_2_1.getValue(), "2*1");
			playMap.put(PlayType.jclq_sf_3_1.getValue(), "3*1");
			playMap.put(PlayType.jclq_sf_4_1.getValue(), "4*1");
			playMap.put(PlayType.jclq_sf_5_1.getValue(), "5*1");
			playMap.put(PlayType.jclq_sf_6_1.getValue(), "6*1");
			playMap.put(PlayType.jclq_sf_7_1.getValue(), "7*1");
			playMap.put(PlayType.jclq_sf_8_1.getValue(), "8*1");
			playMap.put(PlayType.jclq_sf_3_3.getValue(), "3*3");
			playMap.put(PlayType.jclq_sf_3_4.getValue(), "3*4");
			playMap.put(PlayType.jclq_sf_4_4.getValue(), "4*4");
			playMap.put(PlayType.jclq_sf_4_5.getValue(), "4*5");
			playMap.put(PlayType.jclq_sf_4_6.getValue(), "4*6");
			playMap.put(PlayType.jclq_sf_4_11.getValue(), "4*11");
			playMap.put(PlayType.jclq_sf_5_5.getValue(), "5*5");
			playMap.put(PlayType.jclq_sf_5_6.getValue(), "5*6");
			playMap.put(PlayType.jclq_sf_5_10.getValue(), "5*10");
			playMap.put(PlayType.jclq_sf_5_16.getValue(), "5*16");
			playMap.put(PlayType.jclq_sf_5_20.getValue(), "5*20");
			playMap.put(PlayType.jclq_sf_5_26.getValue(), "5*26");
			playMap.put(PlayType.jclq_sf_6_6.getValue(), "6*6");
			playMap.put(PlayType.jclq_sf_6_7.getValue(), "6*7");
			playMap.put(PlayType.jclq_sf_6_15.getValue(), "6*15");
			playMap.put(PlayType.jclq_sf_6_20.getValue(), "6*20");
			playMap.put(PlayType.jclq_sf_6_22.getValue(), "6*22");
			playMap.put(PlayType.jclq_sf_6_35.getValue(), "6*35");
			playMap.put(PlayType.jclq_sf_6_42.getValue(), "6*42");
			playMap.put(PlayType.jclq_sf_6_50.getValue(), "6*50");
			playMap.put(PlayType.jclq_sf_6_57.getValue(), "6*57");
			playMap.put(PlayType.jclq_sf_7_7.getValue(), "7*7");
			playMap.put(PlayType.jclq_sf_7_8.getValue(), "7*8");
			playMap.put(PlayType.jclq_sf_7_21.getValue(), "7*21");
			playMap.put(PlayType.jclq_sf_7_35.getValue(), "7*35");
			playMap.put(PlayType.jclq_sf_7_120.getValue(), "7*120");
			playMap.put(PlayType.jclq_sf_8_8.getValue(), "8*8");
			playMap.put(PlayType.jclq_sf_8_9.getValue(), "8*9");
			playMap.put(PlayType.jclq_sf_8_28.getValue(), "8*28");
			playMap.put(PlayType.jclq_sf_8_56.getValue(), "8*56");
			playMap.put(PlayType.jclq_sf_8_70.getValue(), "8*70");
			playMap.put(PlayType.jclq_sf_8_247.getValue(), "8*247");
			
			
			playMap.put(PlayType.jclq_sf_2_1_dan.getValue(), "2*1");
			playMap.put(PlayType.jclq_sf_3_1_dan.getValue(), "3*1");
			playMap.put(PlayType.jclq_sf_4_1_dan.getValue(), "4*1");
			playMap.put(PlayType.jclq_sf_5_1_dan.getValue(), "5*1");
			playMap.put(PlayType.jclq_sf_6_1_dan.getValue(), "6*1");
			playMap.put(PlayType.jclq_sf_7_1_dan.getValue(), "7*1");
			playMap.put(PlayType.jclq_sf_8_1_dan.getValue(), "8*1");
			playMap.put(PlayType.jclq_sf_3_3_dan.getValue(), "3*3");
			playMap.put(PlayType.jclq_sf_3_4_dan.getValue(), "3*4");
			playMap.put(PlayType.jclq_sf_4_4_dan.getValue(), "4*4");
			playMap.put(PlayType.jclq_sf_4_5_dan.getValue(), "4*5");
			playMap.put(PlayType.jclq_sf_4_6_dan.getValue(), "4*6");
			playMap.put(PlayType.jclq_sf_4_11_dan.getValue(), "4*11");
			playMap.put(PlayType.jclq_sf_5_5_dan.getValue(), "5*5");
			playMap.put(PlayType.jclq_sf_5_6_dan.getValue(),"5*6");             
			playMap.put(PlayType.jclq_sf_5_10_dan.getValue(),"5*10");
			playMap.put(PlayType.jclq_sf_5_16_dan.getValue(),"5*16");
			playMap.put(PlayType.jclq_sf_5_20_dan.getValue(),"5*20");
			playMap.put(PlayType.jclq_sf_5_26_dan.getValue(),"5*26");
			playMap.put(PlayType.jclq_sf_6_6_dan.getValue(),"6*6");
			playMap.put(PlayType.jclq_sf_6_7_dan.getValue(),"6*7");
			playMap.put(PlayType.jclq_sf_6_15_dan.getValue(),"6*15");
			playMap.put(PlayType.jclq_sf_6_20_dan.getValue(),"6*20");
			playMap.put(PlayType.jclq_sf_6_22_dan.getValue(),"6*22");
			playMap.put(PlayType.jclq_sf_6_35_dan.getValue(),"6*35");
			playMap.put(PlayType.jclq_sf_6_42_dan.getValue(),"6*42");
			playMap.put(PlayType.jclq_sf_6_50_dan.getValue(),"6*50");
			playMap.put(PlayType.jclq_sf_6_57_dan.getValue(),"6*57");
			playMap.put(PlayType.jclq_sf_7_7_dan.getValue(),"7*7");
			playMap.put(PlayType.jclq_sf_7_8_dan.getValue(),"7*8");
			playMap.put(PlayType.jclq_sf_7_21_dan.getValue(),"7*21");
			playMap.put(PlayType.jclq_sf_7_35_dan.getValue(),"7*35");
			playMap.put(PlayType.jclq_sf_7_120_dan.getValue(),"7*120");             
			playMap.put(PlayType.jclq_sf_8_8_dan.getValue(),"8*8");
			playMap.put(PlayType.jclq_sf_8_9_dan.getValue(),"8*9");
			playMap.put(PlayType.jclq_sf_8_28_dan.getValue(),"8*28");
			playMap.put(PlayType.jclq_sf_8_56_dan.getValue(),"8*56");
			playMap.put(PlayType.jclq_sf_8_70_dan.getValue(),"8*70");
			playMap.put(PlayType.jclq_sf_8_247_dan.getValue(),"8*247");             
			
			
			
			playMap.put(PlayType.jclq_rfsf_1_1.getValue(),"1*1");
			playMap.put(PlayType.jclq_rfsf_2_1.getValue(),"2*1");
			playMap.put(PlayType.jclq_rfsf_3_1.getValue(),"3*1");
			playMap.put(PlayType.jclq_rfsf_4_1.getValue(),"4*1");
			playMap.put(PlayType.jclq_rfsf_5_1.getValue(),"5*1");
			playMap.put(PlayType.jclq_rfsf_6_1.getValue(),"6*1");
			playMap.put(PlayType.jclq_rfsf_7_1.getValue(),"7*1");
			playMap.put(PlayType.jclq_rfsf_8_1.getValue(),"8*1");
			playMap.put(PlayType.jclq_rfsf_3_3.getValue(),"3*3");
			playMap.put(PlayType.jclq_rfsf_3_4.getValue(),"3*4");
			playMap.put(PlayType.jclq_rfsf_4_4.getValue(),"4*4");
			playMap.put(PlayType.jclq_rfsf_4_5.getValue(),"4*5");
			playMap.put(PlayType.jclq_rfsf_4_6.getValue(),"4*6");
			playMap.put(PlayType.jclq_rfsf_4_11.getValue(),"4*11");
			playMap.put(PlayType.jclq_rfsf_5_5.getValue(),"5*5");
			playMap.put(PlayType.jclq_rfsf_5_6.getValue(),"5*6");
			playMap.put(PlayType.jclq_rfsf_5_10.getValue(),"5*10");
			playMap.put(PlayType.jclq_rfsf_5_16.getValue(),"5*16");
			playMap.put(PlayType.jclq_rfsf_5_20.getValue(),"5*20");
			playMap.put(PlayType.jclq_rfsf_5_26.getValue(),"5*26");
			playMap.put(PlayType.jclq_rfsf_6_6.getValue(),"6*6");
			playMap.put(PlayType.jclq_rfsf_6_7.getValue(),"6*7");
			playMap.put(PlayType.jclq_rfsf_6_15.getValue(),"6*15");
			playMap.put(PlayType.jclq_rfsf_6_20.getValue(),"6*20");
			playMap.put(PlayType.jclq_rfsf_6_22.getValue(),"6*22");
			playMap.put(PlayType.jclq_rfsf_6_35.getValue(),"6*35");
			playMap.put(PlayType.jclq_rfsf_6_42.getValue(),"6*42");
			playMap.put(PlayType.jclq_rfsf_6_50.getValue(),"6*50");
			playMap.put(PlayType.jclq_rfsf_6_57.getValue(),"6*57");
			playMap.put(PlayType.jclq_rfsf_7_7.getValue(),"7*7");
			playMap.put(PlayType.jclq_rfsf_7_8.getValue(),"7*8");
			playMap.put(PlayType.jclq_rfsf_7_21.getValue(),"7*21");
			playMap.put(PlayType.jclq_rfsf_7_35.getValue(),"7*35");
			playMap.put(PlayType.jclq_rfsf_7_120.getValue(),"7*120");
			playMap.put(PlayType.jclq_rfsf_8_8.getValue(),"8*8");
			playMap.put(PlayType.jclq_rfsf_8_9.getValue(),"8*9");
			playMap.put(PlayType.jclq_rfsf_8_28.getValue(),"8*28");
			playMap.put(PlayType.jclq_rfsf_8_56.getValue(),"8*56");
			playMap.put(PlayType.jclq_rfsf_8_70.getValue(),"8*70");
			playMap.put(PlayType.jclq_rfsf_8_247.getValue(),"8*247");
			
			
			
			playMap.put(PlayType.jclq_rfsf_1_1_dan.getValue(),"1*1");
			playMap.put(PlayType.jclq_rfsf_2_1_dan.getValue(),"2*1");
			playMap.put(PlayType.jclq_rfsf_3_1_dan.getValue(),"3*1");
			playMap.put(PlayType.jclq_rfsf_4_1_dan.getValue(),"4*1");
			playMap.put(PlayType.jclq_rfsf_5_1_dan.getValue(),"5*1");
			playMap.put(PlayType.jclq_rfsf_6_1_dan.getValue(),"6*1");
			playMap.put(PlayType.jclq_rfsf_7_1_dan.getValue(),"7*1");
			playMap.put(PlayType.jclq_rfsf_8_1_dan.getValue(),"8*1");
			playMap.put(PlayType.jclq_rfsf_3_3_dan.getValue(),"3*3");
			playMap.put(PlayType.jclq_rfsf_3_4_dan.getValue(),"3*4");
			playMap.put(PlayType.jclq_rfsf_4_4_dan.getValue(),"4*4");
			playMap.put(PlayType.jclq_rfsf_4_5_dan.getValue(),"4*5");
			playMap.put(PlayType.jclq_rfsf_4_6_dan.getValue(),"4*6");
			playMap.put(PlayType.jclq_rfsf_4_11_dan.getValue(),"4*11");           
			playMap.put(PlayType.jclq_rfsf_5_5_dan.getValue(),"5*5");
			playMap.put(PlayType.jclq_rfsf_5_6_dan.getValue(),"5*6");
			playMap.put(PlayType.jclq_rfsf_5_10_dan.getValue(),"5*10");
			playMap.put(PlayType.jclq_rfsf_5_16_dan.getValue(),"5*16");
			playMap.put(PlayType.jclq_rfsf_5_20_dan.getValue(),"5*20");
			playMap.put(PlayType.jclq_rfsf_5_26_dan.getValue(),"5*26");
			playMap.put(PlayType.jclq_rfsf_6_6_dan.getValue(),"6*6");           
			playMap.put(PlayType.jclq_rfsf_6_7_dan.getValue(),"6*7");           
			playMap.put(PlayType.jclq_rfsf_6_15_dan.getValue(),"6*15");
			playMap.put(PlayType.jclq_rfsf_6_20_dan.getValue(),"6*20");
			playMap.put(PlayType.jclq_rfsf_6_22_dan.getValue(),"6*22");
			playMap.put(PlayType.jclq_rfsf_6_35_dan.getValue(),"6*35");
			playMap.put(PlayType.jclq_rfsf_6_42_dan.getValue(),"6*42");
			playMap.put(PlayType.jclq_rfsf_6_50_dan.getValue(),"6*50");
			playMap.put(PlayType.jclq_rfsf_6_57_dan.getValue(),"6*57");
			playMap.put(PlayType.jclq_rfsf_7_7_dan.getValue(),"7*7");
			playMap.put(PlayType.jclq_rfsf_7_8_dan.getValue(),"7*8");
			playMap.put(PlayType.jclq_rfsf_7_21_dan.getValue(),"7*21");
			playMap.put(PlayType.jclq_rfsf_7_35_dan.getValue(),"7*35");
			playMap.put(PlayType.jclq_rfsf_7_120_dan.getValue(),"7*120");           
			playMap.put(PlayType.jclq_rfsf_8_8_dan.getValue(),"8*8");
			playMap.put(PlayType.jclq_rfsf_8_9_dan.getValue(),"8*9");
			playMap.put(PlayType.jclq_rfsf_8_28_dan.getValue(),"8*28");
			playMap.put(PlayType.jclq_rfsf_8_56_dan.getValue(),"8*56");
			playMap.put(PlayType.jclq_rfsf_8_70_dan.getValue(),"8*70");
			playMap.put(PlayType.jclq_rfsf_8_247_dan.getValue(),"8*247");           
		
			playMap.put(PlayType.jclq_sfc_1_1.getValue(),"1*1");
			playMap.put(PlayType.jclq_sfc_2_1.getValue(),"2*1");
			playMap.put(PlayType.jclq_sfc_3_1.getValue(),"3*1");
			playMap.put(PlayType.jclq_sfc_4_1.getValue(),"4*1");
			playMap.put(PlayType.jclq_sfc_5_1.getValue(),"5*1");
			playMap.put(PlayType.jclq_sfc_6_1.getValue(),"6*1");
			playMap.put(PlayType.jclq_sfc_7_1.getValue(),"7*1");
			playMap.put(PlayType.jclq_sfc_8_1.getValue(),"8*1");
			playMap.put(PlayType.jclq_sfc_3_3.getValue(),"3*3");
			playMap.put(PlayType.jclq_sfc_3_4.getValue(),"3*4");
			playMap.put(PlayType.jclq_sfc_4_4.getValue(),"4*4");
			playMap.put(PlayType.jclq_sfc_4_5.getValue(),"4*5");
			playMap.put(PlayType.jclq_sfc_4_6.getValue(),"4*6");
			playMap.put(PlayType.jclq_sfc_4_11.getValue(),"4*11");
			
			
			playMap.put(PlayType.jclq_sfc_1_1_dan.getValue(),"1*1");
			playMap.put(PlayType.jclq_sfc_2_1_dan.getValue(),"2*1");
			playMap.put(PlayType.jclq_sfc_3_1_dan.getValue(),"3*1");
			playMap.put(PlayType.jclq_sfc_4_1_dan.getValue(),"4*1");
			playMap.put(PlayType.jclq_sfc_5_1_dan.getValue(),"5*1");
			playMap.put(PlayType.jclq_sfc_6_1_dan.getValue(),"6*1");
			playMap.put(PlayType.jclq_sfc_7_1_dan.getValue(),"7*1");
			playMap.put(PlayType.jclq_sfc_8_1_dan.getValue(),"8*1");
			playMap.put(PlayType.jclq_sfc_3_3_dan.getValue(),"3*3");
			playMap.put(PlayType.jclq_sfc_3_4_dan.getValue(),"3*4");
			playMap.put(PlayType.jclq_sfc_4_4_dan.getValue(),"4*4");
			playMap.put(PlayType.jclq_sfc_4_5_dan.getValue(),"4*5");
			playMap.put(PlayType.jclq_sfc_4_6_dan.getValue(),"4*6");
			playMap.put(PlayType.jclq_sfc_4_11_dan.getValue(),"4*11");
	    
			playMap.put(PlayType.jclq_dxf_1_1.getValue(),"1*1");
			playMap.put(PlayType.jclq_dxf_2_1.getValue(),"2*1");
			playMap.put(PlayType.jclq_dxf_3_1.getValue(),"3*1");
			playMap.put(PlayType.jclq_dxf_4_1.getValue(),"4*1");
			playMap.put(PlayType.jclq_dxf_5_1.getValue(),"5*1");
			playMap.put(PlayType.jclq_dxf_6_1.getValue(),"6*1");
			playMap.put(PlayType.jclq_dxf_7_1.getValue(),"7*1");
			playMap.put(PlayType.jclq_dxf_8_1.getValue(),"8*1");
			playMap.put(PlayType.jclq_dxf_3_3.getValue(),"3*3");
			playMap.put(PlayType.jclq_dxf_3_4.getValue(),"3*4");
			playMap.put(PlayType.jclq_dxf_4_4.getValue(),"4*4");
			playMap.put(PlayType.jclq_dxf_4_5.getValue(),"4*5");
			playMap.put(PlayType.jclq_dxf_4_6.getValue(),"4*6");
			playMap.put(PlayType.jclq_dxf_4_11.getValue(),"4*11");
			playMap.put(PlayType.jclq_dxf_5_5.getValue(),"5*5");
			playMap.put(PlayType.jclq_dxf_5_6.getValue(),"5*6");
			playMap.put(PlayType.jclq_dxf_5_10.getValue(),"5*10");
			playMap.put(PlayType.jclq_dxf_5_16.getValue(),"5*16");
			playMap.put(PlayType.jclq_dxf_5_20.getValue(),"5*20");
			playMap.put(PlayType.jclq_dxf_5_26.getValue(),"5*26");
			playMap.put(PlayType.jclq_dxf_6_6.getValue(),"6*6");
			playMap.put(PlayType.jclq_dxf_6_7.getValue(),"6*7");
			playMap.put(PlayType.jclq_dxf_6_15.getValue(),"6*15");
			playMap.put(PlayType.jclq_dxf_6_20.getValue(),"6*20");
			playMap.put(PlayType.jclq_dxf_6_22.getValue(),"6*22");
			playMap.put(PlayType.jclq_dxf_6_35.getValue(),"6*35");
			playMap.put(PlayType.jclq_dxf_6_42.getValue(),"6*42");
			playMap.put(PlayType.jclq_dxf_6_50.getValue(),"6*50");
			playMap.put(PlayType.jclq_dxf_6_57.getValue(),"6*57");
			playMap.put(PlayType.jclq_dxf_7_7.getValue(),"7*7");
			playMap.put(PlayType.jclq_dxf_7_8.getValue(),"7*8");
			playMap.put(PlayType.jclq_dxf_7_21.getValue(),"7*21");
			playMap.put(PlayType.jclq_dxf_7_35.getValue(),"7*35");
			playMap.put(PlayType.jclq_dxf_7_120.getValue(),"7*120");
			playMap.put(PlayType.jclq_dxf_8_8.getValue(),"8*8");
			playMap.put(PlayType.jclq_dxf_8_9.getValue(),"8*9");
			playMap.put(PlayType.jclq_dxf_8_28.getValue(),"8*28");
			playMap.put(PlayType.jclq_dxf_8_56.getValue(),"8*56");
			playMap.put(PlayType.jclq_dxf_8_70.getValue(),"8*70");
			playMap.put(PlayType.jclq_dxf_8_247.getValue(),"8*247");
			
			
			
			playMap.put(PlayType.jclq_dxf_1_1_dan.getValue(),"1*1");
			playMap.put(PlayType.jclq_dxf_2_1_dan.getValue(),"2*1");
			playMap.put(PlayType.jclq_dxf_3_1_dan.getValue(),"3*1");
		     playMap.put(PlayType.jclq_dxf_4_1_dan.getValue(),"4*1");
			playMap.put(PlayType.jclq_dxf_5_1_dan.getValue(),"5*1");
			playMap.put(PlayType.jclq_dxf_6_1_dan.getValue(),"6*1");
			playMap.put(PlayType.jclq_dxf_7_1_dan.getValue(),"7*1");
			playMap.put(PlayType.jclq_dxf_8_1_dan.getValue(),"8*1");
			playMap.put(PlayType.jclq_dxf_3_3_dan.getValue(),"3*3");
			playMap.put(PlayType.jclq_dxf_3_4_dan.getValue(),"3*4");
			playMap.put(PlayType.jclq_dxf_4_4_dan.getValue(),"4*4");
			playMap.put(PlayType.jclq_dxf_4_5_dan.getValue(),"4*5");
			playMap.put(PlayType.jclq_dxf_4_6_dan.getValue(),"4*6");
			playMap.put(PlayType.jclq_dxf_4_11_dan.getValue(),"4*11");
			playMap.put(PlayType.jclq_dxf_5_5_dan.getValue(),"5*5");
			playMap.put(PlayType.jclq_dxf_5_6_dan.getValue(),"5*6");
			playMap.put(PlayType.jclq_dxf_5_10_dan.getValue(),"5*10");
			playMap.put(PlayType.jclq_dxf_5_16_dan.getValue(),"5*16");
			playMap.put(PlayType.jclq_dxf_5_20_dan.getValue(),"5*20");
			playMap.put(PlayType.jclq_dxf_5_26_dan.getValue(),"5*26");
			playMap.put(PlayType.jclq_dxf_6_6_dan.getValue(),"6*6");
			playMap.put(PlayType.jclq_dxf_6_7_dan.getValue(),"6*7");
			playMap.put(PlayType.jclq_dxf_6_15_dan.getValue(),"6*15");
			playMap.put(PlayType.jclq_dxf_6_20_dan.getValue(),"6*20");
			playMap.put(PlayType.jclq_dxf_6_22_dan.getValue(),"6*22");
			playMap.put(PlayType.jclq_dxf_6_35_dan.getValue(),"6*35");
			playMap.put(PlayType.jclq_dxf_6_42_dan.getValue(),"6*42");
			playMap.put(PlayType.jclq_dxf_6_50_dan.getValue(),"6*50");
			playMap.put(PlayType.jclq_dxf_6_57_dan.getValue(),"6*57");
			playMap.put(PlayType.jclq_dxf_7_7_dan.getValue(),"7*7");
			playMap.put(PlayType.jclq_dxf_7_8_dan.getValue(),"7*8");
			playMap.put(PlayType.jclq_dxf_7_21_dan.getValue(),"7*21");
			playMap.put(PlayType.jclq_dxf_7_35_dan.getValue(),"7*35");
			playMap.put(PlayType.jclq_dxf_7_120_dan.getValue(),"7*120");
			playMap.put(PlayType.jclq_dxf_8_8_dan.getValue(),"8*8");
			playMap.put(PlayType.jclq_dxf_8_9_dan.getValue(),"8*9");
			playMap.put(PlayType.jclq_dxf_8_28_dan.getValue(),"8*28");
			playMap.put(PlayType.jclq_dxf_8_56_dan.getValue(),"8*56");
			playMap.put(PlayType.jclq_dxf_8_70_dan.getValue(),"8*70");
			playMap.put(PlayType.jclq_dxf_8_247_dan.getValue(),"8*247");

			playMap.put(PlayType.jclq_mix_1_1.getValue(),"1*1");
			playMap.put(PlayType.jclq_mix_2_1.getValue(),"2*1");
			playMap.put(PlayType.jclq_mix_3_1.getValue(),"3*1");
			playMap.put(PlayType.jclq_mix_4_1.getValue(),"4*1");
			playMap.put(PlayType.jclq_mix_5_1.getValue(),"5*1");
			playMap.put(PlayType.jclq_mix_6_1.getValue(),"6*1");
			playMap.put(PlayType.jclq_mix_7_1.getValue(),"7*1");
			playMap.put(PlayType.jclq_mix_8_1.getValue(),"8*1");
			playMap.put(PlayType.jclq_mix_3_3.getValue(),"3*3");
			playMap.put(PlayType.jclq_mix_3_4.getValue(),"3*4");
			playMap.put(PlayType.jclq_mix_4_4.getValue(),"4*4");
			playMap.put(PlayType.jclq_mix_4_5.getValue(),"4*5");
			playMap.put(PlayType.jclq_mix_4_6.getValue(),"4*6");
			playMap.put(PlayType.jclq_mix_4_11.getValue(),"4*11");
			playMap.put(PlayType.jclq_mix_5_5.getValue(),"5*5");
			playMap.put(PlayType.jclq_mix_5_6.getValue(),"5*6");
			playMap.put(PlayType.jclq_mix_5_10.getValue(),"5*10");
			playMap.put(PlayType.jclq_mix_5_16.getValue(),"5*16");
			playMap.put(PlayType.jclq_mix_5_20.getValue(),"5*20");
			playMap.put(PlayType.jclq_mix_5_26.getValue(),"5*26");
			playMap.put(PlayType.jclq_mix_6_6.getValue(),"6*6");
			playMap.put(PlayType.jclq_mix_6_7.getValue(),"6*7");
			playMap.put(PlayType.jclq_mix_6_15.getValue(),"6*15");
			playMap.put(PlayType.jclq_mix_6_20.getValue(),"6*20");
			playMap.put(PlayType.jclq_mix_6_22.getValue(),"6*22");
			playMap.put(PlayType.jclq_mix_6_35.getValue(),"6*35");
			playMap.put(PlayType.jclq_mix_6_42.getValue(),"6*42");
			playMap.put(PlayType.jclq_mix_6_50.getValue(),"6*50");
			playMap.put(PlayType.jclq_mix_6_57.getValue(),"6*57");
			playMap.put(PlayType.jclq_mix_7_7.getValue(),"7*7");
			playMap.put(PlayType.jclq_mix_7_8.getValue(),"7*8");
			playMap.put(PlayType.jclq_mix_7_21.getValue(),"7*21");
			playMap.put(PlayType.jclq_mix_7_35.getValue(),"7*35");
			playMap.put(PlayType.jclq_mix_7_120.getValue(),"7*120");
			playMap.put(PlayType.jclq_mix_8_8.getValue(),"8*8");
			playMap.put(PlayType.jclq_mix_8_9.getValue(),"8*9");
			playMap.put(PlayType.jclq_mix_8_28.getValue(),"8*28");
			playMap.put(PlayType.jclq_mix_8_56.getValue(),"8*56");
			playMap.put(PlayType.jclq_mix_8_70.getValue(),"8*70");
			playMap.put(PlayType.jclq_mix_8_247.getValue(),"8*247");
		
			playMap.put(PlayType.jclq_mix_2_1_dan.getValue(),"2*1");
			playMap.put(PlayType.jclq_mix_3_1_dan.getValue(),"3*1");
			playMap.put(PlayType.jclq_mix_4_1_dan.getValue(),"4*1");
			playMap.put(PlayType.jclq_mix_5_1_dan.getValue(),"5*1");
			playMap.put(PlayType.jclq_mix_6_1_dan.getValue(),"6*1");
			playMap.put(PlayType.jclq_mix_7_1_dan.getValue(),"7*1");
			playMap.put(PlayType.jclq_mix_8_1_dan.getValue(),"8*1");
			playMap.put(PlayType.jclq_mix_3_3_dan.getValue(),"3*3");
			playMap.put(PlayType.jclq_mix_3_4_dan.getValue(),"3*4");
			playMap.put(PlayType.jclq_mix_4_4_dan.getValue(),"4*4");
			playMap.put(PlayType.jclq_mix_4_5_dan.getValue(),"4*5");
			playMap.put(PlayType.jclq_mix_4_6_dan.getValue(),"4*6");
			playMap.put(PlayType.jclq_mix_4_11_dan.getValue(),"4*11");
			playMap.put(PlayType.jclq_mix_5_5_dan.getValue(),"5*5");
			playMap.put(PlayType.jclq_mix_5_6_dan.getValue(),"5*6");
			playMap.put(PlayType.jclq_mix_5_10_dan.getValue(),"5*10");
			playMap.put(PlayType.jclq_mix_5_16_dan.getValue(),"5*16");
			playMap.put(PlayType.jclq_mix_5_20_dan.getValue(),"5*20");
			playMap.put(PlayType.jclq_mix_5_26_dan.getValue(),"5*26");
			playMap.put(PlayType.jclq_mix_6_6_dan.getValue(),"6*6");
			playMap.put(PlayType.jclq_mix_6_7_dan.getValue(),"6*7");
			playMap.put(PlayType.jclq_mix_6_15_dan.getValue(),"6*15");
			playMap.put(PlayType.jclq_mix_6_20_dan.getValue(),"6*20");
			playMap.put(PlayType.jclq_mix_6_22_dan.getValue(),"6*22");
			playMap.put(PlayType.jclq_mix_6_35_dan.getValue(),"6*35");
			playMap.put(PlayType.jclq_mix_6_42_dan.getValue(),"6*42");
			playMap.put(PlayType.jclq_mix_6_50_dan.getValue(),"6*50");
			playMap.put(PlayType.jclq_mix_6_57_dan.getValue(),"6*57");
			playMap.put(PlayType.jclq_mix_7_7_dan.getValue(),"7*7");
			playMap.put(PlayType.jclq_mix_7_8_dan.getValue(),"7*8");
			playMap.put(PlayType.jclq_mix_7_21_dan.getValue(),"7*21");
			playMap.put(PlayType.jclq_mix_7_35_dan.getValue(),"7*35");
			playMap.put(PlayType.jclq_mix_7_120_dan.getValue(),"7*120");
			playMap.put(PlayType.jclq_mix_8_8_dan.getValue(),"8*8");
			playMap.put(PlayType.jclq_mix_8_9_dan.getValue(),"8*9");
			playMap.put(PlayType.jclq_mix_8_28_dan.getValue(),"8*28");
			playMap.put(PlayType.jclq_mix_8_56_dan.getValue(),"8*56");
			playMap.put(PlayType.jclq_mix_8_70_dan.getValue(),"8*70");
			playMap.put(PlayType.jclq_mix_8_247_dan.getValue(),"8*247");
			
			
			
			playMap.put(PlayType.jczq_spf_1_1.getValue(),"1*1");
			playMap.put(PlayType.jczq_spf_2_1.getValue(),"2*1");
			playMap.put(PlayType.jczq_spf_3_1.getValue(),"3*1");
			playMap.put(PlayType.jczq_spf_4_1.getValue(),"4*1");
			playMap.put(PlayType.jczq_spf_5_1.getValue(),"5*1");
			playMap.put(PlayType.jczq_spf_6_1.getValue(),"6*1");
			playMap.put(PlayType.jczq_spf_7_1.getValue(),"7*1");
			playMap.put(PlayType.jczq_spf_8_1.getValue(),"8*1");
			playMap.put(PlayType.jczq_spf_3_3.getValue(),"3*3");
			playMap.put(PlayType.jczq_spf_3_4.getValue(),"3*4");
			playMap.put(PlayType.jczq_spf_4_4.getValue(),"4*4");
			playMap.put(PlayType.jczq_spf_4_5.getValue(),"4*5");
			playMap.put(PlayType.jczq_spf_4_6.getValue(),"4*6");
			playMap.put(PlayType.jczq_spf_4_11.getValue(),"4*11");
			playMap.put(PlayType.jczq_spf_5_5.getValue(),"5*5"); 
			playMap.put(PlayType.jczq_spf_5_6.getValue(),"5*6"); 
			playMap.put(PlayType.jczq_spf_5_10.getValue(),"5*10");  
			playMap.put(PlayType.jczq_spf_5_16.getValue(),"5*16");
			playMap.put(PlayType.jczq_spf_5_20.getValue(),"5*20");
			playMap.put(PlayType.jczq_spf_5_26.getValue(),"5*26");
			playMap.put(PlayType.jczq_spf_6_6.getValue(),"6*6");
			playMap.put(PlayType.jczq_spf_6_7.getValue(),"6*7");
			playMap.put(PlayType.jczq_spf_6_15.getValue(),"6*15");
			playMap.put(PlayType.jczq_spf_6_20.getValue(),"6*20");
			playMap.put(PlayType.jczq_spf_6_22.getValue(),"6*22");
			playMap.put(PlayType.jczq_spf_6_35.getValue(),"6*35");
			playMap.put(PlayType.jczq_spf_6_42.getValue(),"6*42");
			playMap.put(PlayType.jczq_spf_6_50.getValue(),"6*50");
			playMap.put(PlayType.jczq_spf_6_57.getValue(),"6*57");
			playMap.put(PlayType.jczq_spf_7_7.getValue(),"7*7");
			playMap.put(PlayType.jczq_spf_7_8.getValue(),"7*8");
			playMap.put(PlayType.jczq_spf_7_21.getValue(),"7*21");
			playMap.put(PlayType.jczq_spf_7_35.getValue(),"7*35");
			playMap.put(PlayType.jczq_spf_7_120.getValue(),"7*120");  
			playMap.put(PlayType.jczq_spf_8_8.getValue(),"8*8");
			playMap.put(PlayType.jczq_spf_8_9.getValue(),"8*9");
			playMap.put(PlayType.jczq_spf_8_28.getValue(),"8*28");
			playMap.put(PlayType.jczq_spf_8_56.getValue(),"8*56");
			playMap.put(PlayType.jczq_spf_8_70.getValue(),"8*70");
			playMap.put(PlayType.jczq_spf_8_247.getValue(),"8*247"); 				 
			
			
			playMap.put(PlayType.jczq_spf_2_1_dan.getValue(),"2*1");
			playMap.put(PlayType.jczq_spf_3_1_dan.getValue(),"3*1");
			playMap.put(PlayType.jczq_spf_4_1_dan.getValue(),"4*1");
			playMap.put(PlayType.jczq_spf_5_1_dan.getValue(),"5*1");
			playMap.put(PlayType.jczq_spf_6_1_dan.getValue(),"6*1");
			playMap.put(PlayType.jczq_spf_7_1_dan.getValue(),"7*1");
			playMap.put(PlayType.jczq_spf_8_1_dan.getValue(),"8*1");
			playMap.put(PlayType.jczq_spf_3_3_dan.getValue(),"3*3");
			playMap.put(PlayType.jczq_spf_3_4_dan.getValue(),"3*4");
			playMap.put(PlayType.jczq_spf_4_4_dan.getValue(),"4*4");
			playMap.put(PlayType.jczq_spf_4_5_dan.getValue(),"4*5");
			playMap.put(PlayType.jczq_spf_4_6_dan.getValue(),"4*6");
			playMap.put(PlayType.jczq_spf_4_11_dan.getValue(),"4*11");
			playMap.put(PlayType.jczq_spf_5_5_dan.getValue(),"5*5");
			playMap.put(PlayType.jczq_spf_5_6_dan.getValue(),"5*6");
			playMap.put(PlayType.jczq_spf_5_10_dan.getValue(),"5*10");
			playMap.put(PlayType.jczq_spf_5_16_dan.getValue(),"5*16");
			playMap.put(PlayType.jczq_spf_5_20_dan.getValue(),"5*20");
			playMap.put(PlayType.jczq_spf_5_26_dan.getValue(),"5*26");
			playMap.put(PlayType.jczq_spf_6_6_dan.getValue(),"6*6");
			playMap.put(PlayType.jczq_spf_6_7_dan.getValue(),"6*7");
			playMap.put(PlayType.jczq_spf_6_15_dan.getValue(),"6*15");
			playMap.put(PlayType.jczq_spf_6_20_dan.getValue(),"6*20");
			playMap.put(PlayType.jczq_spf_6_22_dan.getValue(),"6*22");
			playMap.put(PlayType.jczq_spf_6_35_dan.getValue(),"6*35");
			playMap.put(PlayType.jczq_spf_6_42_dan.getValue(),"6*42");
			playMap.put(PlayType.jczq_spf_6_50_dan.getValue(),"6*50");
			playMap.put(PlayType.jczq_spf_6_57_dan.getValue(),"6*57");
			playMap.put(PlayType.jczq_spf_7_7_dan.getValue(),"7*7");
			playMap.put(PlayType.jczq_spf_7_8_dan.getValue(),"7*8");
			playMap.put(PlayType.jczq_spf_7_21_dan.getValue(),"7*21");
			playMap.put(PlayType.jczq_spf_7_35_dan.getValue(),"7*35");
			playMap.put(PlayType.jczq_spf_7_120_dan.getValue(),"7*120");
			playMap.put(PlayType.jczq_spf_8_8_dan.getValue(),"8*8");
			playMap.put(PlayType.jczq_spf_8_9_dan.getValue(),"8*9");
			playMap.put(PlayType.jczq_spf_8_28_dan.getValue(),"8*28");
			playMap.put(PlayType.jczq_spf_8_56_dan.getValue(),"8*56");
			playMap.put(PlayType.jczq_spf_8_70_dan.getValue(),"8*70");
			playMap.put(PlayType.jczq_spf_8_247_dan.getValue(),"8*247");
			
			playMap.put(PlayType.jczq_bf_1_1.getValue(),"1*1");
			playMap.put(PlayType.jczq_bf_2_1.getValue(),"2*1");
			playMap.put(PlayType.jczq_bf_3_1.getValue(),"3*1");
			playMap.put(PlayType.jczq_bf_4_1.getValue(),"4*1");
			playMap.put(PlayType.jczq_bf_5_1.getValue(),"5*1");
			playMap.put(PlayType.jczq_bf_6_1.getValue(),"6*1");
			playMap.put(PlayType.jczq_bf_7_1.getValue(),"7*1");
			playMap.put(PlayType.jczq_bf_8_1.getValue(),"8*1");
			playMap.put(PlayType.jczq_bf_3_3.getValue(),"3*3");
			playMap.put(PlayType.jczq_bf_3_4.getValue(),"3*4");
			playMap.put(PlayType.jczq_bf_4_4.getValue(),"4*4");
			playMap.put(PlayType.jczq_bf_4_5.getValue(),"4*5");
			playMap.put(PlayType.jczq_bf_4_6.getValue(),"4*6");
			playMap.put(PlayType.jczq_bf_4_11.getValue(),"4*11");
			
			
			playMap.put(PlayType.jczq_bf_2_1_dan.getValue(),"2*1");
			playMap.put(PlayType.jczq_bf_3_1_dan.getValue(),"3*1");
			playMap.put(PlayType.jczq_bf_4_1_dan.getValue(),"4*1");
			playMap.put(PlayType.jczq_bf_5_1_dan.getValue(),"5*1");
			playMap.put(PlayType.jczq_bf_6_1_dan.getValue(),"6*1");
			playMap.put(PlayType.jczq_bf_7_1_dan.getValue(),"7*1");
			playMap.put(PlayType.jczq_bf_8_1_dan.getValue(),"8*1");
			playMap.put(PlayType.jczq_bf_3_3_dan.getValue(),"3*3");
			playMap.put(PlayType.jczq_bf_3_4_dan.getValue(),"3*4");
			playMap.put(PlayType.jczq_bf_4_4_dan.getValue(),"4*4");
			playMap.put(PlayType.jczq_bf_4_5_dan.getValue(),"4*5");
			playMap.put(PlayType.jczq_bf_4_6_dan.getValue(),"4*6");
			playMap.put(PlayType.jczq_bf_4_11_dan.getValue(),"4*11");
			
			playMap.put(PlayType.jczq_jqs_1_1.getValue(),"1*1");
			playMap.put(PlayType.jczq_jqs_2_1.getValue(),"2*1");
			playMap.put(PlayType.jczq_jqs_3_1.getValue(),"3*1");
			playMap.put(PlayType.jczq_jqs_4_1.getValue(),"4*1");
			playMap.put(PlayType.jczq_jqs_5_1.getValue(),"5*1");
			playMap.put(PlayType.jczq_jqs_6_1.getValue(),"6*1");
			playMap.put(PlayType.jczq_jqs_7_1.getValue(),"7*1");
			playMap.put(PlayType.jczq_jqs_8_1.getValue(),"8*1");
			playMap.put(PlayType.jczq_jqs_3_3.getValue(),"3*3");
			playMap.put(PlayType.jczq_jqs_3_4.getValue(),"3*4");
			playMap.put(PlayType.jczq_jqs_4_4.getValue(),"4*4");
			playMap.put(PlayType.jczq_jqs_4_5.getValue(),"4*5");
			playMap.put(PlayType.jczq_jqs_4_6.getValue(),"4*6");
			playMap.put(PlayType.jczq_jqs_4_11.getValue(),"4*11");
			playMap.put(PlayType.jczq_jqs_5_5.getValue(),"5*5");
			playMap.put(PlayType.jczq_jqs_5_6.getValue(),"5*6");
			playMap.put(PlayType.jczq_jqs_5_10.getValue(),"5*10");
			playMap.put(PlayType.jczq_jqs_5_16.getValue(),"5*16");
			playMap.put(PlayType.jczq_jqs_5_20.getValue(),"5*20");
			playMap.put(PlayType.jczq_jqs_5_26.getValue(),"5*26");
			playMap.put(PlayType.jczq_jqs_6_6.getValue(),"6*6");
			playMap.put(PlayType.jczq_jqs_6_7.getValue(),"6*7");
			playMap.put(PlayType.jczq_jqs_6_15.getValue(),"6*15");
			playMap.put(PlayType.jczq_jqs_6_20.getValue(),"6*20");
			playMap.put(PlayType.jczq_jqs_6_22.getValue(),"6*22");
			playMap.put(PlayType.jczq_jqs_6_35.getValue(),"6*35");
			playMap.put(PlayType.jczq_jqs_6_42.getValue(),"6*42");
			playMap.put(PlayType.jczq_jqs_6_50.getValue(),"6*50");
			playMap.put(PlayType.jczq_jqs_6_57.getValue(),"6*57");
		
			playMap.put(PlayType.jczq_jqs_2_1_dan.getValue(),"2*1");
			playMap.put(PlayType.jczq_jqs_3_1_dan.getValue(),"3*1");
			playMap.put(PlayType.jczq_jqs_4_1_dan.getValue(),"4*1");
			playMap.put(PlayType.jczq_jqs_5_1_dan.getValue(),"5*1");
			playMap.put(PlayType.jczq_jqs_6_1_dan.getValue(),"6*1");
			playMap.put(PlayType.jczq_jqs_7_1_dan.getValue(),"7*1");
			playMap.put(PlayType.jczq_jqs_8_1_dan.getValue(),"8*1");
			playMap.put(PlayType.jczq_jqs_3_3_dan.getValue(),"3*3");
			playMap.put(PlayType.jczq_jqs_3_4_dan.getValue(),"3*4");
			playMap.put(PlayType.jczq_jqs_4_4_dan.getValue(),"4*4");
			playMap.put(PlayType.jczq_jqs_4_5_dan.getValue(),"4*5");
			playMap.put(PlayType.jczq_jqs_4_6_dan.getValue(),"4*6");
			playMap.put(PlayType.jczq_jqs_4_11_dan.getValue(),"4*11");
			playMap.put(PlayType.jczq_jqs_5_5_dan.getValue(),"5*5");
			playMap.put(PlayType.jczq_jqs_5_6_dan.getValue(),"5*6");
			playMap.put(PlayType.jczq_jqs_5_10_dan.getValue(),"5*10");
			playMap.put(PlayType.jczq_jqs_5_16_dan.getValue(),"5*16");
			playMap.put(PlayType.jczq_jqs_5_20_dan.getValue(),"5*20");
			playMap.put(PlayType.jczq_jqs_5_26_dan.getValue(),"5*26");
			playMap.put(PlayType.jczq_jqs_6_6_dan.getValue(),"6*6");
			playMap.put(PlayType.jczq_jqs_6_7_dan.getValue(),"6*7");
			playMap.put(PlayType.jczq_jqs_6_15_dan.getValue(),"6*15");
			playMap.put(PlayType.jczq_jqs_6_20_dan.getValue(),"6*20");
			playMap.put(PlayType.jczq_jqs_6_22_dan.getValue(),"6*22");
			playMap.put(PlayType.jczq_jqs_6_35_dan.getValue(),"6*35");
			playMap.put(PlayType.jczq_jqs_6_42_dan.getValue(),"6*42");
			playMap.put(PlayType.jczq_jqs_6_50_dan.getValue(),"6*50");
			playMap.put(PlayType.jczq_jqs_6_57_dan.getValue(),"6*57");
		
			playMap.put(PlayType.jczq_bqc_1_1.getValue(),"1*1");
			playMap.put(PlayType.jczq_bqc_2_1.getValue(),"2*1");
			playMap.put(PlayType.jczq_bqc_3_1.getValue(),"3*1");
			playMap.put(PlayType.jczq_bqc_4_1.getValue(),"4*1");
			playMap.put(PlayType.jczq_bqc_5_1.getValue(),"5*1");
			playMap.put(PlayType.jczq_bqc_6_1.getValue(),"6*1");
			playMap.put(PlayType.jczq_bqc_7_1.getValue(),"7*1");
			playMap.put(PlayType.jczq_bqc_8_1.getValue(),"8*1");
			playMap.put(PlayType.jczq_bqc_3_3.getValue(),"3*3");
			playMap.put(PlayType.jczq_bqc_3_4.getValue(),"3*4");
			playMap.put(PlayType.jczq_bqc_4_4.getValue(),"4*4");
			playMap.put(PlayType.jczq_bqc_4_5.getValue(),"4*5");
			playMap.put(PlayType.jczq_bqc_4_6.getValue(),"4*6");
			playMap.put(PlayType.jczq_bqc_4_11.getValue(),"4*11");
			
			
			playMap.put(PlayType.jczq_bqc_2_1_dan.getValue(),"2*1");
			playMap.put(PlayType.jczq_bqc_3_1_dan.getValue(),"3*1");
			playMap.put(PlayType.jczq_bqc_4_1_dan.getValue(),"4*1");
			playMap.put(PlayType.jczq_bqc_5_1_dan.getValue(),"5*1");
			playMap.put(PlayType.jczq_bqc_6_1_dan.getValue(),"6*1");
			playMap.put(PlayType.jczq_bqc_7_1_dan.getValue(),"7*1");
			playMap.put(PlayType.jczq_bqc_8_1_dan.getValue(),"8*1");
			playMap.put(PlayType.jczq_bqc_3_3_dan.getValue(),"3*3");
			playMap.put(PlayType.jczq_bqc_3_4_dan.getValue(),"3*4");
			playMap.put(PlayType.jczq_bqc_4_4_dan.getValue(),"4*4");
			playMap.put(PlayType.jczq_bqc_4_5_dan.getValue(),"4*5");
			playMap.put(PlayType.jczq_bqc_4_6_dan.getValue(),"4*6");
			playMap.put(PlayType.jczq_bqc_4_11_dan.getValue(),"4*11");

			playMap.put(PlayType.jczq_spfwrq_1_1.getValue(),"1*1");
			playMap.put(PlayType.jczq_spfwrq_2_1.getValue(),"2*1");
			playMap.put(PlayType.jczq_spfwrq_3_1.getValue(),"3*1");
			playMap.put(PlayType.jczq_spfwrq_4_1.getValue(),"4*1");
			playMap.put(PlayType.jczq_spfwrq_5_1.getValue(),"5*1");
			playMap.put(PlayType.jczq_spfwrq_6_1.getValue(),"6*1");
			playMap.put(PlayType.jczq_spfwrq_7_1.getValue(),"7*1");
			playMap.put(PlayType.jczq_spfwrq_8_1.getValue(),"8*1");
			playMap.put(PlayType.jczq_spfwrq_3_3.getValue(),"3*3");
			playMap.put(PlayType.jczq_spfwrq_3_4.getValue(),"3*4");
			playMap.put(PlayType.jczq_spfwrq_4_4.getValue(),"4*4");
			playMap.put(PlayType.jczq_spfwrq_4_5.getValue(),"4*5");
			playMap.put(PlayType.jczq_spfwrq_4_6.getValue(),"4*6");
			playMap.put(PlayType.jczq_spfwrq_4_11.getValue(),"4*11");
			playMap.put(PlayType.jczq_spfwrq_5_5.getValue(),"5*5");
			playMap.put(PlayType.jczq_spfwrq_5_6.getValue(),"5*6");
			playMap.put(PlayType.jczq_spfwrq_5_10.getValue(),"5*10");
			playMap.put(PlayType.jczq_spfwrq_5_16.getValue(),"5*16");
			playMap.put(PlayType.jczq_spfwrq_5_20.getValue(),"5*20");
			playMap.put(PlayType.jczq_spfwrq_5_26.getValue(),"5*26");
			playMap.put(PlayType.jczq_spfwrq_6_6.getValue(),"6*6");
			playMap.put(PlayType.jczq_spfwrq_6_7.getValue(),"6*7");
			playMap.put(PlayType.jczq_spfwrq_6_15.getValue(),"6*15");
			playMap.put(PlayType.jczq_spfwrq_6_20.getValue(),"6*20");
			playMap.put(PlayType.jczq_spfwrq_6_22.getValue(),"6*22");
			playMap.put(PlayType.jczq_spfwrq_6_35.getValue(),"6*35");
			playMap.put(PlayType.jczq_spfwrq_6_42.getValue(),"6*42");
			playMap.put(PlayType.jczq_spfwrq_6_50.getValue(),"6*50");
			playMap.put(PlayType.jczq_spfwrq_6_57.getValue(),"6*57");
			playMap.put(PlayType.jczq_spfwrq_7_7.getValue(),"7*7");
			playMap.put(PlayType.jczq_spfwrq_7_8.getValue(),"7*8");
			playMap.put(PlayType.jczq_spfwrq_7_21.getValue(),"7*21");
			playMap.put(PlayType.jczq_spfwrq_7_35.getValue(),"7*35");
			playMap.put(PlayType.jczq_spfwrq_7_120.getValue(),"7*120");
			playMap.put(PlayType.jczq_spfwrq_8_8.getValue(),"8*8");
			playMap.put(PlayType.jczq_spfwrq_8_9.getValue(),"8*9");
			playMap.put(PlayType.jczq_spfwrq_8_28.getValue(),"8*28");
			playMap.put(PlayType.jczq_spfwrq_8_56.getValue(),"8*56");
			playMap.put(PlayType.jczq_spfwrq_8_70.getValue(),"8*70");
			playMap.put(PlayType.jczq_spfwrq_8_247.getValue(),"8*247");
			
			playMap.put(PlayType.jczq_spfwrq_2_1_dan.getValue(),"2*1");
			playMap.put(PlayType.jczq_spfwrq_3_1_dan.getValue(),"3*1");
			playMap.put(PlayType.jczq_spfwrq_4_1_dan.getValue(),"4*1");
			playMap.put(PlayType.jczq_spfwrq_5_1_dan.getValue(),"5*1");
			playMap.put(PlayType.jczq_spfwrq_6_1_dan.getValue(),"6*1");
			playMap.put(PlayType.jczq_spfwrq_7_1_dan.getValue(),"7*1");
			playMap.put(PlayType.jczq_spfwrq_8_1_dan.getValue(),"8*1");
			playMap.put(PlayType.jczq_spfwrq_3_3_dan.getValue(),"3*3");
			playMap.put(PlayType.jczq_spfwrq_3_4_dan.getValue(),"3*4");
			playMap.put(PlayType.jczq_spfwrq_4_4_dan.getValue(),"4*4");
			playMap.put(PlayType.jczq_spfwrq_4_5_dan.getValue(),"4*5");
			playMap.put(PlayType.jczq_spfwrq_4_6_dan.getValue(),"4*6");
			playMap.put(PlayType.jczq_spfwrq_4_11_dan.getValue(),"4*11");
			playMap.put(PlayType.jczq_spfwrq_5_5_dan.getValue(),"5*5");
			playMap.put(PlayType.jczq_spfwrq_5_6_dan.getValue(),"5*6");
			playMap.put(PlayType.jczq_spfwrq_5_10_dan.getValue(),"5*10");
			playMap.put(PlayType.jczq_spfwrq_5_16_dan.getValue(),"5*16");
			playMap.put(PlayType.jczq_spfwrq_5_20_dan.getValue(),"5*20");
			playMap.put(PlayType.jczq_spfwrq_5_26_dan.getValue(),"5*26");
			playMap.put(PlayType.jczq_spfwrq_6_6_dan.getValue(),"6*6");
			playMap.put(PlayType.jczq_spfwrq_6_7_dan.getValue(),"6*7");
			playMap.put(PlayType.jczq_spfwrq_6_15_dan.getValue(),"6*15");
			playMap.put(PlayType.jczq_spfwrq_6_20_dan.getValue(),"6*20");
			playMap.put(PlayType.jczq_spfwrq_6_22_dan.getValue(),"6*22");
			playMap.put(PlayType.jczq_spfwrq_6_35_dan.getValue(),"6*35");
			playMap.put(PlayType.jczq_spfwrq_6_42_dan.getValue(),"6*42");
			playMap.put(PlayType.jczq_spfwrq_6_50_dan.getValue(),"6*50");
			playMap.put(PlayType.jczq_spfwrq_6_57_dan.getValue(),"6*57");
			playMap.put(PlayType.jczq_spfwrq_7_7_dan.getValue(),"7*7");
			playMap.put(PlayType.jczq_spfwrq_7_8_dan.getValue(),"7*8");
			playMap.put(PlayType.jczq_spfwrq_7_21_dan.getValue(),"7*21");
			playMap.put(PlayType.jczq_spfwrq_7_35_dan.getValue(),"7*35");
			playMap.put(PlayType.jczq_spfwrq_7_120_dan.getValue(),"7*120");
			playMap.put(PlayType.jczq_spfwrq_8_8_dan.getValue(),"8*8");
			playMap.put(PlayType.jczq_spfwrq_8_9_dan.getValue(),"8*9");
			playMap.put(PlayType.jczq_spfwrq_8_28_dan.getValue(),"8*28");
			playMap.put(PlayType.jczq_spfwrq_8_56_dan.getValue(),"8*56");
			playMap.put(PlayType.jczq_spfwrq_8_70_dan.getValue(),"8*70");
			playMap.put(PlayType.jczq_spfwrq_8_247_dan.getValue(),"8*247");
			
			playMap.put(PlayType.jczq_mix_1_1.getValue(),"1*1");
			playMap.put(PlayType.jczq_mix_2_1.getValue(),"2*1");
			playMap.put(PlayType.jczq_mix_3_1.getValue(),"3*1");
			playMap.put(PlayType.jczq_mix_4_1.getValue(),"4*1");
			playMap.put(PlayType.jczq_mix_5_1.getValue(),"5*1");
			playMap.put(PlayType.jczq_mix_6_1.getValue(),"6*1");
			playMap.put(PlayType.jczq_mix_7_1.getValue(),"7*1");
			playMap.put(PlayType.jczq_mix_8_1.getValue(),"8*1");
			playMap.put(PlayType.jczq_mix_3_3.getValue(),"3*3");
			playMap.put(PlayType.jczq_mix_3_4.getValue(),"3*4");
			playMap.put(PlayType.jczq_mix_4_4.getValue(),"4*4");
			playMap.put(PlayType.jczq_mix_4_5.getValue(),"4*5");
			playMap.put(PlayType.jczq_mix_4_6.getValue(),"4*6");
			playMap.put(PlayType.jczq_mix_4_11.getValue(),"4*11");
			playMap.put(PlayType.jczq_mix_5_5.getValue(),"5*5");
			playMap.put(PlayType.jczq_mix_5_6.getValue(),"5*6");
			playMap.put(PlayType.jczq_mix_5_10.getValue(),"5*10");
			playMap.put(PlayType.jczq_mix_5_16.getValue(),"5*16");
			playMap.put(PlayType.jczq_mix_5_20.getValue(),"5*20");
			playMap.put(PlayType.jczq_mix_5_26.getValue(),"5*26");
			playMap.put(PlayType.jczq_mix_6_6.getValue(),"6*6");
			playMap.put(PlayType.jczq_mix_6_7.getValue(),"6*7");
			playMap.put(PlayType.jczq_mix_6_15.getValue(),"6*15");
			playMap.put(PlayType.jczq_mix_6_20.getValue(),"6*20");
			playMap.put(PlayType.jczq_mix_6_22.getValue(),"6*22");
			playMap.put(PlayType.jczq_mix_6_35.getValue(),"6*35");
			playMap.put(PlayType.jczq_mix_6_42.getValue(),"6*42");
			playMap.put(PlayType.jczq_mix_6_50.getValue(),"6*50");
			playMap.put(PlayType.jczq_mix_6_57.getValue(),"6*57");
			playMap.put(PlayType.jczq_mix_7_7.getValue(),"7*7");
			playMap.put(PlayType.jczq_mix_7_8.getValue(),"7*8");
			playMap.put(PlayType.jczq_mix_7_21.getValue(),"7*21");
			playMap.put(PlayType.jczq_mix_7_35.getValue(),"7*35");
			playMap.put(PlayType.jczq_mix_7_120.getValue(),"7*120");
			playMap.put(PlayType.jczq_mix_8_8.getValue(),"8*8");
			playMap.put(PlayType.jczq_mix_8_9.getValue(),"8*9");
			playMap.put(PlayType.jczq_mix_8_28.getValue(),"8*28");
			playMap.put(PlayType.jczq_mix_8_56.getValue(),"8*56");
			playMap.put(PlayType.jczq_mix_8_70.getValue(),"8*70");
			playMap.put(PlayType.jczq_mix_8_247.getValue(),"8*247");
		
			playMap.put(PlayType.jczq_mix_2_1_dan.getValue(),"2*1");
			playMap.put(PlayType.jczq_mix_3_1_dan.getValue(),"3*1");
			playMap.put(PlayType.jczq_mix_4_1_dan.getValue(),"4*1");
			playMap.put(PlayType.jczq_mix_5_1_dan.getValue(),"5*1");
			playMap.put(PlayType.jczq_mix_6_1_dan.getValue(),"6*1");
			playMap.put(PlayType.jczq_mix_7_1_dan.getValue(),"7*1");
			playMap.put(PlayType.jczq_mix_8_1_dan.getValue(),"8*1");
			playMap.put(PlayType.jczq_mix_3_3_dan.getValue(),"3*3");
			playMap.put(PlayType.jczq_mix_3_4_dan.getValue(),"3*4");
			playMap.put(PlayType.jczq_mix_4_4_dan.getValue(),"4*4");
			playMap.put(PlayType.jczq_mix_4_5_dan.getValue(),"4*5");
			playMap.put(PlayType.jczq_mix_4_6_dan.getValue(),"4*6");
			playMap.put(PlayType.jczq_mix_4_11_dan.getValue(),"4*11");
			playMap.put(PlayType.jczq_mix_5_5_dan.getValue(),"5*5");
			playMap.put(PlayType.jczq_mix_5_6_dan.getValue(),"5*6");
			playMap.put(PlayType.jczq_mix_5_10_dan.getValue(),"5*10");
			playMap.put(PlayType.jczq_mix_5_16_dan.getValue(),"5*16");
			playMap.put(PlayType.jczq_mix_5_20_dan.getValue(),"5*20");
			playMap.put(PlayType.jczq_mix_5_26_dan.getValue(),"5*26");
			playMap.put(PlayType.jczq_mix_6_6_dan.getValue(),"6*6");
			playMap.put(PlayType.jczq_mix_6_7_dan.getValue(),"6*7");
			playMap.put(PlayType.jczq_mix_6_15_dan.getValue(),"6*15");
			playMap.put(PlayType.jczq_mix_6_20_dan.getValue(),"6*20");
			playMap.put(PlayType.jczq_mix_6_22_dan.getValue(),"6*22");
			playMap.put(PlayType.jczq_mix_6_35_dan.getValue(),"6*35");
			playMap.put(PlayType.jczq_mix_6_42_dan.getValue(),"6*42");
			playMap.put(PlayType.jczq_mix_6_50_dan.getValue(),"6*50");
			playMap.put(PlayType.jczq_mix_6_57_dan.getValue(),"6*57");
			playMap.put(PlayType.jczq_mix_7_7_dan.getValue(),"7*7");
			playMap.put(PlayType.jczq_mix_7_8_dan.getValue(),"7*8");
			playMap.put(PlayType.jczq_mix_7_21_dan.getValue(),"7*21");
			playMap.put(PlayType.jczq_mix_7_35_dan.getValue(),"7*35");
			playMap.put(PlayType.jczq_mix_7_120_dan.getValue(),"7*120");
			playMap.put(PlayType.jczq_mix_8_8_dan.getValue(),"8*8");
			playMap.put(PlayType.jczq_mix_8_9_dan.getValue(),"8*9");
			playMap.put(PlayType.jczq_mix_8_28_dan.getValue(),"8*28");
			playMap.put(PlayType.jczq_mix_8_56_dan.getValue(),"8*56");
			playMap.put(PlayType.jczq_mix_8_70_dan.getValue(),"8*70");
			playMap.put(PlayType.jczq_mix_8_247_dan.getValue(),"8*247");
			
		
		IVenderTicketConverter ssq = new IVenderTicketConverter() {
			@Override
			public String convert(Ticket ticket) {
				StringBuilder strBuilder=new StringBuilder();
				String []cons = ticket.getContent().split("-")[1].split("\\^");
				int i=0;
				for(String con:cons){
					strBuilder.append(con.replace("~", "#"));
					if(i!=cons.length-1){
						strBuilder.append(";");
					}
					i++;
				}
				strBuilder.append(":1:").append(playTypeMap.get(ticket.getPlayType()));
		        return strBuilder.toString();
		}};
		IVenderTicketConverter ssqds = new IVenderTicketConverter() {
			@Override
			public String convert(Ticket ticket) {
				StringBuilder strBuilder=new StringBuilder();
				String []cons = ticket.getContent().split("-")[1].split("\\^");
				int i=0;
				for(String con:cons){
					strBuilder.append(con.replace("~", "#"));
					strBuilder.append(":1:").append(playTypeMap.get(ticket.getPlayType()));
					if(i!=cons.length-1){
						strBuilder.append(";");
					}
					i++;
				}
		        return strBuilder.toString();
		}};
		
		IVenderTicketConverter dlt = new IVenderTicketConverter() {
			@Override
			public String convert(Ticket ticket) {
				StringBuilder strBuilder=new StringBuilder();
				String []cons = ticket.getContent().split("-")[1].split("\\^");
				int i=0;
				for(String con:cons){
					strBuilder.append(con.replace("~", "#"));
					if(i!=cons.length-1){
						strBuilder.append(";");
					}
					i++;
				}
				if(ticket.getAddition()==1){
					strBuilder.append(":2:");
				}else{
					strBuilder.append(":1:");
				}
				strBuilder.append(playTypeMap.get(ticket.getPlayType()));
		        return strBuilder.toString();
		}};
		
		IVenderTicketConverter dltdt = new IVenderTicketConverter() {
			@Override
			public String convert(Ticket ticket) {
				StringBuilder strBuilder=new StringBuilder();
				String cons = ticket.getContent().split("-")[1].replace("^","").replace("#", "$");
				strBuilder.append(cons);
				if(ticket.getAddition()==1){
					strBuilder.append(":2:");
				}else{
					strBuilder.append(":1:");
				}
				strBuilder.append(playTypeMap.get(ticket.getPlayType()));
		        return strBuilder.toString();
		}};
		
		
		IVenderTicketConverter ssqdt = new IVenderTicketConverter() {
			@Override
			public String convert(Ticket ticket) {
				StringBuilder strBuilder=new StringBuilder();
				String cons = ticket.getContent().split("-")[1].replace("^","").replace("#", "$");
				strBuilder.append(cons);
				strBuilder.append(":1:").append(playTypeMap.get(ticket.getPlayType()));
		        return strBuilder.toString();
		}};
		
		
		
		IVenderTicketConverter rjcfs = new IVenderTicketConverter() {
			@Override
			public String convert(Ticket ticket) {
				StringBuilder strBuilder=new StringBuilder();
				String cons = ticket.getContent().split("-")[1].replace("^","").replace("~", "#");
				strBuilder.append(cons);
				strBuilder.append(":1:").append(playTypeMap.get(ticket.getPlayType()));
		        return strBuilder.toString();
		}};
	
		
		IVenderTicketConverter sdds = new IVenderTicketConverter() {
			@Override
			public String convert(Ticket ticket) {
				StringBuilder strBuilder=new StringBuilder();
				String []cons = ticket.getContent().split("-")[1].split("\\^");
				int i=0;
				for(String con:cons){
					String []ss=con.split("\\,");
					int j=0;
					for(String s:ss){
						strBuilder.append(Integer.parseInt(s));
						if(j!=ss.length-1){
							strBuilder.append(",");
						}
						j++;
					}
					if(i!=cons.length-1){
						strBuilder.append(";");
					}
					i++;
				}
				strBuilder.append(":").append(playMap.get(ticket.getPlayType())).append(":").append(playTypeMap.get(ticket.getPlayType()));
		        return strBuilder.toString();
		}};
		
		IVenderTicketConverter sdfs = new IVenderTicketConverter() {
			@Override
			public String convert(Ticket ticket) {
				StringBuilder strBuilder=new StringBuilder();
				String []cons = ticket.getContent().split("-")[1].replace("^", "").split("\\|");
				int i=0;
				for(String con:cons){
					String []ss=con.split("\\,");
					for(String s:ss){
						strBuilder.append(Integer.parseInt(s));
					}
					if(i!=cons.length-1){
						strBuilder.append(",");
					}
					i++;
				}
				strBuilder.append(":").append(playMap.get(ticket.getPlayType())).append(":").append(playTypeMap.get(ticket.getPlayType()));
		        return strBuilder.toString();
		}};
		
		IVenderTicketConverter sdzxfs = new IVenderTicketConverter() {
			@Override
			public String convert(Ticket ticket) {
				StringBuilder strBuilder=new StringBuilder();
				String []cons = ticket.getContent().split("-")[1].replace("^", "").split("\\,");
				int i=0;
				for(String con:cons){
					String []ss=con.split("\\,");
					for(String s:ss){
						strBuilder.append(Integer.parseInt(s));
					}
					if(i!=cons.length-1){
						strBuilder.append(",");
					}
					i++;
				}
				strBuilder.append(":").append(playMap.get(ticket.getPlayType())).append(":").append(playTypeMap.get(ticket.getPlayType()));
		        return strBuilder.toString();
		}};
		
		
		IVenderTicketConverter jczq = new IVenderTicketConverter() {
			@Override
			public String convert(Ticket ticket) {
				StringBuilder strBuilder=new StringBuilder();
				int playType=Integer.parseInt(ticket.getContent().split("-")[0]);
				String con = ticket.getContent().split("-")[1].replace("^", "").replace("(","=").replace(")","").replace(",","/").replace("|",",");;
				String  []contents=con.split("\\,");
				strBuilder.append(playTypeMap.get(playType)).append("|");
				int i=0;
				for(String s:contents){
					strBuilder.append(s.substring(2));
					if(i!=contents.length-1){
						strBuilder.append(",");
					}
					i++;
				}
				strBuilder.append("|").append(playMap.get(playType));
		        return strBuilder.toString();
		}};
		IVenderTicketConverter jczqmix = new IVenderTicketConverter() {
			@Override
			public String convert(Ticket ticket) {
				StringBuilder strBuilder=new StringBuilder();
				int playType=Integer.parseInt(ticket.getContent().split("-")[0]);
				String content = ticket.getContent().split("-")[1].replace("^", "").replace("(","=").replace(")","").replace(",","/").replace("|",",");
				String []cons=content.split("\\,");
				StringBuffer stringBuffer=new StringBuffer();
				int i=0;
				for(String con:cons){
					stringBuffer.append(con.split("\\=")[0].split("\\*")[0].substring(2)).append(">").append(toLotteryTypeMap.get(con.split("\\=")[0].split("\\*")[1]));
					stringBuffer.append("=");
					String playTypeStr=con.split("\\=")[0].split("\\*")[1];
					String []ss=con.split("\\=")[1].split("\\/");
					int j=0;
					for(String s:ss){
						if("3007".equals(playTypeStr)){
							stringBuffer.append(s.substring(0,1)).append(":").append(s.substring(1,2));
						}else if("3009".equals(playTypeStr)){
							stringBuffer.append(s.substring(0,1)).append("-").append(s.substring(1,2));
						}else{
							stringBuffer.append(s);
						}
						if(j!=ss.length-1){
							stringBuffer.append("/");
						}
						j++;
					}
					
					if(i!=cons.length-1){
						stringBuffer.append(",");
					}
					i++;
		        }
				strBuilder.append(playTypeMap.get(playType)).append("|").append(stringBuffer.toString()).append("|").append(playMap.get(playType));
		       
		        return strBuilder.toString();
		}};
		
		IVenderTicketConverter jczqbf = new IVenderTicketConverter() {
			@Override
			public String convert(Ticket ticket) {
				StringBuilder strBuilder=new StringBuilder();
				int playType=Integer.parseInt(ticket.getContent().split("-")[0]);
				String content = ticket.getContent().split("-")[1].replace("^", "").replace("(","=").replace(")","").replace(",","/").replace("|",",");
				String []cons=content.split("\\,");
				StringBuffer stringBuffer=new StringBuffer();
				int i=0;
				for(String con:cons){
					stringBuffer.append(con.split("\\=")[0].substring(2)).append("=");
					String []ss=con.split("\\=")[1].split("\\/");
					int j=0;
					for(String s:ss){
						stringBuffer.append(s.substring(0,1)).append(":").append(s.substring(1,2));
						if(j!=ss.length-1){
							stringBuffer.append("/");
						}
						j++;
					}
					
					if(i!=cons.length-1){
						stringBuffer.append(",");
					}
					i++;
		        }
				strBuilder.append(playTypeMap.get(playType)).append("|").append(stringBuffer.toString()).append("|").append(playMap.get(playType));
		        return strBuilder.toString();
		}};
		
		
		IVenderTicketConverter dxf = new IVenderTicketConverter() {
			@Override
			public String convert(Ticket ticket) {
				StringBuilder strBuilder=new StringBuilder();
				int playType=Integer.parseInt(ticket.getContent().split("-")[0]);
				String content = ticket.getContent().split("-")[1].replace("^", "").replace("(","=").replace(")","").replace(",","/").replace("|",",");
				String []cons=content.split("\\,");
				StringBuffer stringBuffer=new StringBuffer();
				int i=0;
				for(String con:cons){
					stringBuffer.append(con.split("\\=")[0].substring(2)).append("=").append(con.split("\\=")[1].replace("1","3").replace("2","0"));
					if(i!=cons.length-1){
						stringBuffer.append(",");
					}
					i++;
		        }
				strBuilder.append(playTypeMap.get(playType)).append("|").append(stringBuffer.toString()).append("|").append(playMap.get(playType));
		        return strBuilder.toString();
		}};
		IVenderTicketConverter jclqmix = new IVenderTicketConverter() {
			@Override
			public String convert(Ticket ticket) {
				StringBuilder strBuilder=new StringBuilder();
				int playType=Integer.parseInt(ticket.getContent().split("-")[0]);
				String content = ticket.getContent().split("-")[1].replace("^", "").replace("(","=").replace(")","").replace(",","/").replace("|",",");
				String []cons=content.split("\\,");
				StringBuffer stringBuffer=new StringBuffer();
				int i=0;
				for(String con:cons){
					stringBuffer.append(con.split("\\=")[0].split("\\*")[0].substring(2)).append(">").append(toLotteryTypeMap.get(con.split("\\=")[0].split("\\*")[1]));
					stringBuffer.append("=");
					String playTypeStr=con.split("\\=")[0].split("\\*")[1];
					String []ss=con.split("\\=")[1].split("\\/");
					int j=0;
					for(String s:ss){
						if("3003".equals(playTypeStr)){
							stringBuffer.append(s.substring(0,2));
						}else if("3004".equals(playTypeStr)){
							stringBuffer.append(s.replace("1","3").replace("2","0"));
						}else{
							stringBuffer.append(s);
						}
						if(j!=ss.length-1){
							stringBuffer.append("/");
						}
						j++;
					}
					
					if(i!=cons.length-1){
						stringBuffer.append(",");
					}
					i++;
		        }
				strBuilder.append(playTypeMap.get(playType)).append("|").append(stringBuffer.toString()).append("|").append(playMap.get(playType));
		        return strBuilder.toString();
		}};
		
		IVenderTicketConverter jczqbqc = new IVenderTicketConverter() {
			@Override
			public String convert(Ticket ticket) {
				StringBuilder strBuilder=new StringBuilder();
				int playType=Integer.parseInt(ticket.getContent().split("-")[0]);
				String content = ticket.getContent().split("-")[1].replace("^", "").replace("(","=").replace(")","").replace(",","/").replace("|",",");
				String []cons=content.split("\\,");
				StringBuffer stringBuffer=new StringBuffer();
				int i=0;
				for(String con:cons){
					stringBuffer.append(con.split("\\=")[0].substring(2)).append("=");
					String []ss=con.split("\\=")[1].split("\\/");
					int j=0;
					for(String s:ss){
						stringBuffer.append(s.substring(0,1)).append("-").append(s.substring(1,2));
						if(j!=ss.length-1){
							stringBuffer.append("/");
						}
						j++;
					}
					
					if(i!=cons.length-1){
						stringBuffer.append(",");
					}
					i++;
		        }
				strBuilder.append(playTypeMap.get(playType)).append("|").append(stringBuffer.toString()).append("|").append(playMap.get(playType));
		        return strBuilder.toString();
		}};
		
		
		playTypeToAdvanceConverterMap.put(PlayType.ssq_dan, ssqds);
		playTypeToAdvanceConverterMap.put(PlayType.ssq_fs, ssq);
		playTypeToAdvanceConverterMap.put(PlayType.ssq_dt, ssqdt);
		
		playTypeToAdvanceConverterMap.put(PlayType.qlc_dan, ssqds);
		playTypeToAdvanceConverterMap.put(PlayType.qlc_fs, ssq);
		playTypeToAdvanceConverterMap.put(PlayType.qlc_dt, ssqdt);
		
		playTypeToAdvanceConverterMap.put(PlayType.dlt_dan, dlt);
		playTypeToAdvanceConverterMap.put(PlayType.dlt_fu, dlt);
		playTypeToAdvanceConverterMap.put(PlayType.dlt_dantuo, dltdt);
		
		playTypeToAdvanceConverterMap.put(PlayType.d3_zhi_dan, sdds);
		playTypeToAdvanceConverterMap.put(PlayType.d3_z3_dan, sdds);
		playTypeToAdvanceConverterMap.put(PlayType.d3_z6_dan, sdds);
		playTypeToAdvanceConverterMap.put(PlayType.d3_zhi_fs, sdfs);
		playTypeToAdvanceConverterMap.put(PlayType.d3_z3_fs, sdzxfs);
		playTypeToAdvanceConverterMap.put(PlayType.d3_z6_fs, sdzxfs);
		playTypeToAdvanceConverterMap.put(PlayType.d3_zhi_hz, sdzxfs);
		playTypeToAdvanceConverterMap.put(PlayType.d3_z3_hz, sdzxfs);
		playTypeToAdvanceConverterMap.put(PlayType.d3_z6_hz, sdzxfs);
		
		
		//足彩
		playTypeToAdvanceConverterMap.put(PlayType.zc_sfc_dan, ssqds);
		playTypeToAdvanceConverterMap.put(PlayType.zc_sfc_fu, ssq);
		playTypeToAdvanceConverterMap.put(PlayType.zc_rjc_dan, ssqds);
		playTypeToAdvanceConverterMap.put(PlayType.zc_rjc_fu, rjcfs);
		playTypeToAdvanceConverterMap.put(PlayType.zc_jqc_dan, ssqds);
		playTypeToAdvanceConverterMap.put(PlayType.zc_jqc_fu, ssq);
		playTypeToAdvanceConverterMap.put(PlayType.zc_bqc_dan, ssqds);
		playTypeToAdvanceConverterMap.put(PlayType.zc_bqc_fu, ssq);
		
		
        playTypeToAdvanceConverterMap.put(PlayType.jclq_sf_1_1,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_sf_2_1,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_sf_3_1,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_sf_4_1,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_sf_5_1,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_sf_6_1,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_sf_7_1,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_sf_8_1,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_sf_3_3,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_sf_3_4,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_sf_4_4,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_sf_4_5,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_sf_4_6,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_sf_4_1,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_sf_5_5,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_sf_5_6,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_sf_5_10,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_sf_5_16,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_sf_5_20,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_sf_5_26,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_sf_6_6,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_sf_6_7,jczq);
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_sf_6_15,jczq);       
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_sf_6_20,jczq);       
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_sf_6_22,jczq);       
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_sf_6_35,jczq);       
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_sf_6_42,jczq);       
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_sf_6_50,jczq);       
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_sf_6_57,jczq);       
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_sf_7_7,jczq);        
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_sf_7_8,jczq);        
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_sf_7_21,jczq);       
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_sf_7_35,jczq);       
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_sf_7_120,jczq);        
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_sf_8_8,jczq);        
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_sf_8_9,jczq);        
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_sf_8_28,jczq);       
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_sf_8_56,jczq);       
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_sf_8_70,jczq);       
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_sf_8_247,jczq);      
    	                                                                       
    	                                                                       
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_sf_2_1_dan,jczq);    
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_sf_3_1_dan,jczq);    
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_sf_4_1_dan,jczq);    
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_sf_5_1_dan,jczq);    
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_sf_6_1_dan,jczq);    
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_sf_7_1_dan,jczq);    
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_sf_8_1_dan,jczq);    
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_sf_3_3_dan,jczq);    
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_sf_3_4_dan,jczq);    
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_sf_4_4_dan,jczq);    
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_sf_4_5_dan,jczq);    
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_sf_4_6_dan,jczq);    
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_sf_4_11_dan,jczq);   
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_sf_5_5_dan,jczq);    
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_sf_5_6_dan,jczq);    
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_sf_5_10_dan,jczq);   
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_sf_5_16_dan,jczq);   
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_sf_5_20_dan,jczq);   
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_sf_5_26_dan,jczq);   
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_sf_6_6_dan,jczq);    
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_sf_6_7_dan,jczq);    
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_sf_6_15_dan,jczq);   
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_sf_6_20_dan,jczq);   
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_sf_6_22_dan,jczq);   
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_sf_6_35_dan,jczq);   
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_sf_6_42_dan,jczq);   
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_sf_6_50_dan,jczq);   
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_sf_6_57_dan,jczq);   
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_sf_7_7_dan,jczq);    
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_sf_7_8_dan,jczq);    
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_sf_7_21_dan,jczq);   
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_sf_7_35_dan,jczq);   
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_sf_7_120_dan,jczq);  
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_sf_8_8_dan,jczq);    
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_sf_8_9_dan,jczq);    
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_sf_8_28_dan,jczq);   
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_sf_8_56_dan,jczq);   
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_sf_8_70_dan,jczq);   
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_sf_8_247_dan,jczq);  
    	                                                                      
    	                                                                      
    	                                                                      
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_rfsf_1_1,jczq);      
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_rfsf_2_1,jczq);      
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_rfsf_3_1,jczq);      
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_rfsf_4_1,jczq);      
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_rfsf_5_1,jczq);      
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_rfsf_6_1,jczq);      
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_rfsf_7_1,jczq);      
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_rfsf_8_1,jczq);      
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_rfsf_3_3,jczq);      
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_rfsf_3_4,jczq);      
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_rfsf_4_4,jczq);      
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_rfsf_4_5,jczq);      
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_rfsf_4_6,jczq);      
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_rfsf_4_11,jczq);     
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_rfsf_5_5,jczq);      
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_rfsf_5_6,jczq);      
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_rfsf_5_10,jczq);     
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_rfsf_5_16,jczq);     
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_rfsf_5_20,jczq);     
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_rfsf_5_26,jczq);     
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_rfsf_6_6,jczq);      
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_rfsf_6_7,jczq);      
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_rfsf_6_15,jczq);     
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_rfsf_6_20,jczq);     
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_rfsf_6_22,jczq);     
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_rfsf_6_35,jczq);     
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_rfsf_6_42,jczq);     
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_rfsf_6_50,jczq);     
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_rfsf_6_57,jczq);     
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_rfsf_7_7,jczq);      
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_rfsf_7_8,jczq);      
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_rfsf_7_21,jczq);     
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_rfsf_7_35,jczq);     
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_rfsf_7_120,jczq);    
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_rfsf_8_8,jczq);      
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_rfsf_8_9,jczq);      
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_rfsf_8_28,jczq);     
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_rfsf_8_56,jczq);     
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_rfsf_8_70,jczq);     
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_rfsf_8_247,jczq);    
    	                                                                       
    	                                                                       
    	                                                                       
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_rfsf_1_1_dan,jczq);  
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_rfsf_2_1_dan,jczq);  
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_rfsf_3_1_dan,jczq);  
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_rfsf_4_1_dan,jczq);  
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_rfsf_5_1_dan,jczq);  
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_rfsf_6_1_dan,jczq);  
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_rfsf_7_1_dan,jczq);  
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_rfsf_8_1_dan,jczq);  
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_rfsf_3_3_dan,jczq);  
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_rfsf_3_4_dan,jczq);  
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_rfsf_4_4_dan,jczq);  
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_rfsf_4_5_dan,jczq);  
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_rfsf_4_6_dan,jczq);  
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_rfsf_4_11_dan,jczq); 
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_rfsf_5_5_dan,jczq);  
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_rfsf_5_6_dan,jczq);  
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_rfsf_5_10_dan,jczq); 
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_rfsf_5_16_dan,jczq); 
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_rfsf_5_20_dan,jczq); 
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_rfsf_5_26_dan,jczq); 
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_rfsf_6_6_dan,jczq);  
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_rfsf_6_7_dan,jczq);  
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_rfsf_6_15_dan,jczq); 
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_rfsf_6_20_dan,jczq); 
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_rfsf_6_22_dan,jczq); 
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_rfsf_6_35_dan,jczq); 
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_rfsf_6_42_dan,jczq); 
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_rfsf_6_50_dan,jczq); 
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_rfsf_6_57_dan,jczq); 
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_rfsf_7_7_dan,jczq);  
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_rfsf_7_8_dan,jczq);  
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_rfsf_7_21_dan,jczq); 
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_rfsf_7_35_dan,jczq); 
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_rfsf_7_120_dan,jczq);
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_rfsf_8_8_dan,jczq);  
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_rfsf_8_9_dan,jczq);  
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_rfsf_8_28_dan,jczq); 
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_rfsf_8_56_dan,jczq); 
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_rfsf_8_70_dan,jczq); 
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_rfsf_8_247_dan,jczq);


    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_sfc_1_1,jczq);     
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_sfc_2_1,jczq);     
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_sfc_3_1,jczq);     
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_sfc_4_1,jczq);     
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_sfc_5_1,jczq);     
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_sfc_6_1,jczq);     
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_sfc_7_1,jczq);     
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_sfc_8_1,jczq);     
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_sfc_3_3,jczq);     
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_sfc_3_4,jczq);     
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_sfc_4_4,jczq);     
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_sfc_4_5,jczq);     
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_sfc_4_6,jczq);     
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_sfc_4_11,jczq);    
    	                                                                    
    	                                                                    
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_sfc_1_1_dan,jczq); 
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_sfc_2_1_dan,jczq); 
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_sfc_3_1_dan,jczq); 
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_sfc_4_1_dan,jczq); 
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_sfc_5_1_dan,jczq); 
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_sfc_6_1_dan,jczq); 
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_sfc_7_1_dan,jczq); 
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_sfc_8_1_dan,jczq); 
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_sfc_3_3_dan,jczq); 
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_sfc_3_4_dan,jczq); 
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_sfc_4_4_dan,jczq); 
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_sfc_4_5_dan,jczq); 
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_sfc_4_6_dan,jczq); 
    	 playTypeToAdvanceConverterMap.put(PlayType.jclq_sfc_4_11_dan,jczq);
  

    	playTypeToAdvanceConverterMap.put(PlayType.jclq_dxf_1_1,dxf);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_dxf_2_1,dxf);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_dxf_3_1,dxf);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_dxf_4_1,dxf);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_dxf_5_1,dxf);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_dxf_6_1,dxf);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_dxf_7_1,dxf);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_dxf_8_1,dxf);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_dxf_3_3,dxf);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_dxf_3_4,dxf);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_dxf_4_4,dxf);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_dxf_4_5,dxf);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_dxf_4_6,dxf);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_dxf_4_11,dxf);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_dxf_5_5,dxf);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_dxf_5_6,dxf);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_dxf_5_10,dxf);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_dxf_5_16,dxf);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_dxf_5_20,dxf);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_dxf_5_26,dxf); 
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_dxf_6_6,dxf);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_dxf_6_7,dxf);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_dxf_6_15,dxf);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_dxf_6_20,dxf);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_dxf_6_22,dxf);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_dxf_6_35,dxf);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_dxf_6_42,dxf);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_dxf_6_50,dxf);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_dxf_6_57,dxf);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_dxf_7_7,dxf);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_dxf_7_8,dxf);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_dxf_7_21,dxf);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_dxf_7_35,dxf);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_dxf_7_120,dxf);  
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_dxf_8_8,dxf);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_dxf_8_9,dxf);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_dxf_8_28,dxf);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_dxf_8_56,dxf);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_dxf_8_70,dxf);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_dxf_8_247,dxf);



    	playTypeToAdvanceConverterMap.put(PlayType.jclq_dxf_1_1_dan,dxf);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_dxf_2_1_dan,dxf);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_dxf_3_1_dan,dxf);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_dxf_4_1_dan,dxf);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_dxf_5_1_dan,dxf);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_dxf_6_1_dan,dxf);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_dxf_7_1_dan,dxf);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_dxf_8_1_dan,dxf);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_dxf_3_3_dan,dxf);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_dxf_3_4_dan,dxf);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_dxf_4_4_dan,dxf);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_dxf_4_5_dan,dxf);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_dxf_4_6_dan,dxf);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_dxf_4_11_dan,dxf); 
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_dxf_5_5_dan,dxf);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_dxf_5_6_dan,dxf);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_dxf_5_10_dan,dxf);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_dxf_5_16_dan,dxf);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_dxf_5_20_dan,dxf);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_dxf_5_26_dan,dxf);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_dxf_6_6_dan,dxf);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_dxf_6_7_dan,dxf);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_dxf_6_15_dan,dxf);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_dxf_6_20_dan,dxf);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_dxf_6_22_dan,dxf);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_dxf_6_35_dan,dxf);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_dxf_6_42_dan,dxf);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_dxf_6_50_dan,dxf);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_dxf_6_57_dan,dxf);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_dxf_7_7_dan,dxf);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_dxf_7_8_dan,dxf);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_dxf_7_21_dan,dxf);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_dxf_7_35_dan,dxf);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_dxf_7_120_dan,dxf);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_dxf_8_8_dan,dxf);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_dxf_8_9_dan,dxf);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_dxf_8_28_dan,dxf);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_dxf_8_56_dan,dxf);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_dxf_8_70_dan,dxf);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_dxf_8_247_dan,dxf);  


    	playTypeToAdvanceConverterMap.put(PlayType.jclq_mix_1_1,jclqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_mix_2_1,jclqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_mix_3_1,jclqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_mix_4_1,jclqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_mix_5_1,jclqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_mix_6_1,jclqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_mix_7_1,jclqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_mix_8_1,jclqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_mix_3_3,jclqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_mix_3_4,jclqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_mix_4_4,jclqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_mix_4_5,jclqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_mix_4_6,jclqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_mix_4_11,jclqmix);   
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_mix_5_5,jclqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_mix_5_6,jclqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_mix_5_10,jclqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_mix_5_16,jclqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_mix_5_20,jclqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_mix_5_26,jclqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_mix_6_6,jclqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_mix_6_7,jclqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_mix_6_15,jclqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_mix_6_20,jclqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_mix_6_22,jclqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_mix_6_35,jclqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_mix_6_42,jclqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_mix_6_50,jclqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_mix_6_57,jclqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_mix_7_7,jclqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_mix_7_8,jclqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_mix_7_21,jclqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_mix_7_35,jclqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_mix_7_120,jclqmix);  
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_mix_8_8,jclqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_mix_8_9,jclqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_mix_8_28,jclqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_mix_8_56,jclqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_mix_8_70,jclqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_mix_8_247,jclqmix); 


    	playTypeToAdvanceConverterMap.put(PlayType.jclq_mix_2_1_dan,jclqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_mix_3_1_dan,jclqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_mix_4_1_dan,jclqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_mix_5_1_dan,jclqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_mix_6_1_dan,jclqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_mix_7_1_dan,jclqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_mix_8_1_dan,jclqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_mix_3_3_dan,jclqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_mix_3_4_dan,jclqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_mix_4_4_dan,jclqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_mix_4_5_dan,jclqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_mix_4_6_dan,jclqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_mix_4_11_dan,jclqmix);   
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_mix_5_5_dan,jclqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_mix_5_6_dan,jclqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_mix_5_10_dan,jclqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_mix_5_16_dan,jclqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_mix_5_20_dan,jclqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_mix_5_26_dan,jclqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_mix_6_6_dan,jclqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_mix_6_7_dan,jclqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_mix_6_15_dan,jclqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_mix_6_20_dan,jclqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_mix_6_22_dan,jclqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_mix_6_35_dan,jclqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_mix_6_42_dan,jclqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_mix_6_50_dan,jclqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_mix_6_57_dan,jclqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_mix_7_7_dan,jclqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_mix_7_8_dan,jclqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_mix_7_21_dan,jclqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_mix_7_35_dan,jclqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_mix_7_120_dan,jclqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_mix_8_8_dan,jclqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_mix_8_9_dan,jclqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_mix_8_28_dan,jclqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_mix_8_56_dan,jclqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_mix_8_70_dan,jclqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jclq_mix_8_247_dan,jclqmix); 



    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spf_1_1,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spf_2_1,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spf_3_1,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spf_4_1,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spf_5_1,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spf_6_1,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spf_7_1,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spf_8_1,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spf_3_3,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spf_3_4,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spf_4_4,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spf_4_5,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spf_4_6,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spf_4_11,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spf_5_5,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spf_5_6,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spf_5_10,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spf_5_16,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spf_5_20,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spf_5_26,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spf_6_6,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spf_6_7,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spf_6_15,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spf_6_20,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spf_6_22,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spf_6_35,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spf_6_42,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spf_6_50,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spf_6_57,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spf_7_7,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spf_7_8,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spf_8_8,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spf_8_9,jczq);    
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spf_7_21,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spf_7_35,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spf_8_28,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spf_8_56,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spf_8_70,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spf_8_247,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spf_7_120,jczq);


    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spf_2_1_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spf_3_1_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spf_4_1_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spf_5_1_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spf_6_1_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spf_7_1_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spf_8_1_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spf_3_3_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spf_3_4_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spf_4_4_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spf_4_5_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spf_4_6_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spf_4_11_dan,jczq); 
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spf_5_5_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spf_5_6_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spf_5_10_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spf_5_16_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spf_5_20_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spf_5_26_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spf_6_6_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spf_6_7_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spf_6_15_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spf_6_20_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spf_6_22_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spf_6_35_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spf_6_42_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spf_6_50_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spf_6_57_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spf_7_7_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spf_7_8_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spf_7_21_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spf_7_35_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spf_7_120_dan,jczq);  
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spf_8_8_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spf_8_9_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spf_8_28_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spf_8_56_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spf_8_70_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spf_8_247_dan,jczq);



    	playTypeToAdvanceConverterMap.put(PlayType.jczq_bf_1_1,jczqbf);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_bf_2_1,jczqbf);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_bf_3_1,jczqbf);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_bf_4_1,jczqbf);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_bf_5_1,jczqbf);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_bf_6_1,jczqbf);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_bf_7_1,jczqbf);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_bf_8_1,jczqbf);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_bf_3_3,jczqbf);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_bf_3_4,jczqbf);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_bf_4_4,jczqbf);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_bf_4_5,jczqbf);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_bf_4_6,jczqbf);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_bf_4_11,jczqbf);  


    	playTypeToAdvanceConverterMap.put(PlayType.jczq_bf_2_1_dan,jczqbf);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_bf_3_1_dan,jczqbf);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_bf_4_1_dan,jczqbf);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_bf_5_1_dan,jczqbf);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_bf_6_1_dan,jczqbf);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_bf_7_1_dan,jczqbf);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_bf_8_1_dan,jczqbf);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_bf_3_3_dan,jczqbf);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_bf_3_4_dan,jczqbf);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_bf_4_4_dan,jczqbf);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_bf_4_5_dan,jczqbf);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_bf_4_6_dan,jczqbf);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_bf_4_11_dan,jczqbf);



    	playTypeToAdvanceConverterMap.put(PlayType.jczq_jqs_1_1,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_jqs_2_1,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_jqs_3_1,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_jqs_4_1,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_jqs_5_1,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_jqs_6_1,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_jqs_7_1,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_jqs_8_1,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_jqs_3_3,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_jqs_3_4,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_jqs_4_4,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_jqs_4_5,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_jqs_4_6,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_jqs_4_11,jczq);   
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_jqs_5_5,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_jqs_5_6,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_jqs_5_10,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_jqs_5_16,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_jqs_5_20,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_jqs_5_26,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_jqs_6_6,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_jqs_6_7,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_jqs_6_15,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_jqs_6_20,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_jqs_6_22,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_jqs_6_35,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_jqs_6_42,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_jqs_6_50,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_jqs_6_57,jczq);



    	playTypeToAdvanceConverterMap.put(PlayType.jczq_jqs_2_1_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_jqs_3_1_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_jqs_4_1_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_jqs_5_1_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_jqs_6_1_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_jqs_7_1_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_jqs_8_1_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_jqs_3_3_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_jqs_3_4_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_jqs_4_4_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_jqs_4_5_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_jqs_4_6_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_jqs_4_11_dan,jczq);   
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_jqs_5_5_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_jqs_5_6_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_jqs_5_10_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_jqs_5_16_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_jqs_5_20_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_jqs_5_26_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_jqs_6_6_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_jqs_6_7_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_jqs_6_15_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_jqs_6_20_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_jqs_6_22_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_jqs_6_35_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_jqs_6_42_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_jqs_6_50_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_jqs_6_57_dan,jczq);



    	playTypeToAdvanceConverterMap.put(PlayType.jczq_bqc_1_1,jczqbqc);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_bqc_2_1,jczqbqc);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_bqc_3_1,jczqbqc);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_bqc_4_1,jczqbqc);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_bqc_5_1,jczqbqc);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_bqc_6_1,jczqbqc);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_bqc_7_1,jczqbqc);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_bqc_8_1,jczqbqc);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_bqc_3_3,jczqbqc);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_bqc_3_4,jczqbqc);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_bqc_4_4,jczqbqc);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_bqc_4_5,jczqbqc);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_bqc_4_6,jczqbqc);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_bqc_4_11,jczqbqc);  


    	playTypeToAdvanceConverterMap.put(PlayType.jczq_bqc_2_1_dan,jczqbqc);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_bqc_3_1_dan,jczqbqc);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_bqc_4_1_dan,jczqbqc);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_bqc_5_1_dan,jczqbqc);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_bqc_6_1_dan,jczqbqc);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_bqc_7_1_dan,jczqbqc);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_bqc_8_1_dan,jczqbqc);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_bqc_3_3_dan,jczqbqc);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_bqc_3_4_dan,jczqbqc);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_bqc_4_4_dan,jczqbqc);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_bqc_4_5_dan,jczqbqc);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_bqc_4_6_dan,jczqbqc);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_bqc_4_11_dan,jczqbqc);  


    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spfwrq_1_1,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spfwrq_2_1,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spfwrq_3_1,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spfwrq_4_1,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spfwrq_5_1,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spfwrq_6_1,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spfwrq_7_1,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spfwrq_8_1,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spfwrq_3_3,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spfwrq_3_4,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spfwrq_4_4,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spfwrq_4_5,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spfwrq_4_6,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spfwrq_4_11,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spfwrq_5_5,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spfwrq_5_6,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spfwrq_5_10,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spfwrq_5_16,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spfwrq_5_20,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spfwrq_5_26,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spfwrq_6_6,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spfwrq_6_7,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spfwrq_6_15,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spfwrq_6_20,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spfwrq_6_22,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spfwrq_6_35,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spfwrq_6_42,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spfwrq_6_50,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spfwrq_6_57,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spfwrq_7_7,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spfwrq_7_8,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spfwrq_7_21,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spfwrq_7_35,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spfwrq_7_120,jczq);     
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spfwrq_8_8,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spfwrq_8_9,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spfwrq_8_28,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spfwrq_8_56,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spfwrq_8_70,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spfwrq_8_247,jczq);


    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spfwrq_2_1_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spfwrq_3_1_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spfwrq_4_1_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spfwrq_5_1_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spfwrq_6_1_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spfwrq_7_1_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spfwrq_8_1_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spfwrq_3_3_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spfwrq_3_4_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spfwrq_4_4_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spfwrq_4_5_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spfwrq_4_6_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spfwrq_4_11_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spfwrq_5_5_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spfwrq_5_6_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spfwrq_5_10_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spfwrq_5_16_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spfwrq_5_20_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spfwrq_5_26_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spfwrq_6_6_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spfwrq_6_7_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spfwrq_6_15_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spfwrq_6_20_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spfwrq_6_22_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spfwrq_6_35_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spfwrq_6_42_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spfwrq_6_50_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spfwrq_6_57_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spfwrq_7_7_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spfwrq_7_8_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spfwrq_7_21_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spfwrq_7_35_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spfwrq_7_120_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spfwrq_8_8_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spfwrq_8_9_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spfwrq_8_28_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spfwrq_8_56_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spfwrq_8_70_dan,jczq);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_spfwrq_8_247_dan,jczq);


    	playTypeToAdvanceConverterMap.put(PlayType.jczq_mix_1_1,jczqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_mix_2_1,jczqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_mix_3_1,jczqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_mix_4_1,jczqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_mix_5_1,jczqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_mix_6_1,jczqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_mix_7_1,jczqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_mix_8_1,jczqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_mix_3_3,jczqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_mix_3_4,jczqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_mix_4_4,jczqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_mix_4_5,jczqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_mix_4_6,jczqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_mix_4_11,jczqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_mix_5_5,jczqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_mix_5_6,jczqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_mix_5_10,jczqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_mix_5_16,jczqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_mix_5_20,jczqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_mix_5_26,jczqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_mix_6_6,jczqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_mix_6_7,jczqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_mix_6_15,jczqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_mix_6_20,jczqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_mix_6_22,jczqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_mix_6_35,jczqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_mix_6_42,jczqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_mix_6_50,jczqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_mix_6_57,jczqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_mix_7_7,jczqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_mix_7_8,jczqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_mix_7_21,jczqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_mix_7_35,jczqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_mix_7_120,jczqmix); 
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_mix_8_8,jczqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_mix_8_9,jczqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_mix_8_28,jczqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_mix_8_56,jczqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_mix_8_70,jczqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_mix_8_247,jczqmix);


    	playTypeToAdvanceConverterMap.put(PlayType.jczq_mix_2_1_dan,jczqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_mix_3_1_dan,jczqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_mix_4_1_dan,jczqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_mix_5_1_dan,jczqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_mix_6_1_dan,jczqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_mix_7_1_dan,jczqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_mix_8_1_dan,jczqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_mix_3_3_dan,jczqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_mix_3_4_dan,jczqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_mix_4_4_dan,jczqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_mix_4_5_dan,jczqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_mix_4_6_dan,jczqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_mix_4_11_dan,jczqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_mix_5_5_dan,jczqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_mix_5_6_dan,jczqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_mix_5_10_dan,jczqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_mix_5_16_dan,jczqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_mix_5_20_dan,jczqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_mix_5_26_dan,jczqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_mix_6_6_dan,jczqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_mix_6_7_dan,jczqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_mix_6_15_dan,jczqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_mix_6_20_dan,jczqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_mix_6_22_dan,jczqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_mix_6_35_dan,jczqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_mix_6_42_dan,jczqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_mix_6_50_dan,jczqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_mix_6_57_dan,jczqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_mix_7_7_dan,jczqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_mix_7_8_dan,jczqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_mix_7_21_dan,jczqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_mix_7_35_dan,jczqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_mix_7_120_dan,jczqmix); 
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_mix_8_8_dan,jczqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_mix_8_9_dan,jczqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_mix_8_28_dan,jczqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_mix_8_56_dan,jczqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_mix_8_70_dan,jczqmix);
    	playTypeToAdvanceConverterMap.put(PlayType.jczq_mix_8_247_dan,jczqmix);  
		
    }
   
    
  

}
