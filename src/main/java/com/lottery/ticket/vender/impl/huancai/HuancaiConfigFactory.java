package com.lottery.ticket.vender.impl.huancai;

import com.lottery.common.contains.lottery.TerminalType;
import com.lottery.ticket.vender.AbstractVenderConfigFactory;

import org.springframework.stereotype.Service;


@Service
public class HuancaiConfigFactory extends AbstractVenderConfigFactory {
	@Override
	protected	TerminalType getTerminalType() {
		return TerminalType.T_HUANCAI;
	}
	@Override
	protected void init(){
		configFactoryMap.put(TerminalType.T_HUANCAI, this);
	}
	
}
