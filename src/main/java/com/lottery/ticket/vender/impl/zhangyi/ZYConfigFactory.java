package com.lottery.ticket.vender.impl.zhangyi;

import org.springframework.stereotype.Service;

import com.lottery.common.contains.lottery.TerminalType;
import com.lottery.ticket.vender.AbstractVenderConfigFactory;
@Service
public class ZYConfigFactory extends AbstractVenderConfigFactory {

	@Override
	protected	TerminalType getTerminalType() {
		return TerminalType.T_ZY;
	}
	@Override
	protected void init(){
		configFactoryMap.put(TerminalType.T_ZY, this);
	}

}
