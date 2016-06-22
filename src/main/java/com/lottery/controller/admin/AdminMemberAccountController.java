package com.lottery.controller.admin;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lottery.common.ResponseData;
import com.lottery.common.contains.ErrorCode;
import com.lottery.core.dao.MemberAccountDAO;
import com.lottery.core.domain.terminal.MemberAccount;
import com.lottery.core.service.MemberAccountService;
import com.lottery.draw.IVenderBalanceWork;

@Controller
@RequestMapping("aMemberAccount")
public class AdminMemberAccountController {
	private final Logger logger=LoggerFactory.getLogger(getClass());
    @Autowired
	private Map<String, IVenderBalanceWork> balanceWorkMap;
    @Autowired
	private MemberAccountService accountService;
    @Autowired
	private MemberAccountDAO memberAccountDAO;
    
    @RequestMapping("syncBalance")
	public @ResponseBody ResponseData syncBalance(){
		ResponseData rd = new ResponseData();
		try {
			for (final IVenderBalanceWork  iVenderBalanceWork : balanceWorkMap.values()) {
				Thread thread=new Thread(new Runnable() {
					@Override
					public void run() {
						iVenderBalanceWork.syncBalance();
					}
				});
				thread.setName("同步出票商余额");
				thread.start();
			}

			rd.setErrorCode(ErrorCode.Success.value);
			rd.setValue("success");
		} catch (Exception e) {
			logger.error("同步余额出错", e);
			rd.setErrorCode(ErrorCode.Faile.value);
			rd.setValue(e.getMessage());
		}
		return rd;
	}
	
    @RequestMapping("syncBalanceWithResult")
	public @ResponseBody ResponseData syncBalanceWithResult(){
		ResponseData rd = new ResponseData();
		try {
			List<FutureTask<List<MemberAccount>>> worklist = new ArrayList<FutureTask<List<MemberAccount>>>();
			
			for (final Entry<String, IVenderBalanceWork>  entry : balanceWorkMap.entrySet()) {
				FutureTask<List<MemberAccount>> futureTask = new FutureTask<List<MemberAccount>>(new Callable<List<MemberAccount>>() {
					@Override
					public List<MemberAccount> call() {
						return entry.getValue().syncBalance();
					}
				});
				worklist.add(futureTask);
			    Thread thread = new Thread(futureTask);
			    thread.setName("同步"+entry.getKey()+"出票商余额");
		        thread.start();
			}

			
			List<MemberAccount> ret = new ArrayList<MemberAccount>();
			for (FutureTask<List<MemberAccount>> futureTask : worklist) {
				ret.addAll(futureTask.get());
			}

			rd.setErrorCode(ErrorCode.Success.value);
			rd.setValue(ret);
		} catch (Exception e) {
			logger.error("同步余额出错", e);
			rd.setErrorCode(ErrorCode.Faile.value);
			rd.setValue(e.getMessage());
		}
		return rd;
	}
    
	@RequestMapping("updateWarmAmount")
	public @ResponseBody ResponseData updateWarmAmount(@RequestParam(value = "terminalType", required = false) Integer terminalType,
			@RequestParam(value = "agentCode", required = false) String agentCode,
			@RequestParam(value = "warnAmount", required = false) BigDecimal warnAmount,
			@RequestParam(value = "smsFlag", required = false) Integer smsFlag,
			@RequestParam(value = "isSync", required = false) Integer isSync){
		ResponseData rd = new ResponseData();
		rd.setErrorCode(ErrorCode.Success.value);
		try {
			accountService.updateMemberAccount(terminalType, agentCode, warnAmount,smsFlag,isSync);
		} catch (Exception e) {
			logger.error("memberAccount/updateWarmAmount error", e);
			rd.setErrorCode(ErrorCode.Faile.value);
			rd.setValue(e);
		}
		return rd;
	}
	
	@RequestMapping("list")
	public @ResponseBody List<MemberAccount> findAll(){
		try {
			return memberAccountDAO.findAll();
		} catch (Exception e) {
			logger.error("aMemberAcount/list", e);
		}
		return new ArrayList<MemberAccount>();
	} 
}
