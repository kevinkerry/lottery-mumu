package com.lottery.common.contains.lottery;

import java.util.ArrayList;
import java.util.List;

/***
 * 奖级
 * */
public enum LotteryDrawPrizeAwarder {
	
	NOT_WIN("", "未中奖奖级",LotteryType.ALL,false), 
	
	SSQ_1("1", "双色球一等奖",LotteryType.SSQ,false), 
	SSQ_2("2", "双色球二等奖",LotteryType.SSQ,false), 
	SSQ_3("3", "双色球三等奖",LotteryType.SSQ,true), 
	SSQ_4("4", "双色球四等奖",LotteryType.SSQ,true), 
	SSQ_5("5", "双色球五等奖",LotteryType.SSQ,true), 
	SSQ_6("6", "双色球六等奖",LotteryType.SSQ,true), 
	SSQ_2A("2A", "双色球二等奖幸运蓝球加奖",LotteryType.SSQ,false), 
	
	
	D3_1("1","3D直选",LotteryType.F3D,true),
	D3_2("2","3D组三",LotteryType.F3D,true),
	D3_3("3","3D组六",LotteryType.F3D,true),
	D3_1D("1D","1D",LotteryType.F3D,true),
	D3_2D("2D","2D",LotteryType.F3D,true),
	D3_3T1("3T1","三星通选中三",LotteryType.F3D,true),
	D3_3T2("3T2","三星通选中二",LotteryType.F3D,true),
	D3_HS0("HS0","和数0\27",LotteryType.F3D,true),
	D3_HS1("HS1","和数1\26",LotteryType.F3D,true),
	D3_HS2("HS2","和数2\25",LotteryType.F3D,true),
	D3_HS3("HS3","和数3\24",LotteryType.F3D,true),
	D3_HS4("HS4","和数4\23",LotteryType.F3D,true),
	D3_HS5("HS5","和数5\22",LotteryType.F3D,true),
	D3_HS6("HS6","和数6\21",LotteryType.F3D,true),
	D3_HS7("HS7","和数7\20",LotteryType.F3D,true),
	D3_HS8("HS8","和数8\19",LotteryType.F3D,true),
	D3_HS9("HS9","和数9\18",LotteryType.F3D,true),
	D3_HS10("HS10","和数10\17",LotteryType.F3D,true),
	D3_HS11("HS11","和数11\16",LotteryType.F3D,true),
	D3_HS12("HS12","和数12\15",LotteryType.F3D,true),
	D3_HS13("HS13","和数13\14",LotteryType.F3D,true),
	D3_DX("DX","猜大小",LotteryType.F3D,true),
	D3_JO("JO","猜奇偶",LotteryType.F3D,true),
	D3_ST("ST","猜三同",LotteryType.F3D,true),
	D3_TLJ("TLJ","拖拉机",LotteryType.F3D,true),
	D3_C1D1("C1D1","猜1D中1",LotteryType.F3D,true),
	D3_C1D2("C1D2","猜1D中2",LotteryType.F3D,true),
	D3_C1D3("C1D3","猜1D中3",LotteryType.F3D,true),
	D3_C2D_ET("C2D1","猜2D二同",LotteryType.F3D,true),
	D3_C2D_EBT("C2D2","猜2D二不同",LotteryType.F3D,true),
	
	
	QLC_1("1", "七乐彩一等奖",LotteryType.QLC,false), 
	QLC_2("2", "七乐彩二等奖",LotteryType.QLC,false), 
	QLC_3("3", "七乐彩三等奖",LotteryType.QLC,false), 
	QLC_4("4", "七乐彩四等奖",LotteryType.QLC,true), 
	QLC_5("5", "七乐彩五等奖",LotteryType.QLC,true), 
	QLC_6("6", "七乐彩六等奖",LotteryType.QLC,true),
	QLC_7("7", "七乐彩七等奖",LotteryType.QLC,true),
	
	
	P3_1("1","排列三直选",LotteryType.PL3,true),
	P3_2("2","排列三组三",LotteryType.PL3,true),
	P3_3("3","排列三组六",LotteryType.PL3,true),
	
	
	P5_1("1","排列五一等奖",LotteryType.PL5,true),
	
	
	QXC_1("1", "七星彩一等奖",LotteryType.QXC,false), 
	QXC_2("2", "七星彩二等奖",LotteryType.QXC,false), 
	QXC_3("3", "七星彩三等奖",LotteryType.QXC,true), 
	QXC_4("4", "七星彩四等奖",LotteryType.QXC,true), 
	QXC_5("5", "七星彩五等奖",LotteryType.QXC,true), 
	QXC_6("6", "七星彩六等奖",LotteryType.QXC,true), 
	
	
	DLT_1A("1A", "大乐透一等奖",LotteryType.CJDLT,false), 
	DLT_1B("1B", "大乐透一等奖追加",LotteryType.CJDLT,false), 
	DLT_2A("2A", "大乐透二等奖",LotteryType.CJDLT,false), 
	DLT_2B("2B", "大乐透二等奖追加",LotteryType.CJDLT,false), 
	DLT_3A("3A", "大乐透三等奖",LotteryType.CJDLT,false), 
	DLT_3B("3B", "大乐透三等奖追加",LotteryType.CJDLT,false), 
	DLT_4A("4A", "大乐透四等奖",LotteryType.CJDLT,true), 
	DLT_4B("4B", "大乐透四等奖追加",LotteryType.CJDLT,true), 
	DLT_5A("5A", "大乐透五等奖",LotteryType.CJDLT,true), 
	DLT_5B("5B", "大乐透五等奖追加",LotteryType.CJDLT,true), 
//	DLT_6("6", "大乐透六等奖",LotteryType.CJDLT,true),
	DLT_6A("6A", "大乐透六等奖",LotteryType.CJDLT,true),
	DLT_6B("6B", "大乐透六等奖追加",LotteryType.CJDLT,true),
	
	
	CQSSC_1D("1D","重庆时时彩一星",LotteryType.CQSSC,true),
	CQSSC_2D("2D","重庆时时彩二星",LotteryType.CQSSC,true),
	CQSSC_3D("3D","重庆时时彩三星",LotteryType.CQSSC,true),
	CQSSC_5D("5D","重庆时时彩五星",LotteryType.CQSSC,true),
	CQSSC_Z3("Z3","重庆时时彩组三",LotteryType.CQSSC,true),
	CQSSC_Z6("Z6","重庆时时彩组六",LotteryType.CQSSC,true),
	CQSSC_H2("H2","重庆时时彩二星直选和值",LotteryType.CQSSC,true),
	CQSSC_2Z("2Z","重庆时时彩二星组选",LotteryType.CQSSC,true),
	CQSSC_DD("DD","重庆时时彩大小单双",LotteryType.CQSSC,true),
	CQSSC_5T5("5T5","重庆时时彩五星通选中五20000",LotteryType.CQSSC,true),
	CQSSC_5T2("5T2","重庆时时彩五星通选中二20",LotteryType.CQSSC,true),
	CQSSC_5T3("5T3","重庆时时彩五星通选中二200",LotteryType.CQSSC,true),
	
	
	JXSSC_1D("1D","江西时时彩一星",LotteryType.JXSSC,true),
	JXSSC_2D("2D","江西时时彩二星",LotteryType.JXSSC,true),
	JXSSC_3D("3D","江西时时彩三星",LotteryType.JXSSC,true),
	JXSSC_4D("4D","江西时时彩四星",LotteryType.JXSSC,true),
	JXSSC_5D("5D","江西时时彩五星",LotteryType.JXSSC,true),
	JXSSC_Z3("Z3","江西时时彩组三",LotteryType.JXSSC,true),
	JXSSC_Z6("Z6","江西时时彩组六",LotteryType.JXSSC,true),
	JXSSC_H2("H2","江西时时彩二星直选和值",LotteryType.JXSSC,true),
	JXSSC_H3("H3","江西时时彩三星直选和值",LotteryType.JXSSC,true),
	JXSSC_2Z("2Z","江西时时彩二星组选",LotteryType.JXSSC,true),
	JXSSC_DD("DD","江西时时彩大小单双",LotteryType.JXSSC,true),
	JXSSC_5T5("5T5","江西时时彩五星通选中五20460",LotteryType.JXSSC,true),
	JXSSC_5T2("5T2","江西时时彩五星通选中二30",LotteryType.JXSSC,true),
	JXSSC_5T3("5T3","江西时时彩五星通选中二200",LotteryType.JXSSC,true),
	JXSSC_1R("1R","江西时时彩任选一",LotteryType.JXSSC,true),
	JXSSC_2R("2R","江西时时彩任选二",LotteryType.JXSSC,true),
	
	
	AHK3_ET_DAN("2","安徽快三二同号单式",LotteryType.AHK3,true),
	AHK3_ET_FU("6","安徽快三二同号复选",LotteryType.AHK3,true),
	AHK3_EBT_DAN("10","安徽快三二不同单式",LotteryType.AHK3,true),
	AHK3_ST_DAN("1","安徽快三三同号单式",LotteryType.AHK3,true),
	AHK3_ST_TONG("3","安徽快三三同号通选",LotteryType.AHK3,true),
	AHK3_SBT("4","安徽快三三不同单式",LotteryType.AHK3,true),
	AHK3_SLH_TONG("8","安徽快三三连号通选",LotteryType.AHK3,true),
	AHK3_HZ_H2("h2","安徽快三和值4\17",LotteryType.AHK3,true),
	AHK3_HZ_H3("h3","安徽快三和值5\16",LotteryType.AHK3,true),
	AHK3_HZ_H4("h4","安徽快三和值6\15",LotteryType.AHK3,true),
	AHK3_HZ_H5("h5","安徽快三和值7\14",LotteryType.AHK3,true),
	AHK3_HZ_H7("h7","安徽快三和值8\13",LotteryType.AHK3,true),
	AHK3_HZ_H8("h8","安徽快三和值9\12",LotteryType.AHK3,true),
	AHK3_HZ_H9("h9","安徽快三和值10\11",LotteryType.AHK3,true),
	
	
	GXK3_ET_DAN("2","广西快三二同号单式",LotteryType.GXK3,true),
	GXK3_ET_FU("6","广西快三二同号复选",LotteryType.GXK3,true),
	GXK3_EBT_DAN("10","广西快三二不同单式",LotteryType.GXK3,true),
	GXK3_ST_DAN("1","广西快三三同号单式",LotteryType.GXK3,true),
	GXK3_ST_TONG("3","广西快三三同号通选",LotteryType.GXK3,true),
	GXK3_SBT("4","广西快三三不同单式",LotteryType.GXK3,true),
	GXK3_SLH_TONG("8","广西快三三连号通选",LotteryType.GXK3,true),
	GXK3_HZ_H2("h2","广西快三和值4\17",LotteryType.GXK3,true),
	GXK3_HZ_H3("h3","广西快三和值5\16",LotteryType.GXK3,true),
	GXK3_HZ_H4("h4","广西快三和值6\15",LotteryType.GXK3,true),
	GXK3_HZ_H5("h5","广西快三和值7\14",LotteryType.GXK3,true),
	GXK3_HZ_H7("h7","广西快三和值8\13",LotteryType.GXK3,true),
	GXK3_HZ_H8("h8","广西快三和值9\12",LotteryType.GXK3,true),
	GXK3_HZ_H9("h9","广西快三和值10\11",LotteryType.GXK3,true),
	
	
	JSK3_ET_DAN("2","江苏快三二同号单式",LotteryType.JSK3,true),
	JSK3_ET_FU("6","江苏快三二同号复选",LotteryType.JSK3,true),
	JSK3_EBT_DAN("10","江苏快三二不同单式",LotteryType.JSK3,true),
	JSK3_ST_DAN("1","江苏快三三同号单式",LotteryType.JSK3,true),
	JSK3_ST_TONG("3","江苏快三三同号通选",LotteryType.JSK3,true),
	JSK3_SBT("4","江苏快三三不同单式",LotteryType.JSK3,true),
	JSK3_SLH_TONG("8","江苏快三三连号通选",LotteryType.JSK3,true),
	JSK3_HZ_H2("h2","江苏快三和值4\17",LotteryType.JSK3,true),
	JSK3_HZ_H3("h3","江苏快三和值5\16",LotteryType.JSK3,true),
	JSK3_HZ_H4("h4","江苏快三和值6\15",LotteryType.JSK3,true),
	JSK3_HZ_H5("h5","江苏快三和值7\14",LotteryType.JSK3,true),
	JSK3_HZ_H7("h7","江苏快三和值8\13",LotteryType.JSK3,true),
	JSK3_HZ_H8("h8","江苏快三和值9\12",LotteryType.JSK3,true),
	JSK3_HZ_H9("h9","江苏快三和值10\11",LotteryType.JSK3,true),
	
