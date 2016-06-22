package com.lottery.ticket.vender.notice;

import com.lottery.common.contains.CharsetConstant;
import com.lottery.common.contains.QueueName;
import com.lottery.common.contains.TerminalPropertyConstant;
import com.lottery.common.contains.lottery.TerminalType;
import com.lottery.common.contains.ticket.TicketVender;
import com.lottery.common.util.CoreHttpUtils;
import com.lottery.common.util.JsonUtil;
import com.lottery.core.IdGeneratorService;
import com.lottery.core.domain.terminal.Terminal;
import com.lottery.core.domain.terminal.TerminalProperty;
import com.lottery.core.handler.VenderConfigHandler;
import com.lottery.core.service.TicketService;
import com.lottery.core.service.queue.QueueMessageSendService;
import com.lottery.core.terminal.ITerminalSelector;
import com.lottery.ticket.IVenderConfig;
import com.lottery.ticket.IVenderConverter;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by fengqinyun on 15/6/16.
 */
public abstract class AbstractVenderNoticeProcess implements IVenderNoticeProcess{
    protected final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    @Autowired
    protected ITerminalSelector terminalSelector;

    @Autowired
    protected IdGeneratorService idGeneratorService;
    @Autowired
    protected TicketService ticketService ;
    @Autowired
    protected VenderConfigHandler venderConfigService;

    @Autowired
    protected QueueMessageSendService queueMessageSendService;

    @Resource(name = "venderConverterBinder")
    protected Map<TerminalType, IVenderConverter> venderConverterBinder;

    protected ThreadLocal<Long> terminalIdThreadLocal = new ThreadLocal<Long>();

    protected ThreadLocal<IVenderConfig> venderConfigThreadLocal = new ThreadLocal<IVenderConfig>();

    protected ThreadLocal<String> ipThreadLocal=new ThreadLocal<String>();


    protected String getCharset() {
        return CharsetConstant.CHARSET_UTF8;
    }
    protected String readRequestString(HttpServletRequest request) throws IOException {
        request.setCharacterEncoding(this.getCharset());
        BufferedReader reader = request.getReader();
        StringBuilder buffer = new StringBuilder();
        int n;
        while ((n = reader.read()) != -1) {
            buffer.append((char) n);
        }
        reader.close();
        return buffer.toString();
    }
    abstract protected TerminalType getTerminalType();

    protected abstract String getAgentId();
    protected String getAgentCode(Map<String,String> requestMap){
        String agentId=getAgentId();
        if (agentId!=null){
            return requestMap.get(agentId);
        }
        return null;
    }

    protected IVenderConverter getVenderConverter(){
        return this.venderConverterBinder.get(getTerminalType());
    }

    protected Map<String, String> convertRequestParameter(String requestString) {
        return CoreHttpUtils.parseQueryString(requestString);
    }


    protected  boolean isEmpty(String requestString){
        if (StringUtils.isBlank(requestString)) {
            // 空消息不做处理
            logger.error("收到空消息, 不做处理, 终端类型为: {}", this.getTerminalType());
            return true;
        }
        return false;
    }



