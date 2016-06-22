package com.lottery.ticket.vender.impl.owncenter;

import com.lottery.common.contains.lottery.TerminalType;
import com.lottery.ticket.vender.AbstractVenderConfigFactory;
import org.springframework.stereotype.Service;


@Service
public class OwnCenterConfigFactory extends AbstractVenderConfigFactory {
	@Override
	protected	TerminalType getTerminalType() {
		return TerminalType.T_OWN_CENTER;
	}
	@Override
	protected void init(){
		configFactoryMap.put(TerminalType.T_OWN_CENTER, this);
	}
	
}
