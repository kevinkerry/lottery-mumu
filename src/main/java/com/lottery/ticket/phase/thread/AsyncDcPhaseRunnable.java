package com.lottery.ticket.phase.thread;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.lottery.common.contains.YesNoStatus;
import com.lottery.common.contains.lottery.DcType;
import com.lottery.common.contains.lottery.LotteryType;
import com.lottery.common.contains.lottery.PhaseEncaseStatus;
import com.lottery.common.contains.lottery.PhaseStatus;
import com.lottery.common.contains.lottery.PhaseTimeStatus;
import com.lottery.common.contains.lottery.PrizeStatus;
import com.lottery.common.contains.lottery.RaceStatus;
import com.lottery.common.contains.lottery.TerminalStatus;
import com.lottery.common.util.CoreDateUtils;
import com.lottery.common.util.StringUtil;
import com.lottery.core.domain.DcRace;
import com.lottery.core.domain.LotteryPhase;
import com.lottery.core.handler.PrizeErrorHandler;
import com.lottery.core.service.DcRaceService;
import com.lottery.ticket.vender.IVenderInternalDcService;

/**
 * 单场新期，赛程处理
 */
public class AsyncDcPhaseRunnable extends AbstractTicketPhaseRunnable {
    protected IVenderInternalDcService internalDcService;
    @Autowired
    protected DcRaceService dcRaceService;
    @Autowired
    private PrizeErrorHandler prizeErrorHandler;

    protected JSONArray getPhaseArray() {
        return internalDcService.getBjdcPhase(null);
    }

    public long getInvitl() {
        return 180000;
    }

    @Override
    protected void execute() {
        while (running) {
            try {
                JSONArray phaseArray = getPhaseArray();
                if (phaseArray != null) {
                    for (int i = 0; i < phaseArray.size(); i++) {
                        JSONObject object = phaseArray.getJSONObject(i);
                        phaseDowith(object);
                    }
                }
                recyclePhase();
            } catch (Exception e) {
                logger.error("执行单场新期出错", e);
            }


            try {
                synchronized (this) {
                    wait(getInvitl());
                }
            } catch (Exception e) {

            }
        }


    }

