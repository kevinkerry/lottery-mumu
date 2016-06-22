package com.lottery.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lottery.common.PageBean;
import com.lottery.common.ResponseData;
import com.lottery.common.contains.ErrorCode;
import com.lottery.common.exception.LotteryException;
import com.lottery.core.domain.coupon.CouponType;
import com.lottery.core.domain.coupon.UserCoupon;
import com.lottery.core.service.coupon.CouponTypeService;
import com.lottery.core.service.coupon.UserCouponService;


@Controller
@RequestMapping("coupon")
public class CouponController {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private CouponTypeService couponTypeService;
	@Autowired
	private UserCouponService userCouponService;
	
	/**
	 * 查询优惠券
	 * @param pageIndex 从1开始
	 * @param maxResult
	 * @param status 1开启 2:关闭，0全部  默认是1
	 * @return
	 */
	@RequestMapping("couponTypes")
	public @ResponseBody ResponseData couponTypes(@RequestParam(required = false, defaultValue = "1") Integer pageIndex,
			@RequestParam(required = false, defaultValue = "30") Integer maxResult,
			@RequestParam(required = false, defaultValue = "1") Integer status){
		ResponseData rd = new ResponseData();
		rd.setErrorCode(ErrorCode.Success.getValue());
		try {
			PageBean<CouponType> page = new PageBean<CouponType>(pageIndex, maxResult);
			couponTypeService.list(status, page);
			rd.setValue(page);
		} catch (Exception e) {
			logger.error("", e);
			rd.setErrorCode(ErrorCode.Faile.getValue());
		}
		return rd;
	}
	
	/**
	 * 查询用户优惠券
	 * @param pageIndex 从1开始
	 * @param maxResult
	 * @param userno 用户编号
	 * @param lotteryType 彩种 为空就查全部
	 * @param status 1:未使用 2：已使用 3：已过期
	 * @return
	 */
	@RequestMapping("userCoupons")
	public @ResponseBody ResponseData userCoupons(@RequestParam(required = false, defaultValue = "1") Integer pageIndex,
			@RequestParam(required = false, defaultValue = "30") Integer maxResult, 
			@RequestParam String userno,  Integer status, @RequestParam(required = false, defaultValue = "") String lotteryType){
		ResponseData rd = new ResponseData();
		rd.setErrorCode(ErrorCode.Success.getValue());
		try {
			PageBean<UserCoupon> page = new PageBean<UserCoupon>(pageIndex, maxResult);
			userCouponService.list(userno, status, lotteryType, page);
			rd.setValue(page);
		} catch (Exception e) {
			logger.error("", e);
			rd.setErrorCode(ErrorCode.Faile.getValue());
		}
		return rd;
	}
	
	
	
	
	@RequestMapping("countEnable")
	public @ResponseBody ResponseData userCoupons(
			@RequestParam String userno,  Integer status){
		ResponseData rd = new ResponseData();
		rd.setErrorCode(ErrorCode.Success.getValue());
		try {
			rd.setValue(userCouponService.countEnable(userno, status));
		} catch (Exception e) {
			logger.error("", e);
			rd.setErrorCode(ErrorCode.Faile.getValue());
		}
		return rd;
	}
	
	
	
	/**
	 * 查询用户优惠券
	 * @param userno 用户编号
	 * @return lotteryType 查彩种
	 */
	@RequestMapping("userEnableCoupons")
	public @ResponseBody ResponseData userEnableCoupons(@RequestParam String userno, String[] lotteryType){
		ResponseData rd = new ResponseData();
		rd.setErrorCode(ErrorCode.Success.getValue());
		try {
			List<UserCoupon> list = userCouponService.findAllEnableCoupon(userno, lotteryType);
			if(list==null||list.size()<=0) {
				rd.setErrorCode(ErrorCode.no_exits.value);
			}
			rd.setValue(list);
		} catch (Exception e) {
			logger.error("", e);
			rd.setErrorCode(ErrorCode.Faile.getValue());
		}
		return rd;
	}
	
	/**
	 * 发优惠券
	 * @param userno 用户编号
	 * @param couponTypeId 优惠券编号
	 * @return
	 */
	@RequestMapping("addUsercoupon")
	public @ResponseBody ResponseData addUsercoupon(@RequestParam String userno, @RequestParam Long couponTypeId){
		ResponseData rd = new ResponseData();
		try {
			rd.setErrorCode(ErrorCode.Success.getValue());
			userCouponService.add(couponTypeId, new String[]{userno});
		} catch (LotteryException e) {
			rd.setErrorCode(e.getErrorCode().getValue());
			rd.setValue(e.getErrorCode().getMemo());
		} catch (Exception e) {
			logger.error("", e);
			rd.setErrorCode(ErrorCode.Faile.getValue());
		}
		return rd;
	}
}
