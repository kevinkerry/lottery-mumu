package com.lottery.draw.impl;

import com.lottery.common.contains.ErrorCode;
import com.lottery.common.contains.lottery.LotteryType;
import com.lottery.common.contains.lottery.PhaseStatus;
import com.lottery.common.contains.lottery.TerminalType;
import com.lottery.common.contains.lottery.TicketResultStatus;
import com.lottery.common.util.CoreDateUtils;
import com.lottery.common.util.HTTPUtil;
import com.lottery.common.util.MD5Util;
import com.lottery.common.util.StringUtil;
import com.lottery.core.IdGeneratorService;
import com.lottery.core.domain.LotteryPhase;
import com.lottery.core.domain.ticket.Ticket;
import com.lottery.draw.AbstractVenderPhaseDrawWorker;
import com.lottery.draw.LotteryDraw;
import com.lottery.ticket.IPrizeNumConverter;
import com.lottery.ticket.IVenderConfig;
import com.lottery.ticket.IVenderConverter;
import com.lottery.ticket.sender.worker.XmlParse;
import com.lottery.ticket.vender.impl.zch.ZchConfig;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class ZchPhaseDrawWorker extends AbstractVenderPhaseDrawWorker {
    private final Logger logger = LoggerFactory.getLogger(getClass().getName());
    protected static Map<Integer, IPrizeNumConverter> prizeNumConverterMap = new HashMap<Integer, IPrizeNumConverter>();
    @Autowired
    IdGeneratorService igenGeneratorService;
    private String openPrizeNum = "010";
    private String queryCode = "011";


    @Override
    protected LotteryDraw get(IVenderConfig config, Integer lotteryType, String phase) {
        if (config == null) {
            return null;
        }
        IVenderConverter zchConverter = getVenderConverter();
        String lotno = zchConverter.convertLotteryType(LotteryType.getLotteryType(lotteryType));
        String convertPhase = zchConverter.convertPhase(LotteryType.getPhaseType(lotteryType), phase);
        if (StringUtils.isBlank(lotno) || StringUtils.isBlank(convertPhase)) {
            return null;
        }
        String message = getElement(lotno, convertPhase, config);
        String returnStr = "";
        try {
            returnStr = HTTPUtil.sendPostMsg(config.getRequestUrl(), message);
        } catch (Exception e) {
            logger.error("开奖结果接口返回异常" + e);
        }

        // 处理结果
        try {
            if (StringUtils.isNotBlank(returnStr)) {
                LotteryDraw lotteryDraw = dealResult(returnStr, phase, lotteryType, zchConverter);
                if (lotteryDraw != null) {
                    logger.error("中彩汇开奖信息查询,彩种:{},期号{},发送的是:{},返回结果是：{}", new Object[]{lotteryType, phase, message, returnStr});
                }
                return lotteryDraw;
            }
        } catch (Exception e) {
            logger.error("处理开奖结果异常" + e);
        }
        return null;
    }

    @Override
    protected TerminalType getTerminalType() {
        return TerminalType.T_ZCH;
    }

    /**
     * 查奖结果处理
     *
     * @param desContent
     * @param count
     * @return
     */
    @SuppressWarnings("unchecked")
    private LotteryDraw dealResult(String desContent, String issue, int lotteryType, IVenderConverter zchConverter) {
        LotteryDraw lotteryDraw = new LotteryDraw();
        try {
            Map<String, String> map = XmlParse.getElementAttribute("body/", "response", desContent);
            if ("0000".equals(map.get("code"))) {
                Map<String, String> mapParam = XmlParse.getElementAttribute("body/response/", "issue", desContent);
                Map<String, String> mapP = XmlParse.getElementText("body/response/", "issue", desContent);
                if (mapParam != null) {

                    String lotno = mapParam.get("lotteryId");
                    String prizecode = mapParam.get("bonusNumber");
                    String openPrizeTime = mapParam.get("bonusTime");
                    String totalPrize = mapParam.get("bonusTotal");
                    String totalSale = mapParam.get("globalSaleTotal");
                    String content = mapP.get("bonusClass");
                    String status = mapParam.get("status");
                    StringBuilder sBuilder = new StringBuilder();
                    if (StringUtils.isNotEmpty(content)) {
                        JSONArray jsonArray = JSONArray.fromObject(content);
                        Iterator<JSONObject> iterator = jsonArray.iterator();
                        int i = 0;
                        while (iterator.hasNext()) {
                            JSONObject jsObject = iterator.next();
                            if (LotteryType.CJDLT.getValue() == lotteryType && prizeNum.containsKey(jsObject.get("n"))) {
                                sBuilder.append(prizeNum.get(jsObject.get("n")));
                            } else {
                                sBuilder.append(jsObject.get("n"));
                            }
                            sBuilder.append("_" + jsObject.get("m") + "" + "_" + jsObject.get("c"));
                            if (i < jsonArray.size() - 1) {
                                sBuilder.append(LotteryDraw.LEVEV_SPILTSTRI);
                            }
                            i++;
                        }
                    }

                    if (StringUtils.isNotBlank(prizecode) && !"-".equals(prizecode)) {
                        lotteryDraw.setPhase(issue);
                        lotteryDraw.setLotteryType(zchConverter.getLotteryMap(lotno).getValue());
                        IPrizeNumConverter prConverter = prizeNumConverterMap.get(lotteryType);
                        lotteryDraw.setWindCode(prConverter.convert(prizecode));
                        lotteryDraw.setResultDetail(sBuilder.toString());
                        lotteryDraw.setTimeDraw(openPrizeTime);
                        if ("3".equals(status) || "4".equals(status)) {
                            lotteryDraw.setStatus(PhaseStatus.prize_open.getValue());
                        } else if ("2".equals(status)) {
                            lotteryDraw.setStatus(PhaseStatus.close.getValue());
                        } else if ("1".equals(status)) {
                            lotteryDraw.setStatus(PhaseStatus.open.getValue());
                        }
                        lotteryDraw.setJackpot(totalPrize);
                        if (StringUtils.isNotBlank(totalSale) && Double.parseDouble(totalSale) > 9) {
                            totalSale = ConvertNum(totalSale.trim());
                        }
                        lotteryDraw.setVolumeOfSales(totalSale);
                        return lotteryDraw;
                    }
                    return null;
                }
            }

        } catch (DocumentException e) {
            logger.error("处理开奖结果异常", e);
        }
        return null;
    }
    protected String ConvertNum(String totalSaleNum) {
        int len = totalSaleNum.split("\\.")[0].length();
        int leftLen = 0;
        int num = 0;
        boolean innum = false;
        if (len % 3 > 0) {
            leftLen = len % 3;
            innum = true;
            num = (len / 3) + 1;
        } else {
            num = len / 3;
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0, j = 3; i < num; i++) {
            String ss = "";
            if (innum && i == 0) {
                ss = totalSaleNum.substring(j - 3, leftLen);
                j = leftLen + 3;
            } else {
                ss = totalSaleNum.substring(j - 3, j);
                j = j + 3;
                ;
            }
            stringBuilder.append(ss);
            if (i != num - 1) {
                stringBuilder.append(",");
            }
        }
        return stringBuilder.toString() + ".00";
    }

    private String getElement(String lotteryNo, String phase, IVenderConfig zchConfig) {
        String md = "";
        XmlParse xmlParse = null;
        try {
            xmlParse = ZchConfig.addZchHead(zchConfig.getAgentCode(), openPrizeNum);
            HashMap<String, Object> bodyAttr = new HashMap<String, Object>();
            bodyAttr.put("lotteryId", lotteryNo);
            bodyAttr.put("issue", phase);
            xmlParse.addBodyElementAndAttribute("query", "", bodyAttr);
            try {
                md = MD5Util.toMd5(zchConfig.getAgentCode() + zchConfig.getKey() + xmlParse.getBodyElement().asXML());
            } catch (Exception e) {
                logger.error("加密异常" + e.getMessage());
            }
            xmlParse.addHeaderElement("digest", md);
            return "msg=" + xmlParse.asXML();
        } catch (Exception e) {
            logger.error("开奖号码查询异常" + e.getMessage());
        }
        return null;
    }

    protected static Map<Integer, String> prizeNum = new HashMap<Integer, String>();

    static {

        prizeNum.put(11, "z1");
        prizeNum.put(12, "z2");
        prizeNum.put(13, "z3");
        prizeNum.put(14, "z4");
        prizeNum.put(15, "z5");
        prizeNum.put(16, "z6");
        prizeNum.put(17, "z7");
        prizeNum.put(18, "z8");
        // 开奖号码转换
        // 3D
        IPrizeNumConverter sandConverter = new IPrizeNumConverter() {
            @Override
            public String convert(String content) {
                String[] ss = content.split(",");
                StringBuilder srBuilder = new StringBuilder();
                int i = 0;
                for (String s : ss) {
                    srBuilder.append(s);
                    if (i < ss.length - 1) {
                        srBuilder.append(",");
                    }
                    i++;
                }
                return srBuilder.toString();
            }
        };

        // //双色球七乐彩 大乐彩
        IPrizeNumConverter qlcConverter = new IPrizeNumConverter() {
            @Override
            public String convert(String content) {
                content = content.replace("#", "|");
                return content;
            }
        };
        // 足彩
        IPrizeNumConverter zcConverter = new IPrizeNumConverter() {
            @Override
            public String convert(String content) {
                String[] numbers = content.split("\\*");
                StringBuilder strBuilder = new StringBuilder();
                int i = 0;
                for (String num : numbers) {
                    if (!StringUtil.isEmpty(num)) {
                        strBuilder.append(num);
                    } else {
                        strBuilder.append("*");
                    }
                    if (i != numbers.length - 1) {
                        strBuilder.append(",");
                    }
                    i++;
                }
                return strBuilder.toString();
            }
        };

        // 足彩胜平负
        IPrizeNumConverter zcspfConverter = new IPrizeNumConverter() {
            @Override
            public String convert(String content) {
                String[] numbers = content.split("\\*");
                StringBuilder strBuilder = new StringBuilder();
                int i = 0;
                for (String num : numbers) {
                    if (!StringUtil.isEmpty(num)) {
                        if ("9".equals(num)) {
                            strBuilder.append("*");
                        } else {
                            strBuilder.append(num);
                        }
                    } else {
                        strBuilder.append("*");
                    }
                    if (i != numbers.length - 1) {
                        strBuilder.append(",");
                    }
                    i++;
                }
                return strBuilder.toString();
            }
        };

        // 快彩
        IPrizeNumConverter cqsscConverter = new IPrizeNumConverter() {
            @Override
            public String convert(String content) {
                return content;
            }
        };
        prizeNumConverterMap.put(LotteryType.SSQ.getValue(), qlcConverter);
        prizeNumConverterMap.put(LotteryType.F3D.getValue(), sandConverter);
        prizeNumConverterMap.put(LotteryType.QLC.getValue(), qlcConverter);
        prizeNumConverterMap.put(LotteryType.CJDLT.getValue(), qlcConverter);
        prizeNumConverterMap.put(LotteryType.PL3.getValue(), sandConverter);
        prizeNumConverterMap.put(LotteryType.PL5.getValue(), sandConverter);
        prizeNumConverterMap.put(LotteryType.QXC.getValue(), sandConverter);

        prizeNumConverterMap.put(LotteryType.ZC_SFC.getValue(), zcspfConverter);
        prizeNumConverterMap.put(LotteryType.ZC_RJC.getValue(), zcConverter);
        prizeNumConverterMap.put(LotteryType.ZC_JQC.getValue(), zcConverter);
        prizeNumConverterMap.put(LotteryType.ZC_BQC.getValue(), zcConverter);

        prizeNumConverterMap.put(LotteryType.CQSSC.getValue(), cqsscConverter);
        prizeNumConverterMap.put(LotteryType.SD_11X5.getValue(), cqsscConverter);
        prizeNumConverterMap.put(LotteryType.AHK3.getValue(), cqsscConverter);
    }


    protected boolean ticketPrize(Ticket ticket, IVenderConfig config) {
        // 拼串
        String messageStr = getElement(ticket, config);
        if (StringUtils.isEmpty(messageStr)) {
            return false;
        }
        String returnStr = null;
        try {
            returnStr = HTTPUtil.sendPostMsg(config.getRequestUrl(), messageStr);
        } catch (Exception e) {
            logger.error("中彩汇查询票中奖接口返回异常", e);
        }
        if (StringUtils.isNotEmpty(returnStr)) {
            // 查票处理结果
            try {
                return dealResult(returnStr, ticket);
            } catch (Exception e) {
                logger.error("中彩汇查询票中奖{}", e);
            }
        } else {
            logger.error("中彩汇查询票中奖返回空");
        }

        return false;
    }

    /**
     * 查开奖结果处理
     *
     * @param desContent
     * @return
     */
    private Boolean dealResult(String desContent, Ticket ticket) {
        try {
            Map<String, String> mapResult = XmlParse.getElementAttribute("body/", "response", desContent);
            String code = mapResult.get("code");
            if (ErrorCode.Success.getValue().equals(Integer.parseInt(code) + "")) {// 成功
                HashMap<String, String> map = XmlParse.getElementAttribute("body/response/", "ticket", desContent);
                if (map != null) {
                    String status = map.get("status");
                    String bonusStatus = map.get("bonusStatus");
                    String bonusAmount = map.get("bonusAmount");// 税后
                    String fixBonusAmount = map.get("fixBonusAmount");// 税前
                    if ("2".equals(status) && "1".equals(bonusStatus)) {// 交易成功且中奖
                        ticket.setPosttaxPrize(new BigDecimal(bonusAmount.replace(",", "")).multiply(new BigDecimal("100")));
                        ticket.setPretaxPrize(new BigDecimal(fixBonusAmount.replace(",", "")).multiply(new BigDecimal("100")));
                        ticket.setTicketResultStatus(TicketResultStatus.win_little.getValue());
                        return true;
                    } else {
                        logger.error("中彩汇查询票中奖票号{}交易结果{}", ticket.getId(), status);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("中彩汇查奖处理结果异常{}", e);
        }
        return false;
    }

    private String getElement(Ticket ticket, IVenderConfig zchConfig) {
        String md = "";
        XmlParse xmlParse = null;
        try {
            // 头部
            xmlParse = ZchConfig.addZchHead(zchConfig.getAgentCode(), queryCode);
            Element element = xmlParse.addBodyElementAndAttribute("tickets", "", new HashMap<String, Object>());

            HashMap<String, Object> bodyAttr = null;
            bodyAttr = new HashMap<String, Object>();
            bodyAttr.put("ticketId", ticket.getId());
            xmlParse.addBodyElementAndAttribute("ticket", "", bodyAttr, element);
            try {
                md = MD5Util.toMd5(zchConfig.getAgentCode() + zchConfig.getKey() + xmlParse.getBodyElement().asXML());
            } catch (Exception e) {
                logger.error("加密异常" + e.getMessage());
            }
            xmlParse.addHeaderElement("digest", md);
            return "msg=" + xmlParse.asXML();
        } catch (Exception e) {
            logger.error("中彩汇查票中奖拼串异常{}", e);
        }
        return null;
    }

    @Override
    protected List<LotteryPhase> getOnSalePhaseList(int lotteryType, IVenderConfig venderConfig) {


        LotteryPhase localPhase = getPhase(lotteryType, venderConfig);
        if (localPhase != null) {
            String phase = localPhase.getPhase();
            Date startTime = localPhase.getStartSaleTime();
            Date endTicketTime = localPhase.getEndTicketTime();
            Date endTime = localPhase.getEndSaleTime();
            if (phase == null || startTime == null || endTime == null || endTicketTime == null) {
                logger.error("彩种:{}查询新期参数不全", lotteryType);
                return null;
            }
            return venderPhaseDrawHandler.updatePhase(lotteryType, phase, startTime, endTime, endTicketTime);
        }

        return null;

    }

    protected LotteryPhase getPhase(int lotteryType, IVenderConfig venderConfig) {
        LotteryPhase lotteryPhase = new LotteryPhase();
        try {
            IVenderConverter zchConverter = getVenderConverter();
            String lotno = zchConverter.convertLotteryType(LotteryType.getLotteryType(lotteryType));

            if (StringUtils.isBlank(lotno)) {
                return null;
            }

            String message = getElement(lotno, null, venderConfig);
            String returnStr = HTTPUtil.sendPostMsg(venderConfig.getRequestUrl(), message);

            logger.error("中彩汇新期信息查询,彩种:{},发送的是:{},返回结果是：{}", new Object[]{lotteryType, message, returnStr});

            Map<String, String> map = XmlParse.getElementAttribute("body/", "response", returnStr);
            if ("0000".equals(map.get("code"))) {
                Map<String, String> mapParam = XmlParse.getElementAttribute("body/response/", "issue", returnStr);
                if (mapParam != null) {
                    String startTime = mapParam.get("startTime");
                    String endTime = mapParam.get("endTime");
                    String stopTime = mapParam.get("stopTime");
                    String status = mapParam.get("status");
                    String name = mapParam.get("name");
                    if ("1".equals(status)) {//销售中
                        lotteryPhase.setEndSaleTime(CoreDateUtils.parseDateTime(endTime));
                        lotteryPhase.setEndTicketTime(CoreDateUtils.parseDateTime(stopTime));
                        lotteryPhase.setStartSaleTime(CoreDateUtils.parseDateTime(startTime));
                        String phaseNo = zchConverter.convertPhaseReverse(LotteryType.getLotteryType(lotteryType), name);
                        lotteryPhase.setPhase(phaseNo);
                        return lotteryPhase;
                    }

                    return null;
                }
            }

        } catch (Exception e) {
            logger.error("查询彩种:{}新期异常", lotteryType, e);
        }
        return null;
    }


}
