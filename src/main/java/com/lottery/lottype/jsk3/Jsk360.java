package com.lottery.lottype.jsk3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.lottery.common.contains.ErrorCode;
import com.lottery.common.contains.lottery.LotteryDrawPrizeAwarder;
import com.lottery.common.exception.LotteryException;
import com.lottery.lottype.SplitedLot;

//和值
//100960-04,05^
public class Jsk360 extends Jsk3X{
	
	private Jsk330 jsk330 = new Jsk330();

	@Override
	public String caculatePrizeLevel(String betcode, String wincode,
			int oneAmount) {
		int[] wincodes = convertToInt(wincode);
		int num = wincodes[0]+wincodes[1]+wincodes[2];
		betcode = betcode.split("-")[1].replace("^", "");
		int[] codes = convertToInt(betcode);
		StringBuilder prize = new StringBuilder();
		for (int code : codes) {
			if (code == num) {
				if (num == 4 || num == 17) {
					prize.append(LotteryDrawPrizeAwarder.JSK3_HZ_H2.value).append(",");
				} else if (num == 5 || num == 16) {
					prize.append(LotteryDrawPrizeAwarder.JSK3_HZ_H3.value).append(",");
				} else if (num == 6 || num == 15) {
					prize.append(LotteryDrawPrizeAwarder.JSK3_HZ_H4.value).append(",");
				} else if (num == 7 || num == 14) {
					prize.append(LotteryDrawPrizeAwarder.JSK3_HZ_H5.value).append(",");
				} else if (num == 8 || num == 13) {
					prize.append(LotteryDrawPrizeAwarder.JSK3_HZ_H7.value).append(",");
				} else if (num == 9 || num == 12) {
					prize.append(LotteryDrawPrizeAwarder.JSK3_HZ_H8.value).append(",");
				} else if (num == 10 || num == 11) {
					prize.append(LotteryDrawPrizeAwarder.JSK3_HZ_H9.value).append(",");
				}
			}
		}
		check2delete(prize);
		return prize.toString();
	}

	@Override
	public long getSingleBetAmount(String betcode, BigDecimal beishu,
			int oneAmount) {
		if(!betcode.matches(k360)) {
			throw new LotteryException(ErrorCode.betamount_error, ErrorCode.betamount_error.memo);
		}
		betcode = betcode.split("-")[1].replace("^", "");
		if(isDuplicate(betcode)) {
			throw new LotteryException(ErrorCode.betamount_error, ErrorCode.betamount_error.memo);
		}
		return betcode.split(",").length*200*beishu.longValue();
	}

	@Override
	public List<SplitedLot> splitByType(String betcode, int lotmulti,
			int oneAmount) {
		List<SplitedLot> list = new ArrayList<SplitedLot>();
		List<SplitedLot> zhumaList = transform(betcode,lotmulti);
		
		for(SplitedLot splitedLot:zhumaList) {
			if(!SplitedLot.isToBeSplitFC(splitedLot.getLotMulti(),splitedLot.getAmt())) {
				list.add(splitedLot);
			}else {
				list.addAll(SplitedLot.splitToPermissionMulti(splitedLot.getBetcode(), splitedLot.getLotMulti(), 50,lotterytype));
			}
		}
		for(SplitedLot s:list) {
			if(s.getBetcode().startsWith("100960")) {
				s.setAmt(getSingleBetAmount(s.getBetcode(), new BigDecimal(s.getLotMulti()), 200));
			}else if(s.getBetcode().startsWith("100930")){
				s.setAmt(jsk330.getSingleBetAmount(s.getBetcode(), new BigDecimal(s.getLotMulti()), 200));
			}
			
		}

		return list;
	}
	
	
	private List<SplitedLot> transform(String betcode,int lotmulti) {
		List<SplitedLot> list = new ArrayList<SplitedLot>();
		betcode = betcode.replace("^", "");
		String[] betcodes = betcode.split("-")[1].split(",");
		
		for(String code:betcodes) {
			
			
			SplitedLot slot = new SplitedLot(lotterytype);
			slot.setLotMulti(lotmulti);
			
			if("3".equals(code)) {
				slot.setBetcode("100930-1,1,1^");
				slot.setAmt(200*lotmulti);
			}else if("18".equals(code)) {
				slot.setBetcode("100930-6,6,6^");
				slot.setAmt(200*lotmulti);
			} else {
				slot.setBetcode("100960-"+code+"^");
				slot.setAmt(getSingleBetAmount(slot.getBetcode(), new BigDecimal(lotmulti), 200));
			}
			
			list.add(slot);
		}
		return list;
	}

}