	NXK3_ET_DAN("2","宁夏快三二同号单式",LotteryType.NXK3,true),
	NXK3_ET_FU("6","宁夏快三二同号复选",LotteryType.NXK3,true),
	NXK3_EBT_DAN("10","宁夏快三二不同单式",LotteryType.NXK3,true),
	NXK3_ST_DAN("1","宁夏快三三同号单式",LotteryType.NXK3,true),
	NXK3_ST_TONG("3","宁夏快三三同号通选",LotteryType.NXK3,true),
	NXK3_SBT("4","宁夏快三三不同单式",LotteryType.NXK3,true),
	NXK3_SLH_TONG("8","宁夏快三三连号通选",LotteryType.NXK3,true),
	NXK3_HZ_H2("h2","宁夏快三和值4\17",LotteryType.NXK3,true),
	NXK3_HZ_H3("h3","宁夏快三和值5\16",LotteryType.NXK3,true),
	NXK3_HZ_H4("h4","宁夏快三和值6\15",LotteryType.NXK3,true),
	NXK3_HZ_H5("h5","宁夏快三和值7\14",LotteryType.NXK3,true),
	NXK3_HZ_H7("h7","宁夏快三和值8\13",LotteryType.NXK3,true),
	NXK3_HZ_H8("h8","宁夏快三和值9\12",LotteryType.NXK3,true),
	NXK3_HZ_H9("h9","宁夏快三和值10\11",LotteryType.NXK3,true),
	
	
	JX11X5_R2("R2","江西11选5任选2",LotteryType.JX_11X5,true),
	JX11X5_R3("R3","江西11选5任选3",LotteryType.JX_11X5,true),
	JX11X5_R4("R4","江西11选5任选4",LotteryType.JX_11X5,true),
	JX11X5_R5("R5","江西11选5任选5",LotteryType.JX_11X5,true),
	JX11X5_R6("R6","江西11选5任选6",LotteryType.JX_11X5,true),
	JX11X5_R7("R7","江西11选5任选7",LotteryType.JX_11X5,true),
	JX11X5_R8("R8","江西11选5任选8",LotteryType.JX_11X5,true),
	JX11X5_Q1("Q1","江西11选5前一",LotteryType.JX_11X5,true),
	JX11X5_Q2("Q2","江西11选5前二",LotteryType.JX_11X5,true),
	JX11X5_Q3("Q3","江西11选5前三",LotteryType.JX_11X5,true),
	JX11X5_Z2("Z2","江西11选5组二",LotteryType.JX_11X5,true),
	JX11X5_Z3("Z3","江西11选5组三",LotteryType.JX_11X5,true),
	
	
	SD11X5_R2("R2","山东11选5任选2",LotteryType.SD_11X5,true),
	SD11X5_R3("R3","山东11选5任选3",LotteryType.SD_11X5,true),
	SD11X5_R4("R4","山东11选5任选4",LotteryType.SD_11X5,true),
	SD11X5_R5("R5","山东11选5任选5",LotteryType.SD_11X5,true),
	SD11X5_R6("R6","山东11选5任选6",LotteryType.SD_11X5,true),
	SD11X5_R7("R7","山东11选5任选7",LotteryType.SD_11X5,true),
	SD11X5_R8("R8","山东11选5任选8",LotteryType.SD_11X5,true),
	SD11X5_Q1("Q1","山东11选5前一",LotteryType.SD_11X5,true),
	SD11X5_Q2("Q2","山东11选5前二",LotteryType.SD_11X5,true),
	SD11X5_Q3("Q3","山东11选5前三",LotteryType.SD_11X5,true),
	SD11X5_Z2("Z2","山东11选5组二",LotteryType.SD_11X5,true),
	SD11X5_Z3("Z3","山东11选5组三",LotteryType.SD_11X5,true),
	
	
	
