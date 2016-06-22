package com.lottery.core.service.give;

import com.lottery.common.contains.YesNoStatus;
import com.lottery.common.util.StringUtil;
import com.lottery.core.IdGeneratorDao;
import com.lottery.core.dao.give.UserRechargeGiveDAO;
import com.lottery.core.domain.give.UserRechargeGive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Service
public class UserRechargeGiveSerivce {
	@Autowired
	protected UserRechargeGiveDAO userRechargeGiveDAO;
	@Autowired
	protected IdGeneratorDao idDao;

	@Transactional
	public void save(UserRechargeGive entity) {
		String id = idDao.getUserRechargeGiveId();
		entity.setId(id);
		userRechargeGiveDAO.insert(entity);
	}
	/**
	 * 根据充值金额，类型查询赠送
	 * @param amount 充值金额
	 * @param rechargeGiveType 充值赠送类型
	 * @return 
	 * */
	@Transactional
	 public UserRechargeGive  getByAmountAndType(BigDecimal amount,int rechargeGiveType){
		 List<UserRechargeGive> giveList=userRechargeGiveDAO.getByAmountAndType(amount, rechargeGiveType);
		giveList=getEableUserRechargeGiveList(giveList,rechargeGiveType);
		if(giveList.size()>0){
			return giveList.get(0);
		}
		return null;
	 }
	/**
	 * 充值条件过滤
	 * */
	protected List<UserRechargeGive> getEableUserRechargeGiveList(List<UserRechargeGive> giveList,int rechargeGiveType){
		List<UserRechargeGive> enableList=new ArrayList<UserRechargeGive>();
		if(giveList==null||giveList.size()==0){
			giveList=userRechargeGiveDAO.getType(rechargeGiveType, 1);
		}
		for(UserRechargeGive userRechargeGive:giveList){
			if(userRechargeGive.getStatus()==YesNoStatus.no.value||userRechargeGive.getFinishTime().before(new Date())||userRechargeGive.getStartTime().after(new Date())){
				continue;
			}
			enableList.add(userRechargeGive);
		}
		if(enableList.size()>0){
			Collections.sort(enableList, new Comparator<UserRechargeGive>(){
				@Override
				public int compare(UserRechargeGive o1, UserRechargeGive o2) {
					 // 按金额升序排列
	                if (o1.getRechargeAmount() != null && o2.getRechargeAmount() != null
	                        && o1.getRechargeAmount().intValue() != o2.getRechargeAmount().intValue()) {
	                    return o1.getRechargeAmount().compareTo( o2.getRechargeAmount());
	                }
	                // 否则按照ID升序排列
	                return o1.getId().compareTo(o2.getId());
				}
			});
		}
		return  enableList;
	}
	
	@Transactional
	public void saveOrUpdate(UserRechargeGive give) {
		if(StringUtil.isEmpty(give.getId())){
            String id = idDao.getUserRechargeGiveId();
            give.setId(id);
            userRechargeGiveDAO.insert(give);
		}else{
			UserRechargeGive rgive = userRechargeGiveDAO.find(give.getId());
			rgive.setFinishTime(give.getFinishTime());
			rgive.setGiveAmount(give.getGiveAmount());
			rgive.setRechargeAmount(give.getRechargeAmount());
			rgive.setRechargeGiveType(give.getRechargeGiveType());
			rgive.setStartTime(give.getStartTime());
			rgive.setStatus(give.getStatus());
			rgive.setForLimit(give.getForLimit());
			rgive.setForScope(give.getForScope());
			rgive.setNotDrawPerset(give.getNotDrawPerset());
			rgive.setAgencyno(give.getAgencyno());
			userRechargeGiveDAO.update(rgive);
		}
	}
	
	@Transactional
	public void delete(String strChecked) {
		String[] ids = strChecked.split(",");
		for (String id : ids) {
			UserRechargeGive userRechargeGive  = userRechargeGiveDAO.find(id);
			userRechargeGiveDAO.remove(userRechargeGive);
		}
	}
	/**
	 * 根据充值类型，渠道查询
	 * @param rechargeGiveType 充值赠送类型
	 * @param agencyno 渠道号
	 * @param max 条数
	 * 
	 * */
	@Transactional
	public List<UserRechargeGive> getByGiveTypeAndAgencyno(int rechargeGiveType,String agencyno,int max){
		return userRechargeGiveDAO.getByGiveTypeAndAgencyno(rechargeGiveType, agencyno, max);
	}
}
