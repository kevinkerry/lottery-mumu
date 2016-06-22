package com.lottery.ticket.vender.impl.shcp;

import com.lottery.common.contains.lottery.TerminalType;
import com.lottery.ticket.vender.AbstractVenderConfigFactory;

import org.springframework.stereotype.Service;


@Service
public class SHCPConfigFactory extends AbstractVenderConfigFactory {
	@Override
	protected	TerminalType getTerminalType() {
		return TerminalType.T_SHCP;
	}
	@Override
	protected void init(){
		configFactoryMap.put(TerminalType.T_SHCP, this);
	}
	
}
