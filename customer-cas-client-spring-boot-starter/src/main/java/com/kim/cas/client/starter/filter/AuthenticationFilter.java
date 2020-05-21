package com.kim.cas.client.starter.filter;

import com.kim.cas.client.starter.config.CasClientConfigurationProperties;
import com.kim.cas.client.starter.config.CasClientConfigurer;
import com.kim.cas.client.starter.entity.PreCasAuthenticationRequest;
import com.kim.cas.client.starter.entity.Result;
import com.kim.cas.client.starter.manager.AuthenticationManager;
import com.kim.cas.client.starter.response.AuthenticationFailureResponseManager;
import net.unicon.cas.client.configuration.EnableCasClient;
import org.jasig.cas.client.Protocol;
import org.jasig.cas.client.authentication.Saml11AuthenticationFilter;
import org.jasig.cas.client.util.CommonUtils;
import org.jasig.cas.client.validation.Assertion;
import org.jasig.cas.client.validation.Cas20ProxyReceivingTicketValidationFilter;
import org.jasig.cas.client.validation.Cas30ProxyReceivingTicketValidationFilter;
import org.jasig.cas.client.validation.Saml11TicketValidationFilter;
import org.springframework.boot.autoconfigure.security.servlet.AntPathRequestMatcherProvider;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author huangjie
 * @description    登录认证过滤器
 * @date 2020/5/20
 */
public class AuthenticationFilter extends AbstractConfigurationFilter {



    public AuthenticationFilter(CasClientConfigurer casClientConfigurer,CasClientConfigurationProperties properties){
        super(casClientConfigurer,properties);
    }

    private boolean isRequestUrlExcluded(HttpServletRequest request){

        List<String> ignores = casClientConfigurer.configrerIgnoreUrls();
        if(ignores==null||ignores.size()==0){
            return false;
        }
        String requestURI = request.getRequestURI();
        if(CommonUtils.isNotBlank(requestURI)&&requestURI.contains("?")){
            requestURI=requestURI.substring(0,requestURI.indexOf("?"));
        }

        AntPathMatcher matcher=new AntPathMatcher();

        for (String ignore:ignores
             ) {
            if(matcher.match(ignore,requestURI)){
                return true;
            }
        }
        return false;

    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if(isRequestUrlExcluded(request)){
            filterChain.doFilter(request,response);
            return;
        }

        //登录认证
        AuthenticationManager authenticationManager = casClientConfigurer.authenticationAssertionManager();
        Assertion assertion = authenticationManager.authenticationValid(request, response);
        if(assertion==null){
            //认证失败时，由前端拼接url在浏览器重定向到cas服务端
            Protocol protocol=getProtocol();
            AuthenticationFailureResponseManager authenticationFailureResponseManager = casClientConfigurer.authenticationFailureResponseManager();
            Result<PreCasAuthenticationRequest> preCasAuthenticationRequestResult=new Result();
            preCasAuthenticationRequestResult.setPath(request.getRequestURI());
            preCasAuthenticationRequestResult.setTimestamp(System.currentTimeMillis());

            String casAuthPath=properties.getServerLoginUrl()+protocol.getServiceParameterName()+"=";
            String servicePath=properties.getClientHostUrl()+CasClientConfigurer.TICKET_VALIDITY+"?"+CasClientConfigurer.FRONT_ROUTE+"=";
            PreCasAuthenticationRequest preRequest=new PreCasAuthenticationRequest();
            preRequest.setCasAuthPath(casAuthPath);
            preRequest.setServicePath(servicePath);
            preCasAuthenticationRequestResult.setData(preRequest);
            preCasAuthenticationRequestResult.setMessage("返回data对象给前端，前端进行url拼接:" +
                    "前端操作步骤:" +
                    "1、service=data.servicePath + 前端路由url。"+
                    "2、encodedService=java.net.URLEncoder.encode(service, \"UTF-8\")。"+
                    "3、authPath=data.casAuthPath+encodedService。"+
                    "4、将authPath放在浏览器地址栏进行请求。"   );
            authenticationFailureResponseManager.casAuthenticationRedirect(request,response,preCasAuthenticationRequestResult);
            return;
        }
        //认证通过
        //通过认证管理器获取Assertion对象，从而获取用户信息。

        filterChain.doFilter(request,response);
        return;
    }





}
