package com.lottery.scheduler.fetcher.jcResult.zch;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.lottery.common.contains.lottery.TerminalType;
import com.lottery.scheduler.fetcher.IGetDataFrom;
import com.lottery.scheduler.fetcher.jcResult.AbstractGetJczqResultFrom;
import com.lottery.ticket.IVenderConfig;
import com.lottery.core.handler.VenderConfigHandler;

@Component(value="jczqResultFromZch")
public class GetJczqResultFromZch extends AbstractGetJczqResultFrom{
	protected final Logger logger=LoggerFactory.getLogger(getClass());
	@Autowired
	private VenderConfigHandler venderConfigService;
	@Autowired
	@Qualifier("jczqResult")
	private IGetDataFrom iGetDataFrom;
	@Autowired
	@Qualifier("zch")  
	private TerminalType terminalType;
	
	@Override
	public JSONArray getArray(String phaseNo, String sn){
			try {
				List<IVenderConfig> list=venderConfigService.getAllByTerminalType(terminalType);
				IVenderConfig config = null;
				if(list!=null&&list.size()>0){
					config = list.get(0);
				}
				Map<String, String> reqMap = new HashMap<String, String>();
				reqMap.put("phaseNo", phaseNo);
				return iGetDataFrom.perfrom(reqMap, config);
			} catch (Exception e) {
				logger.error("获取jczqresult出错", e);
			} 
			return null;
	 }

}