    /**
     * 处理获取的新期
     **/
    protected LotteryPhase phaseDowith(JSONObject object) {
        LotteryPhase lotteryPhase = null;
        try {
            LotteryType lotteryType = LotteryType.getLotteryType(object.getInt("lotteryType"));
            String phaseNo = object.getString("phaseNo");
            Date startTime = CoreDateUtils.parseDate(object.getString("startTime"), CoreDateUtils.DATETIME);
            Date endTime = CoreDateUtils.parseDate(object.getString("endTime"), CoreDateUtils.DATETIME);
            Date drawTime = CoreDateUtils.parseDate(object.getString("drawTime"), CoreDateUtils.DATETIME);
            int phaseStatus = object.getInt("phaseStatus");
            lotteryPhase = phaseService.getCurrent(lotteryType.getValue());
            if (lotteryPhase == null) {
                lotteryPhase = phaseService.getByTypeAndPhase(lotteryType.getValue(), phaseNo);
                boolean flag = false;
                if (lotteryPhase != null) {
                    flag = phaseStatus(lotteryPhase, startTime, endTime, drawTime, phaseStatus);
                    if (flag)
                        phaseService.update(lotteryPhase);
                } else {
                    lotteryPhase = new LotteryPhase();
                    lotteryPhase.setCreateTime(new Date());
                    lotteryPhase.setLotteryType(lotteryType.getValue());
                    lotteryPhase.setPhase(phaseNo);
                    phaseStatus(lotteryPhase, startTime, endTime, drawTime, phaseStatus);
                    phaseService.save(lotteryPhase);
                }
            } else {
                if (!lotteryPhase.getPhase().equals(phaseNo)) {
                    phaseService.updateStatus(lotteryPhase.getLotteryType(), lotteryPhase.getPhase(), PhaseStatus.close.getValue(), TerminalStatus.disenable.value, YesNoStatus.no.value, YesNoStatus.no.value);
                    LotteryPhase newPhase = new LotteryPhase();
                    newPhase.setLotteryType(lotteryType.getValue());
                    newPhase.setPhase(phaseNo);
                    newPhase.setStartSaleTime(startTime);
                    newPhase.setEndSaleTime(endTime);
                    newPhase.setEndTicketTime(endTime);
                    newPhase.setHemaiEndTime(endTime);
                    newPhase.setPhaseStatus(PhaseStatus.open.value);
                    newPhase.setForSale(YesNoStatus.yes.value);
                    newPhase.setPhaseTimeStatus(PhaseTimeStatus.NO_CORRECTION.value);
                    newPhase.setTerminalStatus(TerminalStatus.enable.value);
                    newPhase.setDrawTime(drawTime);
                    newPhase.setSwitchTime(new Date());
                    newPhase.setCreateTime(new Date());
                    newPhase.setPhaseEncaseStatus(PhaseEncaseStatus.draw_not.getValue());
                    newPhase.setForCurrent(YesNoStatus.yes.getValue());
                    phaseService.save(newPhase);
                } else {
                    if ((lotteryPhase.getEndTicketTime().getTime() - endTime.getTime()) != 0) {
                        lotteryPhase.setEndSaleTime(endTime);
                        lotteryPhase.setEndTicketTime(endTime);
                        lotteryPhase.setHemaiEndTime(endTime);
                        phaseService.update(lotteryPhase);
                    }
                }

            }
            match(lotteryType, phaseNo);
        } catch (Exception e) {
            logger.error("操作单场新期出错", e);
        }
        return lotteryPhase;
    }

    /**
     * 期状态修改
     */
    protected boolean phaseStatus(LotteryPhase lotteryPhase, Date startTime, Date endTime, Date drawTime, int phaseStatus) {
        boolean flag = false;
        if (phaseStatus == PhaseStatus.open.getValue()) {
            lotteryPhase.setPhaseStatus(PhaseStatus.open.value);
            lotteryPhase.setForSale(YesNoStatus.yes.value);
            lotteryPhase.setPhaseTimeStatus(PhaseTimeStatus.NO_CORRECTION.value);
            lotteryPhase.setTerminalStatus(TerminalStatus.enable.value);
            lotteryPhase.setSwitchTime(new Date());
            lotteryPhase.setPhaseEncaseStatus(PhaseEncaseStatus.draw_not.getValue());
            lotteryPhase.setForCurrent(YesNoStatus.yes.getValue());
            lotteryPhase.setStartSaleTime(startTime);
            lotteryPhase.setEndSaleTime(endTime);
            lotteryPhase.setEndTicketTime(endTime);
            lotteryPhase.setHemaiEndTime(endTime);
            lotteryPhase.setDrawTime(drawTime);
            flag = true;
        } else {
            lotteryPhase.setPhaseStatus(PhaseStatus.unopen.value);
            lotteryPhase.setForSale(YesNoStatus.no.value);
            lotteryPhase.setPhaseTimeStatus(PhaseTimeStatus.NO_CORRECTION.value);
            lotteryPhase.setTerminalStatus(TerminalStatus.disenable.value);
            lotteryPhase.setSwitchTime(new Date());
            lotteryPhase.setPhaseEncaseStatus(PhaseEncaseStatus.draw_not.getValue());
            lotteryPhase.setForCurrent(YesNoStatus.no.getValue());
            lotteryPhase.setStartSaleTime(startTime);
            lotteryPhase.setDrawTime(drawTime);
            lotteryPhase.setEndSaleTime(endTime);
            lotteryPhase.setEndTicketTime(endTime);
            lotteryPhase.setHemaiEndTime(endTime);
            lotteryPhase.setDrawTime(drawTime);
            flag = true;
        }

        if (lotteryPhase.getEndSaleTime() != null) {
            if ((endTime.getTime() - lotteryPhase.getEndSaleTime().getTime()) != 0) {
                flag = true;
                lotteryPhase.setEndSaleTime(endTime);
                lotteryPhase.setEndTicketTime(endTime);
                lotteryPhase.setHemaiEndTime(endTime);
            }
        } else {
            lotteryPhase.setEndSaleTime(endTime);
            lotteryPhase.setEndTicketTime(endTime);
            lotteryPhase.setHemaiEndTime(endTime);
            flag = true;
        }

        return flag;
    }


