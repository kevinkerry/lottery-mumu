package com.lottery.ticket.vender.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by fengqinyun on 15/6/12.
 * 通知类处理
 */

public interface IVenderNoticeProcess {

    public  void process(HttpServletRequest request, HttpServletResponse response) throws Exception;

}
