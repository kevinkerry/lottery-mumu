package com.lottery.scheduler.fetcher.sp.zch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lottery.common.contains.CharsetConstant;
import com.lottery.common.contains.LotteryConstant;
import com.lottery.common.contains.lottery.LotteryType;
import com.lottery.common.util.HTTPUtil;
import com.lottery.scheduler.fetcher.sp.IGetSpData;
import com.lottery.scheduler.fetcher.sp.domain.MatchSpDomain;
import com.lottery.ticket.IVenderConfig;
import com.lottery.ticket.sender.worker.XmlParse;
import com.lottery.ticket.vender.impl.zch.ZchConfig;

public class GetSpFromZch implements IGetSpData {

	private static Logger logger=LoggerFactory.getLogger(GetSpFromZch.class);
	
	@Override
	public List<MatchSpDomain> getJczqStatic(IVenderConfig config) {
		String jcUrl = ((ZchConfig)config).getJcUrl();
		String url = jcUrl + "/200.xml";
		List<MatchSpDomain> retList = new ArrayList<MatchSpDomain>();
		try {
			String matchStr = HTTPUtil.post(url, "" ,CharsetConstant.CHARSET_UTF8);
			XmlParse body = new XmlParse(matchStr, "root", "", "");
			Element el = body.getRootElement();
			// 时间戳 20140409095046
			Iterator<Element> packages = el.elementIterator("package");
			while (packages.hasNext()) {
				Element packag = packages.next();
				// 日期 20140410
				String date = packag.elementText("date");
				Iterator<Element> matchs = packag.elementIterator("match");
				while (matchs.hasNext()) {
					Element match = matchs.next();
					// 当天赛事场次编号
					String sn = match.attributeValue("sn");
					// 选号方式(购买方式) 02是串关 01是单关 如果是02playCode是00
					String pollCode = match.attributeValue("pollCode");
					if ("02".equals(pollCode)) {

						// 串关sp
						MatchSpDomain jczqStaticSp = new MatchSpDomain();
						jczqStaticSp.setMatchNum(date + sn);

						Map<String, Map<String, Object>> lottery_type = new HashMap<String, Map<String, Object>>();

						Map<String, Object> spfMap = new HashMap<String, Object>();
						Element winOrNega = match.element("winOrNega");
						// 让球胜平负-负
						String nega = winOrNega.attributeValue("nega");
						spfMap.put(LotteryConstant.JCZQ_SPF_F_VALUE, nega);
						// 让球胜平负-平
						String flat = winOrNega.attributeValue("flat");
						spfMap.put(LotteryConstant.JCZQ_SPF_P_VALUE, flat);
						// 让球胜平负-胜
						String win = winOrNega.attributeValue("win");
						spfMap.put(LotteryConstant.JCZQ_SPF_S_VALUE, win);
						lottery_type.put(LotteryType.JCZQ_RQ_SPF.value+"", spfMap);

						Map<String, Object> spfMap2 = new HashMap<String, Object>();
						Element spfWinOrNega = match.element("spfWinOrNega");
						// 胜平负-负
						String spfNega = spfWinOrNega.attributeValue("spfNega");
						spfMap2.put(LotteryConstant.JCZQ_SPF_WRQ_F_VALUE,
								spfNega);
						// 胜平负-平
						String spfFlat = spfWinOrNega.attributeValue("spfFlat");
						spfMap2.put(LotteryConstant.JCZQ_SPF_WRQ_P_VALUE,
								spfFlat);
						// 胜平负-胜
						String spfWin = spfWinOrNega.attributeValue("spfWin");
						spfMap2.put(LotteryConstant.JCZQ_SPF_WRQ_S_VALUE,
								spfWin);
						lottery_type.put(LotteryType.JCZQ_SPF_WRQ.value+"", spfMap2);

						Map<String, Object> bfMap = new HashMap<String, Object>();
						Element score = match.element("score");
						// 比分-负其它
						String sp_f_qt = score.attributeValue("sp_f_qt");
						bfMap.put(LotteryConstant.JCZQ_BF_ZF_QT_VALUE, sp_f_qt);
						// 比分-平其它
						String sp_p_qt = score.attributeValue("sp_p_qt");
						bfMap.put(LotteryConstant.JCZQ_BF_ZP_QT_VALUE, sp_p_qt);
						// 比分-胜其它
						String sp_s_qt = score.attributeValue("sp_s_qt");
						bfMap.put(LotteryConstant.JCZQ_BF_ZS_QT_VALUE, sp_s_qt);
						// 比分-0:0
						String sp_00 = score.attributeValue("sp_00");
						bfMap.put(LotteryConstant.JCZQ_BF_ZP_0_0_VALUE, sp_00);
						// 比分-0:1
						String sp_01 = score.attributeValue("sp_01");
						bfMap.put(LotteryConstant.JCZQ_BF_ZF_0_1_VALUE, sp_01);
						// sp_02 比分-0:2
						String sp_02 = score.attributeValue("sp_02");
						bfMap.put(LotteryConstant.JCZQ_BF_ZF_0_2_VALUE, sp_02);
						// sp_03 比分-0:3
						String sp_03 = score.attributeValue("sp_03");
						bfMap.put(LotteryConstant.JCZQ_BF_ZF_0_3_VALUE, sp_03);
						// sp_04 比分-0:4
						String sp_04 = score.attributeValue("sp_04");
						bfMap.put(LotteryConstant.JCZQ_BF_ZF_0_4_VALUE, sp_04);
						// sp_05 比分-0:5
						String sp_05 = score.attributeValue("sp_05");
						bfMap.put(LotteryConstant.JCZQ_BF_ZF_0_5_VALUE, sp_05);
						// sp_10 比分-1:0
						String sp_10 = score.attributeValue("sp_10");
						bfMap.put(LotteryConstant.JCZQ_BF_ZS_1_0_VALUE, sp_10);
						// sp_11 比分-1:1
						String sp_11 = score.attributeValue("sp_11");
						bfMap.put(LotteryConstant.JCZQ_BF_ZP_1_1_VALUE, sp_11);
						// sp_12 比分-1:2
						String sp_12 = score.attributeValue("sp_12");
						bfMap.put(LotteryConstant.JCZQ_BF_ZF_1_2_VALUE, sp_12);
						// sp_13 比分-1:3
						String sp_13 = score.attributeValue("sp_13");
						bfMap.put(LotteryConstant.JCZQ_BF_ZF_1_3_VALUE, sp_13);
						// sp_14 比分-1:4
						String sp_14 = score.attributeValue("sp_14");
						bfMap.put(LotteryConstant.JCZQ_BF_ZF_1_4_VALUE, sp_14);
						// sp_15 比分-1:5
						String sp_15 = score.attributeValue("sp_15");
						bfMap.put(LotteryConstant.JCZQ_BF_ZF_1_5_VALUE, sp_15);
						// sp_20 比分-2:0
						String sp_20 = score.attributeValue("sp_20");
						bfMap.put(LotteryConstant.JCZQ_BF_ZS_2_0_VALUE, sp_20);
						// sp_21 比分-2:1
						String sp_21 = score.attributeValue("sp_21");
						bfMap.put(LotteryConstant.JCZQ_BF_ZS_2_1_VALUE, sp_21);
						// sp_22 比分-2:2
						String sp_22 = score.attributeValue("sp_22");
						bfMap.put(LotteryConstant.JCZQ_BF_ZP_2_2_VALUE, sp_22);
						// sp_23 比分-2:3
						String sp_23 = score.attributeValue("sp_23");
						bfMap.put(LotteryConstant.JCZQ_BF_ZF_2_3_VALUE, sp_23);
						// sp_24 比分-2:4
						String sp_24 = score.attributeValue("sp_24");
						bfMap.put(LotteryConstant.JCZQ_BF_ZF_2_4_VALUE, sp_24);
						// sp_25 比分-2:5
						String sp_25 = score.attributeValue("sp_25");
						bfMap.put(LotteryConstant.JCZQ_BF_ZF_2_5_VALUE, sp_25);
						// sp_30 比分-3:0
						String sp_30 = score.attributeValue("sp_30");
						bfMap.put(LotteryConstant.JCZQ_BF_ZS_3_0_VALUE, sp_30);
						// sp_31 比分-3:1
						String sp_31 = score.attributeValue("sp_31");
						bfMap.put(LotteryConstant.JCZQ_BF_ZS_3_1_VALUE, sp_31);
						// sp_32 比分-3:2
						String sp_32 = score.attributeValue("sp_32");
						bfMap.put(LotteryConstant.JCZQ_BF_ZS_3_2_VALUE, sp_32);
						// sp_33 比分-3:3
						String sp_33 = score.attributeValue("sp_33");
						bfMap.put(LotteryConstant.JCZQ_BF_ZP_3_3_VALUE, sp_33);
						// sp_40 比分-4:0
						String sp_40 = score.attributeValue("sp_40");
						bfMap.put(LotteryConstant.JCZQ_BF_ZS_4_0_VALUE, sp_40);
						// sp_41 比分-4:1
						String sp_41 = score.attributeValue("sp_41");
						bfMap.put(LotteryConstant.JCZQ_BF_ZS_4_1_VALUE, sp_41);
						// sp_42 比分-4:2
						String sp_42 = score.attributeValue("sp_42");
						bfMap.put(LotteryConstant.JCZQ_BF_ZS_4_2_VALUE, sp_42);
						// sp_50 比分-5:0
						String sp_50 = score.attributeValue("sp_50");
						bfMap.put(LotteryConstant.JCZQ_BF_ZS_5_0_VALUE, sp_50);
						// sp_51 比分-5:1
						String sp_51 = score.attributeValue("sp_51");
						bfMap.put(LotteryConstant.JCZQ_BF_ZS_5_1_VALUE, sp_51);
						// sp_52 比分-5:2
						String sp_52 = score.attributeValue("sp_52");
						bfMap.put(LotteryConstant.JCZQ_BF_ZS_5_2_VALUE, sp_52);
						lottery_type.put(LotteryType.JCZQ_BF.value+"", bfMap);

						Map<String, Object> zjqMap = new HashMap<String, Object>();
						Element totalGoal = match.element("totalGoal");
						// 总进球-进0个
						String tg_0 = totalGoal.attributeValue("tg_0");
						zjqMap.put(LotteryConstant.JCZQ_JQS_0_VALUE, tg_0);
						// 总进球-进1个
						String tg_1 = totalGoal.attributeValue("tg_1");
						zjqMap.put(LotteryConstant.JCZQ_JQS_1_VALUE, tg_1);
						// 总进球-进2个
						String tg_2 = totalGoal.attributeValue("tg_2");
						zjqMap.put(LotteryConstant.JCZQ_JQS_2_VALUE, tg_2);
						// 总进球-进3个
						String tg_3 = totalGoal.attributeValue("tg_3");
						zjqMap.put(LotteryConstant.JCZQ_JQS_3_VALUE, tg_3);
						// 总进球-进4个
						String tg_4 = totalGoal.attributeValue("tg_4");
						zjqMap.put(LotteryConstant.JCZQ_JQS_4_VALUE, tg_4);
						// 总进球-进5个
						String tg_5 = totalGoal.attributeValue("tg_5");
						zjqMap.put(LotteryConstant.JCZQ_JQS_5_VALUE, tg_5);
						// 总进球-进6个
						String tg_6 = totalGoal.attributeValue("tg_6");
						zjqMap.put(LotteryConstant.JCZQ_JQS_6_VALUE, tg_6);
						// 总进球-进7+个
						String tg_7 = totalGoal.attributeValue("tg_7");
						zjqMap.put(LotteryConstant.JCZQ_JQS_7_VALUE, tg_7);
						lottery_type.put(LotteryType.JCZQ_JQS.value+"", zjqMap);

						Map<String, Object> bqcMap = new HashMap<String, Object>();
						Element halfCourt = match.element("halfCourt");
						// 半场胜平负-负负
						String hc_ff = halfCourt.attributeValue("hc_ff");
						bqcMap.put(LotteryConstant.JCZQ_BQC_FF_VALUE, hc_ff);
						// 半场胜平负-负平
						String hc_fp = halfCourt.attributeValue("hc_fp");
						bqcMap.put(LotteryConstant.JCZQ_BQC_FP_VALUE, hc_fp);
						// 半场胜平负-负胜
						String hc_fs = halfCourt.attributeValue("hc_fs");
						bqcMap.put(LotteryConstant.JCZQ_BQC_FS_VALUE, hc_fs);
						// 半场胜平负-平负
						String hc_pf = halfCourt.attributeValue("hc_pf");
						bqcMap.put(LotteryConstant.JCZQ_BQC_PF_VALUE, hc_pf);
						// 半场胜平负-平平
						String hc_pp = halfCourt.attributeValue("hc_pp");
						bqcMap.put(LotteryConstant.JCZQ_BQC_PP_VALUE, hc_pp);
						// 半场胜平负-平胜
						String hc_ps = halfCourt.attributeValue("hc_ps");
						bqcMap.put(LotteryConstant.JCZQ_BQC_PS_VALUE, hc_ps);
						// 半场胜平负-胜负
						String hc_sf = halfCourt.attributeValue("hc_sf");
						bqcMap.put(LotteryConstant.JCZQ_BQC_SF_VALUE, hc_sf);
						// 半场胜平负-胜平
						String hc_sp = halfCourt.attributeValue("hc_sp");
						bqcMap.put(LotteryConstant.JCZQ_BQC_SP_VALUE, hc_sp);
						// 半场胜平负-胜胜
						String hc_ss = halfCourt.attributeValue("hc_ss");
						bqcMap.put(LotteryConstant.JCZQ_BQC_SS_VALUE, hc_ss);

						lottery_type.put(LotteryType.JCZQ_BQC.value+"", bqcMap);

						jczqStaticSp.setLotteryType(lottery_type);
						retList.add(jczqStaticSp);
					}
				}
			}
		} catch (Exception e) {
			logger.error("竞彩足球抓取固定sp出错",e);
		}

		return retList;
	}

	
	@Override
	public List<MatchSpDomain> getJclqStatic(IVenderConfig config) {
		String jcUrl = ((ZchConfig)config).getJcUrl();
		String url = jcUrl + "/201.xml";
		List<MatchSpDomain> sps = new ArrayList<MatchSpDomain>();
		try {
			String matchStr = HTTPUtil.post(url, "", CharsetConstant.CHARSET_UTF8);
			XmlParse body = new XmlParse(matchStr, "root", "", "");
			Element el = body.getRootElement();
			Iterator<Element> packages = el.elementIterator("package");
			while (packages.hasNext()) {
				Element packag = packages.next();
				//日期 20140410
				String date = packag.elementText("date");
				Iterator<Element> matchs = packag.elementIterator("match");
				while (matchs.hasNext()) {
					Element match = matchs.next();
					//选号方式(购买方式)
					String pollCode = match.attributeValue("pollCode");
					//当天赛事场次编号
					String sn = match.attributeValue("sn");
					String letBall = match.attributeValue("letBall");
					String preCast = match.attributeValue("preCast");
					
					MatchSpDomain sp = new MatchSpDomain();
					sp.setMatchNum(date + sn);
					Map<String, Map<String, Object>> lottery_type = new HashMap<String, Map<String, Object>>();
					if("02".equals(pollCode)){
						Element winOrNega = match.element("winOrNega");
						Map<String, Object> winOrNegasp = new HashMap<String, Object>();
						//胜负-负
						String nega = winOrNega.attributeValue("nega");
						winOrNegasp.put(LotteryConstant.JCLQ_SF_F_VALUE, nega);
						//胜负-胜
						String win = winOrNega.attributeValue("win");
						winOrNegasp.put(LotteryConstant.JCLQ_SF_S_VALUE, win);
						lottery_type.put(LotteryType.JCLQ_SF.value+"", winOrNegasp);
						
						Element winNegaDiff  = match.element("winNegaDiff");
						Map<String, Object> winNegaDiffmap = new HashMap<String, Object>();
						//胜差分-主胜1~5分
						String s1_5 = winNegaDiff.attributeValue("s1-5");
						winNegaDiffmap.put(LotteryConstant.JCLQ_SFC_H_1_5_VALUE, s1_5);
						//胜差分-主胜6~10分
						String s6_10 = winNegaDiff.attributeValue("s6-10");
						winNegaDiffmap.put(LotteryConstant.JCLQ_SFC_H_6_10_VALUE, s6_10);
						//s11-15 胜差分-主胜11~15分
						String s11_15 = winNegaDiff.attributeValue("s11-15");
						winNegaDiffmap.put(LotteryConstant.JCLQ_SFC_H_11_15_VALUE, s11_15);
						//s16-20 胜差分-主胜16~20分
						String s16_20 = winNegaDiff.attributeValue("s16-20");
						winNegaDiffmap.put(LotteryConstant.JCLQ_SFC_H_16_20_VALUE, s16_20);
						//s21-25 胜差分-主胜21~25分
						String s21_25 = winNegaDiff.attributeValue("s21-25");
						winNegaDiffmap.put(LotteryConstant.JCLQ_SFC_H_21_25_VALUE, s21_25);
						//s26 胜差分-主胜26分以上
						String s26 = winNegaDiff.attributeValue("s26");
						winNegaDiffmap.put(LotteryConstant.JCLQ_SFC_H_26_PLUS_VALUE, s26);
						//f1-5 胜差分-客胜1~5分
						String f1_5 = winNegaDiff.attributeValue("f1-5");
						winNegaDiffmap.put(LotteryConstant.JCLQ_SFC_A_1_5_VALUE, f1_5);
						//f6-10 胜差分-客胜6~10分
						String f6_10 = winNegaDiff.attributeValue("f6-10");
						winNegaDiffmap.put(LotteryConstant.JCLQ_SFC_A_6_10_VALUE, f6_10);
						//f11-15 胜差分-客胜11~15分
						String f11_15 = winNegaDiff.attributeValue("f11-15");
						winNegaDiffmap.put(LotteryConstant.JCLQ_SFC_A_11_15_VALUE, f11_15);
						//f16-20 胜差分-客胜16~20分
						String f16_20 = winNegaDiff.attributeValue("f16-20");
						winNegaDiffmap.put(LotteryConstant.JCLQ_SFC_A_16_20_VALUE, f16_20);
						//f21-25 胜差分-客胜21~25分
						String f21_25 = winNegaDiff.attributeValue("f21-25");
						winNegaDiffmap.put(LotteryConstant.JCLQ_SFC_A_21_25_VALUE, f21_25);
						//f26 胜差分-客胜26分以上
						String f26 = winNegaDiff.attributeValue("f26");
						winNegaDiffmap.put(LotteryConstant.JCLQ_SFC_A_26_PLUS_VALUE, f26);
						lottery_type.put(LotteryType.JCLQ_SFC.value+"", winNegaDiffmap);
						
						Element letWinOrNega = match.element("letWinOrNega");
						Map<String, Object> letWinOrNegamap = new HashMap<String, Object>();
						//让负
						String letNega = letWinOrNega.attributeValue("letNega");
						letWinOrNegamap.put(LotteryConstant.JCLQ_RFSF_F_VALUE, letNega);
						//让胜
						String letWin = letWinOrNega.attributeValue("letWin");
						letWinOrNegamap.put(LotteryConstant.JCLQ_RFSF_S_VALUE, letWin);
						letWinOrNegamap.put(LotteryConstant.JCLQ_RFSF_HANDICAP, letBall);
						lottery_type.put(LotteryType.JCLQ_RFSF.value+"", letWinOrNegamap);
						
						Element bigOrLittle = match.element("bigOrLittle");
						Map<String, Object> bigOrLittlemap = new HashMap<String, Object>();
						//大
						String big = bigOrLittle.attributeValue("big");
						bigOrLittlemap.put(LotteryConstant.JCLQ_DXF_LARGE, big);
						//小
						String little = bigOrLittle.attributeValue("little");
						bigOrLittlemap.put(LotteryConstant.JCLQ_DXF_SMALL, little);
						bigOrLittlemap.put(LotteryConstant.JCLQ_DXF_PRESETSCORE, preCast);
						lottery_type.put(LotteryType.JCLQ_DXF.value+"", bigOrLittlemap);
						
						sp.setLotteryType(lottery_type);
						sps.add(sp);
					}
				}
			}
		} catch (Exception e) {
			logger.error("竞彩篮球 固定sp出错",e);
		}
		return sps;
	}

}
