package com.lottery.draw.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lottery.common.contains.lottery.TerminalType;
import com.lottery.core.domain.terminal.MemberAccount;
import com.lottery.core.domain.terminal.MemberAccountPK;
import com.lottery.core.domain.ticket.LotteryLogicMachine;
import com.lottery.draw.AbstractVenderBalanceWork;
import com.lottery.ticket.IVenderConfig;
import com.lottery.ticket.vender.lotterycenter.tianjin.impl.SyncAccountHandler;
@Component("tjfc_account")
public class TJFCAccountDrawWorker  extends AbstractVenderBalanceWork{
    @Autowired
	protected SyncAccountHandler  accountHander;
	@Override
	protected TerminalType getTerminalType() {
		
		return TerminalType.T_TJFC_CENTER;
	}

	public List<MemberAccount> syncBalance(){
		List<LotteryLogicMachine> list=accountHander.getAccount();
		List<MemberAccount> memberList=new ArrayList<MemberAccount>();
		if(list!=null){
			for(LotteryLogicMachine logic:list){
				MemberAccount account=accountWith(logic.getPk().getId()+"",logic.getBalance());
				memberList.add(account);
			}
		}
		return memberList;
	}
	@Override
	protected MemberAccount getAccount(IVenderConfig config) {
		
		return null;
	}
	
	protected MemberAccount accountWith(String agentCode,BigDecimal balance){
		MemberAccount memberAccount=new MemberAccount();
		MemberAccountPK memberAccountPK=new MemberAccountPK();
		memberAccountPK.setTerminalType(TerminalType.T_TJFC_CENTER.getValue());
	    memberAccountPK.setAgentCode(agentCode);
	    memberAccount.setId(memberAccountPK);
	   
	  
	    memberAccount.setBalance(balance);
	    memberAccount.setTerminalName(TerminalType.T_TJFC_CENTER.getName());
	    memberAccountService.merge(memberAccount);
	    return memberAccount;
	}

}