    public void match(LotteryType lotteryType, String phase) {
        Map<String, String> mapParam = new HashMap<String, String>();
        mapParam.put("lotteryType", lotteryType.getValue() + "");
        mapParam.put("phaseNo", phase);
        JSONArray array = internalDcService.getBjdcSchedule(mapParam);
        if (array == null) {
            return;
        }

        try {
            for (int i = 0; i < array.size(); i++) {
                JSONObject jo = array.getJSONObject(i);
                Integer matchNum = jo.getInt("matchNum");
                Date endSaleTime = CoreDateUtils.parseDate(jo.getString("endSaleTime"), CoreDateUtils.DATETIME);
                Date matchDate = CoreDateUtils.parseDate(jo.getString("matchDate"), CoreDateUtils.DATETIME);
                String score = jo.get("sr") == null ? "" : jo.getString("sr");
                Integer status = jo.getInt("status");
                String phaseNo = jo.getString("phase");
                Integer dcType = jo.getInt("dcType");
                Long id=new Long(phaseNo + matchNum);
                DcRace dcRace = dcRaceService.getByPhase(phase, matchNum, jo.getInt("dcType"));
                if (dcRace == null) {


                    String homeTeam = jo.getString("homeTeam");
                    String awayTeam = jo.getString("awayTeam");
                    String handicap = jo.getString("handicap");

                    String matchName = jo.getString("matchName");

                    Integer prizeStatus = jo.getInt("prizeStatus");

                    DcRace dc = new DcRace();
                    dc.setId(id);
                    dc.setCreateTime(new Date());
                    dc.setUpdateTime(new Date());
                    dc.setPhase(phaseNo);
                    dc.setDcType(dcType);
                    dc.setMatchNum(matchNum);
                    dc.setMatchDate(matchDate);
                    dc.setEndSaleTime(endSaleTime);
                    dc.setHomeTeam(homeTeam);
                    dc.setAwayTeam(awayTeam);
                    dc.setHandicap(handicap);
                    dc.setWholeScore(StringUtils.isEmpty(score) ? "" : score.split("\\$")[1]);
                    dc.setHalfScore(StringUtils.isEmpty(score) ? "" : score.split("\\$")[0]);
                    dc.setMatchName(matchName);
                    dc.setStatus(status);
                    dc.setPrizeStatus(prizeStatus);
                    dcRaceService.save(dc);
                } else {
                    //2：未开售 1销售中 -1  已停售-2  已开奖3  已计算4  已返奖

                    if (dcRace.getStatus().intValue() == RaceStatus.OPEN.value ||
                            dcRace.getStatus().intValue() == RaceStatus.UNOPEN.value ||
                            dcRace.getStatus().intValue() == RaceStatus.PAUSE.value) {
                        //赛事取消、暂停、暂停后又开启。
                        if (status.intValue() != dcRace.getStatus().intValue()) {
                            dcRace.setStatus(status);
                            dcRaceService.merge(dcRace);
                            //赛事取消  全部撤单 不再判断对阵结束时间
                            if (status.intValue() == RaceStatus.CANCEL.value) {
                                prizeErrorHandler.modifyTime(LotteryType.DC_SPF.value, phaseNo, matchNum.toString(), true);
                            }
                            continue;
                        }
                        //结束时间有变
                        if (!endSaleTime.equals(dcRace.getEndSaleTime())) {
                            dcRace.setStatus(status);
//							dcRace.setPrizeStatus(jo.getInt("prizeStatus"));
                            dcRace.setEndSaleTime(endSaleTime);
                            dcRaceService.merge(dcRace);
                            //修改订单中的时间
                            prizeErrorHandler.modifyTime(LotteryType.DC_SPF.value, phaseNo, matchNum.toString(), false);
                        }
                        //过期关闭
                        if ((new Date().getTime()) - dcRace.getEndSaleTime().getTime() > 0) {
                            dcRace.setStatus(status);
//							    dcRace.setPrizeStatus(jo.getInt("prizeStatus"));
                            dcRaceService.update(dcRace);
                        }
                        //关闭的 设置比分
                    } else if (dcRace.getStatus().intValue() == RaceStatus.CLOSE.value) {
                        if (!StringUtil.isEmpty(score)) {
                            dcRace.setStatus(status);
//							dcRace.setPrizeStatus(jo.getInt("prizeStatus"));
                            if(dcType==DcType.normal.value()){
                                String[] scores=score.split("\\$");
                                if (scores.length==2){
                                    dcRace.setHalfScore(score.split("\\$")[0]);
                                    dcRace.setWholeScore(score.split("\\$")[1]);
                                    dcRaceService.merge(dcRace);
                                }else{
                                    logger.error("比分有问题,matchNum={},原始比分是:{}",id,score);
                                }
                            }
                            if (dcType==DcType.sfgg.value()){
                                dcRace.setWholeScore(score);
                                dcRaceService.merge(dcRace);
                            }

                        }
                    }

                }
            }
        } catch (Exception e) {
            logger.error("单场赛程处理出错", e);
            logger.error("lotterytype={},phase={},array={}",lotteryType,phase, array);
        }

        closeMatch(phase);
    }

