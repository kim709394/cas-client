package com.kim.cas.client.starter.manager;

import org.jasig.cas.client.validation.Assertion;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author huangjie
 * @description   登录认证管理器
 * @date 2020/5/20
 */
public interface AuthenticationManager {


    /**
     * 登录认证管理  如果返回空则认证失败
     * @param request   请求
     * @param response   响应
     * @return assertion   认证信息对象
     * */
    Assertion authenticationValid(HttpServletRequest request, HttpServletResponse response);



}