	GD11X5_R2("R2","广东11选5任选2",LotteryType.GD_11X5,true),
	GD11X5_R3("R3","广东11选5任选3",LotteryType.GD_11X5,true),
	GD11X5_R4("R4","广东11选5任选4",LotteryType.GD_11X5,true),
	GD11X5_R5("R5","广东11选5任选5",LotteryType.GD_11X5,true),
	GD11X5_R6("R6","广东11选5任选6",LotteryType.GD_11X5,true),
	GD11X5_R7("R7","广东11选5任选7",LotteryType.GD_11X5,true),
	GD11X5_R8("R8","广东11选5任选8",LotteryType.GD_11X5,true),
	GD11X5_Q1("Q1","广东11选5前一",LotteryType.GD_11X5,true),
	GD11X5_Q2("Q2","广东11选5前二",LotteryType.GD_11X5,true),
	GD11X5_Q3("Q3","广东11选5前三",LotteryType.GD_11X5,true),
	GD11X5_Z2("Z2","广东11选5组二",LotteryType.GD_11X5,true),
	GD11X5_Z3("Z3","广东11选5组三",LotteryType.GD_11X5,true),
	
	
	
	TJSSC_1D("1D","天津时时彩一星",LotteryType.TJSSC,true),
	TJSSC_2D("2D","天津时时彩二星",LotteryType.TJSSC,true),
	TJSSC_3D("3D","天津时时彩三星",LotteryType.TJSSC,true),
	TJSSC_4D("4D","天津时时彩四星",LotteryType.TJSSC,true),
	TJSSC_5D("5D","天津时时彩五星",LotteryType.TJSSC,true),
	TJSSC_Z3("Z3","天津时时彩组三",LotteryType.TJSSC,true),
	TJSSC_Z6("Z6","天津时时彩组六",LotteryType.TJSSC,true),
	TJSSC_2Z("2Z","天津时时彩二星组选",LotteryType.TJSSC,true),
	TJSSC_DD("DD","天津时时彩大小单双",LotteryType.TJSSC,true),
	TJSSC_5T5("5T5","天津时时彩五星通选中五",LotteryType.TJSSC,true),
	TJSSC_5T2("5T2","天津时时彩五星通选中二",LotteryType.TJSSC,true),
	TJSSC_5T3("5T3","天津时时彩五星通选中三",LotteryType.TJSSC,true),
	TJSSC_QW1("QW1","天津时时彩趣味二星中1",LotteryType.TJSSC,true),
	TJSSC_QW2("QW2","天津时时彩趣味二星中2",LotteryType.TJSSC,true),
	TJSSC_QJ("QJ","天津时时彩区间二星",LotteryType.TJSSC,true),
	TJSSC_1R("1R","天津时时彩任选1",LotteryType.TJSSC,true),
	TJSSC_2R("2R","天津时时彩任选2",LotteryType.TJSSC,true),
	TJSSC_3R("3R","天津时时彩任选3",LotteryType.TJSSC,true),
	
	
	HLJK10_S1("S1","黑龙江快乐十分数投",LotteryType.HLJKL10,true),
	HLJK10_H1("H1","黑龙江快乐十分红投",LotteryType.HLJKL10,true),
	HLJK10_Q2("Q2","黑龙江快乐十分前二",LotteryType.HLJKL10,true),
	HLJK10_Q3("Q3","黑龙江快乐十分前三",LotteryType.HLJKL10,true),
	HLJK10_Z2("Z2","黑龙江快乐十分组二",LotteryType.HLJKL10,true),
	HLJK10_Z3("Z3","黑龙江快乐十分组三",LotteryType.HLJKL10,true),
	HLJK10_R2("R2","黑龙江快乐十分任二",LotteryType.HLJKL10,true),
	HLJK10_R3("R3","黑龙江快乐十分任三",LotteryType.HLJKL10,true),
	HLJK10_R4("R4","黑龙江快乐十分任四",LotteryType.HLJKL10,true),
	HLJK10_R5("R5","黑龙江快乐十分任五",LotteryType.HLJKL10,true),
	
	
	TJK10_S1("S1","天津快乐十分数投",LotteryType.TJKL10,true),
	TJK10_H1("H1","天津快乐十分红投",LotteryType.TJKL10,true),
	TJK10_Q2("Q2","天津快乐十分前二",LotteryType.TJKL10,true),
	TJK10_Q3("Q3","天津快乐十分前三",LotteryType.TJKL10,true),
	TJK10_Z2("Z2","天津快乐十分组二",LotteryType.TJKL10,true),
	TJK10_Z3("Z3","天津快乐十分组三",LotteryType.TJKL10,true),
	TJK10_R2("R2","天津快乐十分任二",LotteryType.TJKL10,true),
	TJK10_R3("R3","天津快乐十分任三",LotteryType.TJKL10,true),
	TJK10_R4("R4","天津快乐十分任四",LotteryType.TJKL10,true),
	TJK10_R5("R5","天津快乐十分任五",LotteryType.TJKL10,true),
	