    protected void closeMatch(String phase) {
        try {
            List<Integer> statusList = new ArrayList<Integer>();
            statusList.add(RaceStatus.UNOPEN.getValue());
            statusList.add(RaceStatus.OPEN.getValue());
            List<DcRace> list = dcRaceService.getByPhaseAndStatus(phase, DcType.normal.value(), statusList);
            if (list != null && list.size() > 0) {
                for (DcRace dcRace : list) {
                    if ((new Date().getTime()) - (dcRace.getEndSaleTime().getTime() - getEndSaleForward()) >= 0) {
                        logger.error("单场:{}关闭", dcRace.toString());
                        dcRace.setStatus(RaceStatus.CLOSE.getValue());
                        dcRaceService.update(dcRace);

                    }
                }
            }
        } catch (Exception e) {
            logger.error("关闭单场过期赛程出错", e);
        }
    }

    public IVenderInternalDcService getInternalDcService() {
        return internalDcService;
    }

    public void setInternalDcService(IVenderInternalDcService internalDcService) {
        this.internalDcService = internalDcService;
    }


    public void proccessResult(String phaseNo, String sportballid) {
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("phaseNo", phaseNo);
        if (!StringUtil.isEmpty(sportballid)) {
            paramMap.put("sportballid", sportballid);
        }
        JSONArray ja = internalDcService.getBjdcDrawResult(paramMap);
        for (int i = 0; i < ja.size(); i++) {
            this.updateDcRace(phaseNo, ja.getJSONObject(i));
        }
    }

