package com.kim.cas.client.starter.config;

import com.kim.cas.client.starter.manager.AuthenticationManager;
import com.kim.cas.client.starter.manager.TicketValidationManager;
import com.kim.cas.client.starter.response.AuthenticationFailureResponseManager;
import com.kim.cas.client.starter.response.TicketValidationFailureResponseManager;

import java.util.List;

/**
 * @author huangjie
 * @description
 * @date 2020/5/20
 */
public interface CasClientConfigurer {

    /**
     * cas要验证的接口url集合
     * */
    List<String> configurerAuthenticationUrls();

    /**
     * 忽略cas验证的接口url集合
     * */
    List<String> configrerIgnoreUrls();


    /**
     * 登录认证
     * @return AuthenticationManager
     * */
    AuthenticationManager authenticationAssertionManager();


    /**
     * ticket认证通过后的Assertion管理
     * @return TicketValidationManager
     * */
    TicketValidationManager ticketValidationAssertionManager();

    /**
     *  认证失败，进行重新认证请求返回管理
     * @return AuthenticationFailureResponseManager
     * */
    AuthenticationFailureResponseManager authenticationFailureResponseManager();

    /**
     * ticket认证失败响应管理
     * @return TicketValidationFailureResponseManager
     * */
    TicketValidationFailureResponseManager ticketValidationFailureResponseManager();



}
