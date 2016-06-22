package com.lottery.ticket.vender.impl.gzcp;

import com.lottery.common.contains.lottery.TerminalType;
import com.lottery.ticket.vender.AbstractVenderConfigFactory;

import org.springframework.stereotype.Service;


@Service
public class GzcpConfigFactory extends AbstractVenderConfigFactory {
	@Override
	protected	TerminalType getTerminalType() {
		return TerminalType.T_GZCP;
	}
	@Override
	protected void init(){
		configFactoryMap.put(TerminalType.T_GZCP, this);
	}
	
}
