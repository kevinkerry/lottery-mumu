package com.lottery.lottype;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.lottery.common.contains.ErrorCode;
import com.lottery.common.contains.lottery.LotteryType;
import com.lottery.common.contains.lottery.PlayType;
import com.lottery.common.exception.LotteryException;
import com.lottery.common.util.StringUtil;
import com.lottery.core.domain.LotteryPhase;
import com.lottery.lottype.zc.sfc.Zucaisfc01;
import com.lottery.lottype.zc.sfc.Zucaisfc02;

@Component("4001")
public class Zcsfc extends AbstractLot{

	private Logger logger = LoggerFactory.getLogger(Zcsfc.class);
	
	Map<String, LotPlayType> map = new HashMap<String, LotPlayType>();
	{
		map.put("400101", new Zucaisfc01());
		map.put("400102", new Zucaisfc02());
	}
	
	@Override
	public boolean validate(String betcode, BigDecimal amount,
			BigDecimal beishu, int oneAmount) {
		if(validateBasicBetcode(betcode)==false) {
			return false;
		}
		
		long totalAmt = 0;
		for(String code:betcode.split("!")) {
			LotPlayType type = map.get(code.substring(0, 6));
			if(null==type) {
				logger.error("注码金额校验错误betcode={} beishu={} amount={} oneAmout={},玩法不存在",new Object[]{betcode,beishu.intValue(),amount,oneAmount});
				throw new LotteryException(ErrorCode.betamount_error, ErrorCode.betamount_error.memo);
			}
			totalAmt = totalAmt + type.getSingleBetAmount(code, beishu, oneAmount);
		}
		return amount.longValue()==totalAmt;
	}

	@Override
	public List<SplitedLot> split(String betcode, int lotmulti, long amt,
			int oneAmount) {
		List<SplitedLot> splitedLots = new ArrayList<SplitedLot>();
		
		long totalAmt = 0;
		if(isZcSingleUpload(betcode, String.valueOf(PlayType.zc_sfc_dan.value))) {
			LotPlayType type = map.get(betcode.substring(0, 6));
			splitedLots.addAll(type.splitByType(betcode, lotmulti, oneAmount));
		} else {
			for(String code:betcode.split("!")) {
				LotPlayType type = map.get(code.substring(0, 6));
				splitedLots.addAll(type.splitByType(code, lotmulti, oneAmount));
			}
		}
				// 验证过程
		for (SplitedLot s : splitedLots) {
			// 验证拆分后的倍数和金额的限制
			if(s.getAmt()>2000000||s.getLotMulti()>99) {
				logger.error("split amt_lotmulti err:betcode={} lotmulti={} amt={}",new Object[]{s.getBetcode(),String.valueOf(s.getLotMulti()),String.valueOf(s.getAmt())});
				throw new LotteryException(ErrorCode.betamount_error, ErrorCode.betamount_error.memo);
			}
			// 验证注码格式和金额
//			if (!validate(s.getBetcode(), new BigDecimal(s.getAmt()),
//					new BigDecimal(s.getLotMulti()), oneAmount) ) {
//				logger.error("split validate err:betcode={} lotmulti={} amt={}",new String[]{s.getBetcode(),String.valueOf(s.getLotMulti()),String.valueOf(s.getAmt())});
//				throw new TiantiancaiException(ErrorCode.Betcode_Error, ErrorCode.Betcode_Error.memo);
//			}
			totalAmt = totalAmt + s.getAmt();
		}

		// 验证金额
		if (totalAmt != amt) {
			logger.error("split amt_equal totalAmt={} amt={}",new Object[]{String.valueOf(totalAmt),String.valueOf(amt)});
			throw new LotteryException(ErrorCode.betamount_error, ErrorCode.betamount_error.memo);
		}

		return splitedLots;
	}

	@Override
	public boolean isBigPrize(String prizeinfo, BigDecimal preprizeamt,
			BigDecimal afterprizeamt) {
		if(preprizeamt.intValue()>=1000000) {
			return true;
		}
		return false;
	}

	@Override
	public LotteryType getLotteryType() {
		return LotteryType.ZC_SFC;
	}
	
	
	@Override
	public String getPrizeLevelInfo(String betcode, String wincode,
			BigDecimal lotmulti,int oneAmount) {
		String playtype = betcode.substring(0, 6);
		LotPlayType type = map.get(playtype);
		return combinePrizeInfo(type.caculatePrizeLevel(betcode, wincode,oneAmount), lotmulti);
	}
	
	@Override
	public BigDecimal computeAchievement(BigDecimal amt, BigDecimal preTaxAmount, BigDecimal afterTaxAmount) {
		BigDecimal result = BigDecimal.ZERO;
		if (amt == null || preTaxAmount == null || afterTaxAmount == null) {
			return result;
		}
		// 盈利=税后奖金-方案金额
		BigDecimal afterAmt = afterTaxAmount.add(amt.negate());
		// 盈利回报 = 税后奖金/方案金额
		BigDecimal afterrate = BigDecimal.ZERO;
		if (amt.compareTo(BigDecimal.ZERO) > 0) {
			afterrate = afterTaxAmount.divide(amt, 0, BigDecimal.ROUND_HALF_UP);
			if (afterAmt.compareTo(new BigDecimal(80000000)) >= 0 || afterrate.compareTo(new BigDecimal(500)) >= 0) {// 税后盈利达80万或回报达到500倍，获得两颗金星
				result = result.add(new BigDecimal(20));
			} else if ((afterAmt.compareTo(new BigDecimal(5000000)) >= 0 && afterrate.compareTo(new BigDecimal(2)) >= 0)
					|| (afterAmt.compareTo(new BigDecimal(500000)) >= 0 && afterrate.compareTo(new BigDecimal(10)) >= 0)) {// 税后盈利达到5万且回报2倍以上，或盈利达到5000且回报10倍以上
				result = result.add(new BigDecimal(10));
			}
			if (preTaxAmount.compareTo(new BigDecimal(450000000)) >= 0) {// 当期中得450万元税前奖金，则再获得一颗金星。
				result = result.add(new BigDecimal(10));
			}
		}
		return result;
	}

	@Override
	public Map<String, Long> getAwardLevels(int lotterytype, String phase) {
		LotteryPhase lotteryPhase = phaseDao.getByTypeAndPhase(lotterytype, phase);
		String[] infos1 = lotteryPhase.getPrizeDetail().split(",")[0].split("\\_");
		String[] infos2 = lotteryPhase.getPrizeDetail().split(",")[1].split("\\_");
		Map<String, Long> map = new HashMap<String,Long>();
		map.put("1", Long.parseLong(infos1[2])*100);
		map.put("2", Long.parseLong(infos2[2])*100);
		return map;
	}
	
	
	@Override
	public boolean validatePrizeDetail(String prizedetail) {
		if(StringUtil.isEmpty(prizedetail)) {
			return false;
		}
		String regex = "1_\\d+_\\d+,2_\\d+_\\d+";
		return prizedetail.matches(regex);
	}
}
