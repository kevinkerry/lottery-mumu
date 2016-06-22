package com.lottery.controller;

import com.lottery.ticket.vender.notice.impl.Gzcp.GzcpNoticeProcess;
import com.lottery.ticket.vender.notice.impl.HuAi.HuAiNoticeProcess;
import com.lottery.ticket.vender.notice.impl.JYDP.JYDPNoticeProcess;
import com.lottery.ticket.vender.notice.impl.JinNuo.JinNuoNoticeProcess;
import com.lottery.ticket.vender.notice.impl.ZhangYi.ZYNoticeProcess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;


/**
 * Created by fengqinyun on 15/6/17.
 * 出票商通知类
 */

@Controller
@RequestMapping("/notice")
public class VenderNoticeController {
    private final Logger logger= LoggerFactory.getLogger(getClass());
    @Autowired
    protected GzcpNoticeProcess gzcpNoticeProcess;
    @Autowired
    protected HuAiNoticeProcess huAiNoticeProcess;
    @Autowired
    protected ZYNoticeProcess zyNoticeProcess;
    @Autowired
    protected JinNuoNoticeProcess jinnuoNoticeProcess;
    @Autowired
    protected JYDPNoticeProcess jydpNoticeProcess;
    @RequestMapping("/gzcp")
    public  void gzcp(HttpServletRequest request,HttpServletResponse response){

        try {
            gzcpNoticeProcess.process(request,response);
        } catch (Exception e) {
           logger.error("广州出票通知处理出错",e);
        }
    }
    @RequestMapping("/hacp")
    public  void hacp(HttpServletRequest request,HttpServletResponse response){

        try {
            huAiNoticeProcess.process(request,response);
        } catch (Exception e) {
            logger.error("互爱出票通知处理出错",e);
        }
    }
    
    @RequestMapping("/zy")
    public  void zy(HttpServletRequest request,HttpServletResponse response){

        try {
        	zyNoticeProcess.process(request,response);
        } catch (Exception e) {
            logger.error("掌奕出票通知处理出错",e);
        }
    }


    @RequestMapping("/jinnuo")
    public  void jinnuo(HttpServletRequest request,HttpServletResponse response){

        try {
        	jinnuoNoticeProcess.process(request,response);
        } catch (Exception e) {
            logger.error("金诺出票通知处理出错",e);
        }
    }
    @RequestMapping("/jydp")
    public  void jydp(HttpServletRequest request,HttpServletResponse response){

        try {
        	jydpNoticeProcess.process(request,response);
        } catch (Exception e) {
            logger.error("嘉优出票通知处理出错",e);
        }
    }

    @RequestMapping("/taobao")
    public  void taobao(HttpServletRequest request,HttpServletResponse response){

        try {
            String toke=this.readRequestString(request);

            logger.error("获取的token={}",toke);
        } catch (Exception e) {
            logger.error("金诺出票通知处理出错",e);
        }
    }

    protected String readRequestString(HttpServletRequest request) throws IOException {
        request.setCharacterEncoding("utf-8");
        BufferedReader reader = request.getReader();
        StringBuilder buffer = new StringBuilder();
        int n;
        while ((n = reader.read()) != -1) {
            buffer.append((char) n);
        }
        reader.close();
        return buffer.toString();
    }

}
