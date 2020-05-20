package com.kim.cas.client.starter.manager;

import org.jasig.cas.client.validation.Assertion;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author huangjie
 * @description ticket认证管理器
 * @date 2020/5/20
 */
public interface TicketValidationManager {



    /**
     * ticket认证通过后的Assertion管理
     * @param request   请求
     * @param response   响应
     * @param assertion   认证信息对象
     * */
    void ticketValidationSuccess(HttpServletRequest request, HttpServletResponse response, Assertion assertion);

}
