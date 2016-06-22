package com.lottery.core.handler;

import com.lottery.common.ListSerializable;
import com.lottery.common.cache.CacheService;
import com.lottery.common.contains.EnabledStatus;
import com.lottery.common.contains.YesNoStatus;
import com.lottery.common.contains.lottery.LotteryType;
import com.lottery.common.contains.lottery.LottypeConfigStatus;
import com.lottery.common.exception.CacheNotFoundException;
import com.lottery.common.exception.CacheUpdateException;
import com.lottery.core.cache.model.LotteryCurrentPhaseCacheModel;
import com.lottery.core.cache.model.LotteryTicketConfigCacheModel;
import com.lottery.core.cache.model.LottypeConfigCacheModel;
import com.lottery.core.domain.LotteryPhase;
import com.lottery.core.domain.LottypeConfig;
import com.lottery.core.domain.ticket.LotteryTicketConfig;
import com.lottery.core.service.LottypeConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class LotteryPhaseHandler {
	private final Logger logger = LoggerFactory.getLogger(getClass().getName());
	@Autowired
	protected LotteryTicketConfigCacheModel lotteryTicketConfigCacheModel;
	@Autowired
	protected LotteryCurrentPhaseCacheModel currentPhaseCacheModel;
	@Autowired
    protected LottypeConfigCacheModel lottypeConfigCacheModel;
	@Autowired
	protected LottypeConfigService lottypeConfigService;
	@Resource(name = "xmemcachedService")
	private CacheService cacheService;
	/**
	 * 计算时间偏移量
	 * */
	public  Date getEndSaleForwardDate(Date date, Integer lotteryType) {
		Date returnDate = date;
		if (date == null) {
			logger.error("请求参数不能为空:时间{},彩种{}", new Object[] { date, lotteryType });
		}
		if(lotteryType==null){
			lotteryType=LotteryType.ALL.value;
		}
		if (lotteryTicketConfigCacheModel != null && date != null&&lotteryType!=null) {
			try {
				LotteryTicketConfig lotteryTicketConfig = lotteryTicketConfigCacheModel.get(LotteryType.getPhaseType(lotteryType));
				if (lotteryTicketConfig != null) {
					if (lotteryTicketConfig.getEndSaleForward() != null) {
						long time = date.getTime() - lotteryTicketConfig.getEndSaleForward().longValue();
						returnDate = new Date(time);
					}
				}
			} catch (CacheNotFoundException e) {

            } catch (CacheUpdateException e) {

            }catch (Exception e) {
				logger.error("彩种:{},获取偏移量出错",lotteryType,e);
			}
		}
		return returnDate;
	}
	
	public Long getEndTicketTimeout(Integer lotteryType){
		Long returnDate=0l;
		if(lotteryType==null){
			lotteryType=LotteryType.ALL.value;
		}
		if (lotteryTicketConfigCacheModel != null&&lotteryType!=null) {
			try {
				LotteryTicketConfig lotteryTicketConfig = lotteryTicketConfigCacheModel.get(LotteryType.getPhaseType(lotteryType));
				if (lotteryTicketConfig != null) {
					if (lotteryTicketConfig.getEndTicketTimeout() != null) {
						returnDate = lotteryTicketConfig.getEndTicketTimeout();
					}
				}
			}catch (CacheNotFoundException e) {

            } catch (CacheUpdateException e) {

            } catch (Exception e) {
				logger.error("彩种:{},获取出票超时截止出错",lotteryType,e);
			}
		}
		return returnDate;
	}
	
	public  LotteryPhase getCurrent(Integer lotteryType){
        try {
			LotteryPhase lotteryPhase= currentPhaseCacheModel.get(LotteryType.getPhaseTypeValue(lotteryType));
			if (lotteryPhase!=null){
				lotteryPhase.setEnableSaleTime(this.getEndSaleForwardDate(lotteryPhase.getEndTicketTime(), lotteryType));
			}
			return lotteryPhase;
        } catch (CacheNotFoundException e) {

        } catch (CacheUpdateException e) {

        }catch(Exception e){
            logger.error("彩种:{},获取当前期出错",lotteryType,e);
        }
        return null;
	}

	public List<LotteryPhase> getCurrentList(){
		List<LotteryPhase> phaselist=new ArrayList<LotteryPhase>();
		for(Integer lotteryType:LotteryType.getAllLotteryType()){
			LotteryPhase phase=this.getCurrent(lotteryType);
			if (phase!=null){
				phaselist.add(phase);
			}
		}
		return phaselist;
	}

/**
 *获取lottypeconfig 配置
 * @param  lotteryType 彩种
 * */
    public LottypeConfig getValidateConfig(int lotteryType){
        LottypeConfig config=this.filterConfig(lotteryType);
        LotteryPhase phase=this.getCurrent(lotteryType);
        if(phase!=null&&config!=null){
            config.setLotteryType(lotteryType);
            config.setPhase(phase.getPhase());
            config.setEndSaleTime(phase.getEnableSaleTime());
        }
        return  config;
    }


   /**
    * 一次性获取lottypeconfig 所有配置
    * */
    public List<LottypeConfig> getValidateConfigList(){
		String key="lottype_config_list";
		ListSerializable<LottypeConfig> listSerializable=cacheService.get(key,3000);
		if (listSerializable==null){
			Set<LottypeConfig> list=new HashSet<LottypeConfig>();
			for (int lotteryType: LotteryType.getValues()){
				LottypeConfig lottypeConfig=getValidateConfig(lotteryType);
				list.add(lottypeConfig);
			}
			if (list.size()>0){
				List<LottypeConfig> lottypeConfigList=new ArrayList<LottypeConfig>();
				lottypeConfigList.addAll(list);
				listSerializable=new ListSerializable<LottypeConfig>();
				listSerializable.setList(lottypeConfigList);
				cacheService.set(key,10,listSerializable);//10秒钟一次
				return lottypeConfigList;
			}
		}else {
			return listSerializable.getList();
		}

        return  null;
    }

	public  LottypeConfig filterConfig(int lotteryType){
		LottypeConfig allConfig=null;
		try {
			allConfig = lottypeConfigCacheModel.get(LotteryType.ALL.value);
		} catch (CacheNotFoundException e) {
			
		} catch (CacheUpdateException e) {
			
		}
		LottypeConfig lottypeConfig=null;
		try {
			lottypeConfig = lottypeConfigCacheModel.get(LotteryType.getSingleValue(lotteryType));
		} catch (CacheNotFoundException e) {
		
		} catch (CacheUpdateException e) {
			
		}
		if(lottypeConfig==null||lottypeConfig.getState()==null){
			return allConfig;
		}
		
		
		if(allConfig==null||allConfig.getState()==null){
			return lottypeConfig;
		}
		

		
		if(lottypeConfig.getHeimaiForward()==null){
			lottypeConfig.setHeimaiForward(allConfig.getHeimaiForward());
		}
		
		if(lottypeConfig.getUploadForward()==null){
			lottypeConfig.setUploadForward(allConfig.getUploadForward());
		}

        if(lottypeConfig.getSingleHemaiForward()==null){
            lottypeConfig.setSingleHemaiForward(allConfig.getSingleHemaiForward());
        }

		if (lottypeConfig.getB2bForward()==null){
			lottypeConfig.setB2bForward(allConfig.getB2bForward());
		}

		
		
		
		
		
		if(allConfig.getState()==LottypeConfigStatus.paused.value||allConfig.getState()==LottypeConfigStatus.no_used.value){
			lottypeConfig.setState(LottypeConfigStatus.paused.value);
		}

		if (allConfig.getSaleEnabled()!=null&&allConfig.getSaleEnabled()== EnabledStatus.disabled.value){
			lottypeConfig.setSaleEnabled(EnabledStatus.disabled.value);
		}

		if(allConfig.getIosEndSale()!=null&&allConfig.getIosEndSale()==YesNoStatus.yes.value){
			lottypeConfig.setIosEndSale(YesNoStatus.yes.value);
		}
		
		if(allConfig.getWebEndSale()!=null&&allConfig.getWebEndSale()==YesNoStatus.yes.value){
			lottypeConfig.setWebEndSale(YesNoStatus.yes.value);
		}
		
		if(allConfig.getAndroidEendSale()!=null&&allConfig.getAndroidEendSale()==YesNoStatus.yes.value){
			lottypeConfig.setAndroidEendSale(YesNoStatus.yes.value);
		}
		if (allConfig.getB2bEndSale()!=null&&allConfig.getB2bEndSale()==YesNoStatus.yes.value){
			lottypeConfig.setB2bEndSale(YesNoStatus.yes.value);
		}
		
		if(allConfig.getHemaiEndSale()!=null&&allConfig.getHemaiEndSale()==YesNoStatus.yes.value){
			lottypeConfig.setHemaiEndSale(YesNoStatus.yes.value);
		}
		
		if(allConfig.getChaseEndSale()!=null&&allConfig.getChaseEndSale()==YesNoStatus.yes.value){
			lottypeConfig.setChaseEndSale(YesNoStatus.yes.value);
		}
		
		return lottypeConfig;
		
	}


	public LotteryTicketConfig getLotteryTicketConfig(int lotteryType){

		LotteryTicketConfig lotteryTicketConfig=null;
		try {
			lotteryTicketConfig=lotteryTicketConfigCacheModel.get(LotteryType.get(lotteryType));
		}catch (Exception e) {

		}

		if(lotteryTicketConfig==null){
			try {
				lotteryTicketConfig=lotteryTicketConfigCacheModel.get(LotteryType.getPhaseType(lotteryType));
			}catch (Exception e) {

			}

		}

		return lotteryTicketConfig;
	}
}
