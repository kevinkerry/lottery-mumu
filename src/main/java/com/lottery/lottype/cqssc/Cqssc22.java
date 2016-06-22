package com.lottery.lottype.cqssc;

import java.math.BigDecimal;
import java.util.List;

import com.lottery.lottype.SplitedLot;

//三星和值暂时不做
public class Cqssc22 extends CqsscX{

	@Override
	public String caculatePrizeLevel(String betcode, String wincode,
			int oneAmount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getSingleBetAmount(String betcode, BigDecimal beishu,
			int oneAmount) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<SplitedLot> splitByType(String betcode, int lotmulti,
			int oneAmount) {
		// TODO Auto-generated method stub
		return null;
	}

}