    public void process(HttpServletRequest request,
                           HttpServletResponse response)throws Exception{

        String ip=CoreHttpUtils.getClientIP(request);
        String requestString = this.readRequestString(request);
        ipThreadLocal.set(ip);
        if (isEmpty(requestString)) {
            return;
        }



        Map<String,String> requestMap = this.convertRequestParameter(requestString);

        if (requestMap == null) {
            logger.error("解析请求参数出错为空,直接返回,参数为:{},ip={}",requestString,ip);
            return;
        }


        String agentCode = this.getAgentCode(requestMap);
        Long terminalId = null;
        IVenderConfig venderConfig=null;
        List<TerminalProperty> terminalPropertys = this.getTerminalProperty(agentCode);
        if(terminalPropertys==null||terminalPropertys.size()==0){
            logger.error("未找到对应的终端, 不做处理, terminalType={}, requestString={},ip={}", this.getTerminalType(), requestString,ip);
            return;
        }
        for(TerminalProperty property: terminalPropertys){
            //如果agentCode为空直接返回所有属性
            if(StringUtils.isBlank(agentCode)){
                terminalId = property.getTerminalId();
                break;
            }
            if (TerminalPropertyConstant.AGENT_CODE.equals(property.getTerminalKey())) {
                if (agentCode.equals(property.getTerminalValue())) {
                    terminalId = property.getTerminalId();
                }
            }
        }
        venderConfig= this.getVenderConfig(terminalPropertys);
        if (terminalId==null||venderConfig==null){
            logger.error("terminalId:{},venderconfig:{},不能同时为空",terminalId,venderConfig);
            return;
        }
        this.terminalIdThreadLocal.set(terminalId);
        this.venderConfigThreadLocal.set(this.getVenderConfig(terminalPropertys));

        writeNoticeLog("接收ip=["+ip+"]的原始字符串:"+requestString);
        String responstStr=null;
        boolean flag=this.validate(requestMap, venderConfig);
        if (!flag){
            writeNoticeLog("加密验证未通过");
            responstStr="validate error";
        }else{
            responstStr=execute(requestMap,venderConfig);
        }
        writeNoticeLog("通知返回:"+responstStr);
        response.getWriter().print(responstStr);
        response.getWriter().close();


    }
    /**
     * 加密验证
     * */
    protected  abstract boolean validate(Map<String,String> requestMap,IVenderConfig venderConfig);



    abstract protected  String execute(Map<String,String> requestMap,IVenderConfig venderConfig);


    protected List<TerminalProperty> getTerminalProperty(String agentCode) {
        List<Terminal> terminalList = null;
        try {
            terminalList = terminalSelector.getTerminalList(getTerminalType());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            logger.error("终端类型={}获取属性配置出错",getTerminalType());
            return null;
        }
        // 如果terminalList对象为null，下面for循环的时候会捕获空指针异常，打印异常消息后线程休眠几秒继续while循环。
        if(terminalList == null || terminalList.isEmpty()) {
            logger.error("获取的终端列表为空，通知处理异常!");
            return null;
        }else{
            for (Terminal terminal : terminalList) {
                Long terminalId = terminal.getId();
                List<TerminalProperty> terminalPropertys = null;
                try {
                    terminalPropertys = terminalSelector.getTerminalPropertyFromCache(terminalId);
                    if(terminalPropertys==null||terminalPropertys.size()==0){
                        logger.error("终端号id={}没有属性配置",terminalId);
                        continue;
                    }else{
                        //如果agentCode为空直接返回所有属性
                        if(agentCode == null || agentCode.isEmpty()){
                            return terminalPropertys;
                        }
                        for(TerminalProperty terminalProperty: terminalPropertys){
                            if (TerminalPropertyConstant.AGENT_CODE.equals(terminalProperty.getTerminalKey())) {
                                if (agentCode.equals(terminalProperty.getTerminalValue())) {
                                    return terminalPropertys;
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                    logger.error("({})号终端的票时出错, 退出本次处理", terminalId);

                }
            }
        }
        return null;
    }

    protected IVenderConfig getVenderConfig(List<TerminalProperty> terminalPropertyList){
     return venderConfigService.getVenderConfig(getTerminalType(),terminalPropertyList);
    }

    protected void writeNoticeLog(String requestString){
        Long terminalId = terminalIdThreadLocal.get();
        Logger notifyLogger = LoggerFactory.getLogger("notify_logger");
        MDC.put("terminalId", terminalId.toString());
        notifyLogger.error(requestString);
    }

    protected TicketVender createInit(){
        TicketVender ticketVender = new TicketVender();
        ticketVender.setTicketNotify(true);
        ticketVender.setTerminalType(this.getTerminalType());
        ticketVender.setTerminalId(terminalIdThreadLocal.get());
        return ticketVender;
    }

    protected void ticketVenderProcess(TicketVender	ticketVender){
        try{

            String body= JsonUtil.toJson(ticketVender);
            queueMessageSendService.sendMessage(QueueName.ticketNotice,body);
        }catch (Exception e){
            logger.error("发送通知消息体出错",e);
        }
    }

}
