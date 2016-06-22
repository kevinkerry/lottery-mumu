package com.lottery.ticket.vender.impl.gz2cp;

import com.lottery.common.contains.lottery.TerminalType;
import com.lottery.ticket.vender.AbstractVenderConfigFactory;

import org.springframework.stereotype.Service;


@Service
public class Gz2cpConfigFactory extends AbstractVenderConfigFactory {
	@Override
	protected	TerminalType getTerminalType() {
		return TerminalType.T_GZT;
	}
	@Override
	protected void init(){
		configFactoryMap.put(TerminalType.T_GZT, this);
	}
	
}