	SDPK3_R1("R1","山东快乐扑克任选一",LotteryType.SD_PK3,true),
	SDPK3_R2("R2","山东快乐扑克任选二",LotteryType.SD_PK3,true),
	SDPK3_R3("R3","山东快乐扑克任选三",LotteryType.SD_PK3,true),
	SDPK3_R4("R4","山东快乐扑克任选四",LotteryType.SD_PK3,true),
	SDPK3_R5("R5","山东快乐扑克任选五",LotteryType.SD_PK3,true),
	SDPK3_R6("R6","山东快乐扑克任选六",LotteryType.SD_PK3,true),
	SDPK3_BX_TH("BX-TH","山东快乐扑克包选同花",LotteryType.SD_PK3,true),
	SDPK3_BX_THS("BX-THS","山东快乐扑克包选同花顺",LotteryType.SD_PK3,true),
	SDPK3_BX_SZ("BX-SZ","山东快乐扑克包选顺子",LotteryType.SD_PK3,true),
	SDPK3_BX_BZ("BX-BZ","山东快乐扑克包选豹子",LotteryType.SD_PK3,true),
	SDPK3_BX_DZ("BX-DZ","山东快乐扑克包选对子",LotteryType.SD_PK3,true),
	SDPK3_BZ("BZ","山东快乐扑克豹子",LotteryType.SD_PK3,true),
	SDPK3_DZ("DZ","山东快乐扑克对子",LotteryType.SD_PK3,true),
	SDPK3_SZ("SZ","山东快乐扑克顺子",LotteryType.SD_PK3,true),
	SDPK3_TH("TH","山东快乐扑克同花",LotteryType.SD_PK3,true),
	SDPK3_THS("THS","山东快乐扑克同花顺",LotteryType.SD_PK3,true),
	
