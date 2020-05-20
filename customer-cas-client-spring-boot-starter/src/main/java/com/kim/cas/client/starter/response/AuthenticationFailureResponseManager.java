package com.kim.cas.client.starter.response;

import com.kim.cas.client.starter.entity.PreCasAuthenticationRequest;
import com.kim.cas.client.starter.entity.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author huangjie
 * @description    认证失败响应管理器
 * @date 2020/5/20
 */
public interface AuthenticationFailureResponseManager {


    /**
     * 认证失败，进行重新认证请求返回
     * @param request   请求
     * @param response   响应
     * @param preCasAuthenticationRequest
     * */
    void casAuthenticationRedirect(HttpServletRequest request, HttpServletResponse response, Result<PreCasAuthenticationRequest> preCasAuthenticationRequest);



}
