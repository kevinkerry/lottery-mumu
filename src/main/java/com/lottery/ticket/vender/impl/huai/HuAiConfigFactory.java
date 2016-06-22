package com.lottery.ticket.vender.impl.huai;

import com.lottery.common.contains.lottery.TerminalType;
import com.lottery.ticket.vender.AbstractVenderConfigFactory;

import org.springframework.stereotype.Service;


@Service
public class HuAiConfigFactory extends AbstractVenderConfigFactory {
	@Override
	protected	TerminalType getTerminalType() {
		return TerminalType.T_HUAI;
	}
	@Override
	protected void init(){
		configFactoryMap.put(TerminalType.T_HUAI, this);
	}
	
}