	GXKL10_HY1("HY1","好运一",LotteryType.GXKL10,true),
	GXKL10_HY2("HY2","好运二",LotteryType.GXKL10,true),
	GXKL10_HY3("HY3","好运三",LotteryType.GXKL10,true),
	GXKL10_HY4("HY4","好运四",LotteryType.GXKL10,true),
	GXKL10_HY5("HY5","好运五",LotteryType.GXKL10,true),
	GXKL10_HYT("HYT","好运特",LotteryType.GXKL10,true),
	GXKL10_HYTX3_3("THY3-3","好运通选3中3",LotteryType.GXKL10,true),
	GXKL10_HYTX3_2("THY3-2","好运通选3中2",LotteryType.GXKL10,true),
	GXKL10_HYTX4_4("THY4-4","好运通选4中4",LotteryType.GXKL10,true),
	GXKL10_HYTX4_3("THY4-3","好运通选4中3",LotteryType.GXKL10,true),
	GXKL10_HYTX4_2("THY4-2","好运通选4中2",LotteryType.GXKL10,true),
	GXKL10_HYTX5_5("THY5-5","好运通选5中5",LotteryType.GXKL10,true),
	GXKL10_HYTX5_4("THY5-4","好运通选5中4",LotteryType.GXKL10,true),
	GXKL10_HYTX5_3("THY5-3","好运通选5中3",LotteryType.GXKL10,true),
	
