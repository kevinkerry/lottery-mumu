package com.lottery.draw.impl;

import com.lottery.common.contains.lottery.LotteryType;
import com.lottery.common.contains.lottery.PhaseStatus;
import com.lottery.common.contains.lottery.TerminalType;
import com.lottery.common.util.DateUtil;
import com.lottery.common.util.HTTPUtil;
import com.lottery.common.util.MD5Util;
import com.lottery.core.IdGeneratorService;
import com.lottery.core.domain.LotteryPhase;
import com.lottery.core.domain.ticket.Ticket;
import com.lottery.draw.AbstractVenderPhaseDrawWorker;
import com.lottery.draw.LotteryDraw;
import com.lottery.ticket.IPrizeNumConverter;
import com.lottery.ticket.IVenderConfig;
import com.lottery.ticket.IVenderConverter;
import com.lottery.ticket.sender.worker.XmlParse;
import com.lottery.ticket.vender.impl.huancai.HuancaiConfig;
import org.apache.commons.lang.StringUtils;
import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class HuanCaiPhaseDrawWorker extends AbstractVenderPhaseDrawWorker {
    private final Logger logger = LoggerFactory.getLogger(getClass().getName());
    protected static Map<Integer, IPrizeNumConverter> prizeNumConverterMap = new HashMap<Integer, IPrizeNumConverter>();
    @Autowired
    IdGeneratorService igenGeneratorService;
    private String openPrizeNum = "1001";


    @Override
    protected LotteryDraw get(IVenderConfig config, Integer lotteryType, String phase) {
        if (config == null) {
            return null;
        }
        IVenderConverter huancaiConverter = getVenderConverter();
        String lotno = huancaiConverter.convertLotteryType(LotteryType.getLotteryType(lotteryType));
        String convertPhase = huancaiConverter.convertPhase(LotteryType.getPhaseType(lotteryType), phase);
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
                LotteryDraw lotteryDraw = dealResult(returnStr, phase, lotteryType, huancaiConverter);
                if (lotteryDraw != null) {
                    logger.error("环彩开奖信息查询,彩种:{},期号{},发送的是:{},返回结果是：{}", new Object[]{lotteryType, phase, message, returnStr});
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
        return TerminalType.T_HUANCAI;
    }

    /**
     * 查奖结果处理
     *
     * @param desContent
     * @param count
     * @return
     */
    private LotteryDraw dealResult(String desContent, String issue, int lotteryType, IVenderConverter zchConverter) {
        LotteryDraw lotteryDraw = new LotteryDraw();
        try {
            Map<String, String> map = XmlParse.getElementAttribute("/", "response", desContent);
            if ("0000".equals(map.get("code"))) {
                Map<String, String> mapParam = XmlParse.getElementAttribute("response/", "issue", desContent);
                if (mapParam != null) {
                    String prizecode = mapParam.get("bonusCode");
                    if (StringUtils.isNotBlank(prizecode) && !"-".equals(prizecode)) {
                        lotteryDraw.setPhase(issue);
                        lotteryDraw.setLotteryType(lotteryType);
                        IPrizeNumConverter prConverter = prizeNumConverterMap.get(lotteryType);
                        lotteryDraw.setWindCode(prConverter.convert(prizecode));
                        if (prizecode!=null&&!"".equals(prizecode)) {
                            lotteryDraw.setStatus(PhaseStatus.prize_open.getValue());
                        }
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
   
    private String getElement(String lotteryNo, String phase, IVenderConfig huancaiConfig) {
        String md = "";
        XmlParse xmlParse = null;
        try {
        	String messageId=DateUtil.format("yyyyMMddHHmmss", new Date());;
        	xmlParse = HuancaiConfig.addGxHead(openPrizeNum,huancaiConfig.getAgentCode(),messageId);
            HashMap<String, Object> bodyAttr = new HashMap<String, Object>();
            bodyAttr.put("lottid", lotteryNo);
            bodyAttr.put("name", phase);
            xmlParse.addBodyElementAndAttribute("issue","", bodyAttr);
            try {
    			md = MD5Util.toMd5(huancaiConfig.getAgentCode()+messageId + huancaiConfig.getKey() + xmlParse.getBodyElement().asXML());
    		} catch (Exception e) {
    			logger.error("加密异常" + e.getMessage());
    		}
    		xmlParse.addHeaderElement("digest", md);
    		return  "cmd="+openPrizeNum+"&msg="+xmlParse.asXML();
        } catch (Exception e) {
            logger.error("开奖号码查询异常" + e.getMessage());
        }
        return null;
    }

    protected static Map<Integer, String> prizeNum = new HashMap<Integer, String>();

    static {

        // 开奖号码转换
        // 快彩
        IPrizeNumConverter jsk3Converter = new IPrizeNumConverter() {
            @Override
            public String convert(String content) {
                return content;
            }
        };
      
        prizeNumConverterMap.put(LotteryType.JSK3.getValue(), jsk3Converter);
    }


    protected boolean ticketPrize(Ticket ticket, IVenderConfig config) {
        return false;
    }

    @Override
    protected List<LotteryPhase> getOnSalePhaseList(int lotteryType, IVenderConfig venderConfig) {

        return null;

    }

    protected LotteryPhase getPhase(int lotteryType, IVenderConfig venderConfig) {
        
        return null;
    }


}
