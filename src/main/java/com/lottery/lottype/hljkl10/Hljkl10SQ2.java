package com.lottery.lottype.hljkl10;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.lottery.common.contains.ErrorCode;
import com.lottery.common.contains.lottery.LotteryDrawPrizeAwarder;
import com.lottery.common.contains.lottery.PlayType;
import com.lottery.common.exception.LotteryException;
import com.lottery.lottype.SplitedLot;

public class Hljkl10SQ2 extends Hljkl10X {

	@Override
	public String caculatePrizeLevel(String betcode, String wincode,int oneAmount) {
		betcode = betcode.substring(7);
		StringBuilder sb = new StringBuilder("");
		String[] wincodes = wincode.split(",");
		for (String code : betcode.split("\\^")) {
			int a = -2;
			int b = -2;
			for(int i=0,j=wincodes.length;i<j;i++) {
				if(wincodes[i].equals(code.substring(0, 2))) {
					a = i;
					continue;
				}
				if(wincodes[i].equals(code.substring(3, 5))) {
					b = i;
				}
			}
			if(b-a==1) {
				sb.append(LotteryDrawPrizeAwarder.HLJK10_Q2.value).append(",");
			}
		}
		if (sb.length() > 0) {
			sb.deleteCharAt(sb.length() - 1);
		}
		return sb.toString();
	}

	@Override
	public long getSingleBetAmount(String betcode, BigDecimal beishu,
			int oneAmount) {
		betcode = betcode.substring(7);
		for (String code : betcode.split("\\^")) {
			if (!validateBetcode(code, SQ2)) {
				throw new LotteryException(ErrorCode.betamount_error, ErrorCode.betamount_error.memo);
			}
		}
		return betcode.split("\\^").length * beishu.longValue() * 200;
	}

	@Override
	public List<SplitedLot> splitByType(String betcode, int lotmulti,
			int oneAmount) {
		List<SplitedLot> list = new ArrayList<SplitedLot>();
		List<SplitedLot> zhumaList = transformSingle5(betcode,lotmulti,PlayType.hljkl10_sq2);
		
		for(SplitedLot splitedLot:zhumaList) {
			if(!SplitedLot.isToBeSplit99(splitedLot.getLotMulti(),splitedLot.getAmt())) {
				list.add(splitedLot);
			}else {
				list.addAll(SplitedLot.splitToPermissionMulti(splitedLot.getBetcode(), splitedLot.getLotMulti(), 50,lotterytype));
			}
		}
		for(SplitedLot s:list) {
			s.setAmt(getSingleBetAmount(s.getBetcode(), new BigDecimal(s.getLotMulti()), 200));
		}

		return list;
	}

}