	GXKL10_HY3BX4_3("HY3BX4-3","好运三包选4中3",LotteryType.GXKL10,true),
	GXKL10_HY3BX4_4("HY3BX4-4","好运三包选4中4",LotteryType.GXKL10,true),
	GXKL10_HY3BX5_3("HY3BX5-3","好运三包选5中3",LotteryType.GXKL10,true),
	GXKL10_HY3BX5_4("HY3BX5-4","好运三包选5中4",LotteryType.GXKL10,true),
	GXKL10_HY3BX5_5("HY3BX5-5","好运三包选5中5",LotteryType.GXKL10,true),
	GXKL10_HY3BX6_3("HY3BX6-3","好运三包选6中3",LotteryType.GXKL10,true),
	GXKL10_HY3BX6_4("HY3BX6-4","好运三包选6中4",LotteryType.GXKL10,true),
	GXKL10_HY3BX6_5("HY3BX6-5","好运三包选6中5",LotteryType.GXKL10,true),
	
	GXKL10_HY4BX5_4("HY4BX5-4","好运四包选5中4",LotteryType.GXKL10,true),
	GXKL10_HY4BX5_5("HY4BX5-5","好运四包选5中5",LotteryType.GXKL10,true),
	GXKL10_HY4BX6_4("HY4BX6-4","好运四包选6中4",LotteryType.GXKL10,true),
	GXKL10_HY4BX6_5("HY4BX6-5","好运四包选6中5",LotteryType.GXKL10,true),
	GXKL10_HY4BX7_4("HY4BX7-4","好运四包选7中4",LotteryType.GXKL10,true),
	GXKL10_HY4BX7_5("HY4BX7-5","好运四包选7中5",LotteryType.GXKL10,true),
	GXKL10_HY4BX8_4("HY4BX8-4","好运四包选8中4",LotteryType.GXKL10,true),
	GXKL10_HY4BX8_5("HY4BX8-5","好运四包选8中5",LotteryType.GXKL10,true),
	GXKL10_HY4BX9_4("HY4BX9-4","好运四包选9中4",LotteryType.GXKL10,true),
	GXKL10_HY4BX9_5("HY4BX9-5","好运四包选9中5",LotteryType.GXKL10,true),
	GXKL10_HY4BX10_4("HY4BX10-4","好运四包选10中4",LotteryType.GXKL10,true),
	GXKL10_HY4BX10_5("HY4BX10-5","好运四包选10中5",LotteryType.GXKL10,true),
	
