package com.lottery.core;

import com.lottery.common.util.CoreDateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class IdGeneratorService {
    @Autowired
	IdGeneratorDao idDao;
    @Transactional
    public String getBatchTicketId(){
    	return idDao.getBatchTicketId();
    }
    
    
    @Transactional
    public String getMessageId(){
    	return idDao.getMessageId();
    }
    
    
    @Transactional
    public String getLotteryOrderId(){
    	return idDao.getOrderId();
    }
    
    @Transactional
    public String getLotteryOrderId(Long id){
    	return idDao.getOrderId(id);
    }
    
    @Transactional
    public Long getLotteryOrderCount(int count){
    	return idDao.getOrderId(count);
    }
    
    
    
    @Transactional
    public void mergeid(Long id, Long seqid, String sequeueName){
    	IdGenerator idg = idDao.find(id);
    	idg.setSeqid(seqid);
    	idg.setSequeueName(sequeueName);
    	idDao.merge(idg);
    }
    /**
     * 生成新期的id
     * @param count 生成个数
     * */
    @Transactional
    public Long getLotteryPhaseId(int count){
    	return idDao.getLotteryPhaseId(count);
    }
    
    @Transactional
    public String getLotteryOrderId(int count){
    	return idDao.getOrderId();
    }
    /**
     * 获取票的id
     * 
     * */
    @Transactional
    public Long gerTicketId(int count){
    	return idDao.getTikcetId(count);
    }
    /***
     * 获取出票mesageid
     * @param  seqEnum 出票标示
     * */
    @Transactional
    public String getMessageId(SeqEnum seqEnum){

        String dateStr= CoreDateUtils.formatDate(new Date(), "yyMMdd");
        return String.format("%s%08d", dateStr, idDao.get(seqEnum));
    }
    
    
    /***
     * 获取出票mesageid
     * @param  seqEnum 出票标示
     * */
    @Transactional
    public String getMessageId(SeqEnum seqEnum,String format){

        String dateStr= CoreDateUtils.formatDate(new Date(), format);
        return String.format("%s%010d", dateStr, idDao.get(seqEnum));
    }
    /**
     *
     * 获取默认id
     * */
    @Transactional
    public Long getDefaultId(){
        return idDao.getDefaultId();
    }
    
}
