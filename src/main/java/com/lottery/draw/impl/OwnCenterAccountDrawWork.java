package com.lottery.draw.impl;

import com.lottery.common.contains.lottery.TerminalType;
import com.lottery.core.domain.terminal.MemberAccount;
import com.lottery.draw.AbstractVenderBalanceWork;
import com.lottery.ticket.IVenderConfig;
import org.springframework.stereotype.Service;

/**
 * Created by fengqinyun on 15/9/6.
 */

public class OwnCenterAccountDrawWork extends AbstractVenderBalanceWork {
    @Override
    protected TerminalType getTerminalType() {
        return null;
    }

    @Override
    protected MemberAccount getAccount(IVenderConfig config) {
        return null;
    }
}