    private void updateDcRace(String phaseNo, JSONObject jo) {
        Integer matchNum = jo.getInt("matchNum");
        DcRace dr = dcRaceService.getByPhase(phaseNo, matchNum, jo.getInt("dcType"));
        if (dr == null) {
            return;
        }
        //结束或取消
        if (dr.getStatus().intValue() != RaceStatus.CLOSE.value) {
            return;
        }
        if (dr.getPrizeStatus().intValue() == PrizeStatus.pending.value || dr.getPrizeStatus().intValue() == PrizeStatus.draw.value
                || dr.getPrizeStatus().intValue() == PrizeStatus.rewarded.value) {
            return;
        }
        String spSfp = jo.getString("spSfp");
        String sfpResult = jo.getString("sfpResult");
        String spSxds = jo.getString("spSxds");
        String sxdsResult = jo.getString("sxdsResult");
        String spJqs = jo.getString("spJqs");
        String jqsResult = jo.getString("jqsResult");
        String spBf = jo.getString("spBf");
        String sfsp = jo.getString("spsf");
        String sfResult = jo.getString("sfResult");
        String bfResult = jo.getString("bfResult");
        String spBcsfp = jo.getString("spBcsfp");
        String bcsfpResult = jo.getString("bcsfpResult");

        if (StringUtil.isEmpty(spSfp) || StringUtil.isEmpty(sfpResult) || StringUtil.isEmpty(spSxds) || StringUtil.isEmpty(sxdsResult)
                || StringUtil.isEmpty(spJqs) || StringUtil.isEmpty(jqsResult) || StringUtil.isEmpty(spBf)
                || StringUtil.isEmpty(bfResult) || bfResult.contains("延") || sfpResult.contains("延") || StringUtil.isEmpty(spBcsfp) || StringUtil.isEmpty(bcsfpResult)) {
            return;
        }
        dr.setPrizeTime(new Date());
//		dr.setHalfScore(halfScore);
//		dr.setWholeScore(wholeScore);
        if (spSfp != null && StringUtils.isNotEmpty(spSfp)) {
            dr.setSpSfp(spSfp);
        }
        if (sfpResult != null && StringUtils.isNotEmpty(sfpResult)) {
            dr.setSfpResult(sfpResult);
        }
        if (sfsp != null && StringUtils.isNotEmpty(sfsp)) {
            dr.setSpSf(sfsp);
        }
        if (sfResult != null && StringUtils.isNotEmpty(sfResult)) {
            dr.setSfResult(sfResult);
        }
        if (spSxds != null && StringUtils.isNotEmpty(spSxds)) {
            dr.setSpSxds(spSxds);
        }
        if (sxdsResult != null && StringUtils.isNotEmpty(sxdsResult)) {
            dr.setSxdsResult(sxdsResult);
        }
        if (spJqs != null && StringUtils.isNotEmpty(spJqs)) {
            dr.setSpJqs(spJqs);
        }
        if (jqsResult != null && StringUtils.isNotEmpty(jqsResult)) {
            dr.setJqsResult(jqsResult);
        }
        if (spBf != null && StringUtils.isNotEmpty(spBf)) {
            dr.setSpBf(spBf);
        }
        if (bfResult != null && StringUtils.isNotEmpty(bfResult)) {
            dr.setBfResult(bfResult);
        }
        if (spBcsfp != null && StringUtils.isNotEmpty(spBcsfp)) {
            dr.setSpBcsfp(spBcsfp);
        }
        if (bcsfpResult != null && StringUtils.isNotEmpty(bcsfpResult)) {
            dr.setBcsfpResult(bcsfpResult);
        }

        dr.setPrizeStatus(PrizeStatus.result_set.value);  //开奖状态   获取到结果   开奖  派奖
        dr.setResultFrom("高德");
        dcRaceService.update(dr);
    }

    @Override
    protected List<LotteryType> getLotteryTypeList() {

        return LotteryType.getDc();
    }

    @Override
    protected boolean isNotPrize(String phase) {
        List<DcRace> list = dcRaceService.getCloseByPhaseAndPrizeStatus(phase, PrizeStatus.pending.value, DcType.normal);
        if (list != null && list.size() > 0) {
            return true;
        }
        return false;
    }
}
