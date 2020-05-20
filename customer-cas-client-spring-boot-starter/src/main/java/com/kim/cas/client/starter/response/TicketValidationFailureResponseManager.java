package com.kim.cas.client.starter.response;

import org.jasig.cas.client.validation.TicketValidationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author huangjie
 * @description   ticket认证失败响应管理器
 * @date 2020/5/20
 */
public interface TicketValidationFailureResponseManager {


    /**
     * ticket认证失败响应
     * @param request   请求
     * @param response   响应
     * @param ticketValidationException
     * */
    void ticketValidationExceptionResponse(HttpServletRequest request, HttpServletResponse response, TicketValidationException ticketValidationException);

}
