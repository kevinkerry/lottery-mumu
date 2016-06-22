package com.lottery.ticket.vender.impl;

import com.lottery.common.contains.lottery.TerminalType;
import com.lottery.core.handler.VenderConfigHandler;
import com.lottery.ticket.IVenderConfig;
import com.lottery.ticket.vender.IVenderInternalDcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractInternalDcService implements
		IVenderInternalDcService {

	    @Autowired
		protected VenderConfigHandler venderConfigService;
		protected abstract TerminalType getTerminalType();
		
		protected List<IVenderConfig> getVenderCofig(){
			
			IVenderConfig config = new IVenderConfig() {
				@Override
				public Integer getTimeOutSecondForSend() {
					return 0;
				}
				@Override
				public Integer getTimeOutSecondForCheck() {
					return 0;
				}
				@Override
				public Integer getSendCount() {
					return 0;
				}
				@Override
				public String getRequestUrl() {
					return "http://interlib.198tc.com/b2blib/lotteryxml.php";
				}
				@Override
				public String getPublicKey() {
					return null;
				}
				@Override
				public String getPrivateKey() {
					return null;
				}
				@Override
				public Integer getPort() {
					return 0;
				}
				@Override
				public String getPasswd() {
					return null;
				}
				@Override
				public String getKey() {
					return "859fe415351be71a0d9d2ab8f0de2996";
				}
				@Override
				public String getCheckUrl() {
					return null;
				}
				@Override
				public Integer getCheckCount() {
					return 0;
				}
				@Override
				public String getAgentCode() {
					return "10000542";
				}
				@Override
				public Long getTerminalId() {
					return null;
				}
			};
			List<IVenderConfig> list = new ArrayList<IVenderConfig>();
//			list.add(config);
//			return list; 
			return venderConfigService.getAllByTerminalType(getTerminalType());
		}

}
