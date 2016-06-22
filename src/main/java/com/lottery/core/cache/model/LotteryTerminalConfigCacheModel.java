package com.lottery.core.cache.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lottery.common.cache.AbstractKeyValueCacheWithTimeoutModel;
import com.lottery.common.contains.lottery.LotteryType;
import com.lottery.common.exception.CacheNotFoundException;
import com.lottery.common.exception.CacheUpdateException;
import com.lottery.core.domain.terminal.LotteryTerminalConfig;
import com.lottery.core.service.LotteryTerminalConfigService;

@Service
public class LotteryTerminalConfigCacheModel extends AbstractKeyValueCacheWithTimeoutModel<LotteryType, LotteryTerminalConfig> {
    @Autowired
    private LotteryTerminalConfigService lotteryTerminalConfigService;

    protected Map<LotteryType, LotteryTerminalConfig> cachedLotteryTerminalConfigMap = new ConcurrentHashMap<LotteryType, LotteryTerminalConfig>();

    @Override
    protected LotteryTerminalConfig getFromCacheWithoutTimeout(LotteryType key) throws CacheNotFoundException {
        if (!this.cachedLotteryTerminalConfigMap.containsKey(key)) {
            throw new CacheNotFoundException("缓存中未找到对应的终端, key=" + key);
        }
        return this.cachedLotteryTerminalConfigMap.get(key);
    }

    @Override
    protected void setCacheWithoutTimeout(LotteryType key, LotteryTerminalConfig value) throws CacheUpdateException {
        if (value == null) {
            // ConcurrentHashMap不允许保存null值
            throw new CacheUpdateException("终端信息为空，不允许保存，key=" + key);
        }
        this.cachedLotteryTerminalConfigMap.put(key, value);
    }

    @Override
    protected Map<LotteryType, LotteryTerminalConfig> mgetFromSource(List<LotteryType> keyList) throws Exception {
        return this.getFromList(keyList);
    }

    @Override
    protected LotteryTerminalConfig getFromSource(LotteryType key) throws Exception {
    	LotteryType lotteryType=LotteryType.getPhaseType(key);
    	LotteryTerminalConfig source=getFromMC(lotteryType);
    	if(source==null){
    		source=this.lotteryTerminalConfigService.get(lotteryType.value);
    		if(source!=null){
    			setMC(lotteryType,source);
    		}
    	}
        return  source;
    }

   protected Map<LotteryType, LotteryTerminalConfig> getFromList(List<LotteryType> keyList)throws Exception{
	   Map<LotteryType, LotteryTerminalConfig> lotteryTerminalConfigMap = new HashMap<LotteryType, LotteryTerminalConfig>();
	   for(LotteryType lotteryType:keyList){
		   LotteryTerminalConfig config=this.getFromSource(lotteryType);
		   if(config!=null){
			   lotteryTerminalConfigMap.put(lotteryType, config);
		   }
	   }
	   return lotteryTerminalConfigMap;
   }



}