	GXKL10_HY5BX6_5("HY5BX6-5","好运五包选6中5",LotteryType.GXKL10,true),
	GXKL10_HY5BX7_5("HY5BX7-5","好运五包选7中5",LotteryType.GXKL10,true),
	GXKL10_HY5BX8_5("HY5BX8-5","好运五包选8中5",LotteryType.GXKL10,true),
	GXKL10_HY5BX9_5("HY5BX9-5","好运五包选9中5",LotteryType.GXKL10,true),
	GXKL10_HY5BX10_5("HY5BX10-5","好运五包选10中5",LotteryType.GXKL10,true),
	
	

	all("0","所有",LotteryType.ALL,false);
	public String value;
	public String name;
	public LotteryType lotteryType;
	public boolean isStatic;
	LotteryDrawPrizeAwarder(String value,String name,LotteryType lotteryType,boolean isStatic){
		this.value = value;
		this.name = name;
		this.lotteryType=lotteryType;
		this.isStatic=isStatic;
	}
	
	public static List<LotteryDrawPrizeAwarder> getByLotteryType(LotteryType lotteryType){
		List<LotteryDrawPrizeAwarder> prizeAwarderList=new ArrayList<LotteryDrawPrizeAwarder>();
		for(LotteryDrawPrizeAwarder drawPrizeAwarder:LotteryDrawPrizeAwarder.values()){
			if(drawPrizeAwarder.lotteryType==lotteryType){
				prizeAwarderList.add(drawPrizeAwarder);
			}
		}
		return prizeAwarderList;
	}
	
	public static LotteryDrawPrizeAwarder get(String value,LotteryType lotteryType){
		for(LotteryDrawPrizeAwarder drawPrizeAwarder:LotteryDrawPrizeAwarder.values()){
			if(drawPrizeAwarder.lotteryType==lotteryType&&drawPrizeAwarder.value.equals(value)){
				return drawPrizeAwarder;
			}
		}
		return null;
	}
}
