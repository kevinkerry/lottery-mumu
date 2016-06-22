package com.lottery.draw.impl;

import com.lottery.common.contains.CharsetConstant;
import com.lottery.common.contains.lottery.PhaseStatus;
import com.lottery.common.contains.lottery.TerminalType;
import com.lottery.common.util.CoreDateUtils;
import com.lottery.common.util.CoreStringUtils;
import com.lottery.common.util.DESCoder;
import com.lottery.common.util.HTTPUtil;
import com.lottery.core.domain.LotteryPhase;
import com.lottery.draw.AbstractVenderPhaseDrawWorker;
import com.lottery.draw.LotteryDraw;
import com.lottery.ticket.IVenderConfig;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by fengqinyun on 15/9/6.
 */
@Service
public class OwnCenterPhaseDrawWorker extends AbstractVenderPhaseDrawWorker {

    @Override
    protected TerminalType getTerminalType() {
        return TerminalType.T_OWN_CENTER;
    }

    @Override
    protected List<LotteryPhase> getOnSalePhaseList(int lotteryType, IVenderConfig venderConfig) {
        return null;
    }



    @Override
    protected LotteryDraw get(IVenderConfig config, Integer lotteryType, String phase) {
        Document document= DocumentHelper.createDocument();
        Element root=document.addElement("content");
        Element head=root.addElement("head");
        Element version=head.addElement("version");
        version.setText("1.0");

        Element merchant=head.addElement("merchant");
        merchant.setText(config.getAgentCode());
        Element command=head.addElement("command");
        command.setText("805");

        Element timestamp=head.addElement("timestamp");
        String times= CoreDateUtils.getNowDateYYYYMMDDHHMMSS();
        timestamp.setText(times);

        Element messageid=head.addElement("messageid");
        messageid.setText(config.getAgentCode() + times);




        Element bodyRoot=DocumentHelper.createElement("message");
        Element content=bodyRoot.addElement("content");
        Element lotterytype=content.addElement("lotterytype");
        lotterytype.setText(lotteryType+"");
        Element phaseE=content.addElement("phase");
        phaseE.setText(phase);
        String md5Str = "805" + times + config.getAgentCode() + config.getKey();
        String md5 = CoreStringUtils.md5(md5Str, CharsetConstant.CHARSET_UTF8);

        String body = DESCoder.desEncrypt(bodyRoot.asXML(), config.getKey());

        Element bodyE=root.addElement("body");
        bodyE.setText(body);

        Element signature=root.addElement("signature");
        signature.setText(md5);
        String response=null;
        try{
            response= HTTPUtil.post(config.getRequestUrl(), root.asXML(), CharsetConstant.CHARSET_UTF8);


            Document reroot=DocumentHelper.parseText(response);
            Element rootElt = reroot.getRootElement();
            Element rehead=rootElt.element("head");
            Element errorcodeE=rehead.element("errorcode");
            String errorcode=errorcodeE.getText();
            if (errorcode.equals("0")){
                String bodystr=rootElt.element("body").getText();
                String responseString=DESCoder.desDecrypt(bodystr, config.getKey());
                Document bodydoc  = DocumentHelper.parseText(responseString);
                Element bRoot=bodydoc.getRootElement();
                LotteryDraw lotteryDraw=new LotteryDraw();
                lotteryDraw.setLotteryType(lotteryType);
                lotteryDraw.setPhase(phase);
                lotteryDraw.setResponsMessage(responseString);
                Element wincode=bRoot.element("wincode");
                if (wincode!=null){
                    lotteryDraw.setStatus(PhaseStatus.prize_open.getValue());
                    lotteryDraw.setWindCode(wincode.getText());
                }
                Element windetail=bRoot.element("windetail");
                if (windetail!=null){
                    lotteryDraw.setWindCode(windetail.getText());
                }
                return lotteryDraw;

            }else{
                //logger.error("返回字符串全局不为0,response={}",response);
            }
        }catch (Exception e){
          logger.error("出票中心抓取开奖号码出错,response={}",response,e);
        }
        return null;
    }
}
