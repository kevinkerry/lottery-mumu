package com.lottery.ticket.vender.impl.jydp;

import com.lottery.common.contains.lottery.TerminalType;
import com.lottery.ticket.vender.AbstractVenderConfigFactory;
import org.springframework.stereotype.Service;

/**
 * 
 * @author Liuxy
 *
 */
@Service
public class JydpConfigFactory extends AbstractVenderConfigFactory {
    @Override
    protected TerminalType getTerminalType() {
        return TerminalType.T_JYDP;
    }

    @Override
    protected void init() {
        configFactoryMap.put(TerminalType.T_JYDP, this);
    }
}
