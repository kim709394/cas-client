package com.kim.cas.client.starter.filter;

import com.kim.cas.client.starter.config.CasClientConfigurationProperties;
import com.kim.cas.client.starter.config.CasClientConfigurer;
import com.kim.cas.client.starter.manager.TicketValidationManager;
import com.kim.cas.client.starter.response.TicketValidationFailureResponseManager;
import net.unicon.cas.client.configuration.EnableCasClient;
import org.jasig.cas.client.Protocol;
import org.jasig.cas.client.util.CommonUtils;
import org.jasig.cas.client.validation.*;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;

/**
 * @author huangjie
 * @description    ticket认证过滤器
 * @date 2020/5/20
 */
public class TicketValidationFilter extends AbstractConfigurationFilter {



    public TicketValidationFilter(CasClientConfigurer casClientConfigurer,CasClientConfigurationProperties properties){
        super(casClientConfigurer,properties);
    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        //验证ticket
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        Protocol protocol=getProtocol();
        String ticket = CommonUtils.safeGetParameter(request, protocol.getArtifactParameterName());
        TicketValidationFailureResponseManager ticketValidationFailureResponseManager = casClientConfigurer.ticketValidationFailureResponseManager();
        if(CommonUtils.isBlank(ticket)){
            ticketValidationFailureResponseManager.ticketValidationExceptionResponse(request,response,new TicketValidationException("ticket is not found"));
            return;
        }

        String service=properties.getClientHostUrl()+request.getRequestURI()+"?"+CasClientConfigurer.FRONT_ROUTE+"="+request.getParameter(CasClientConfigurer.FRONT_ROUTE);

        TicketValidator ticketValidator=getTicketValidator();

        Assertion assertion=null;
        try {
            assertion = ticketValidator.validate(ticket, service);
        } catch (TicketValidationException e) {
            //ticket认证失败
            ticketValidationFailureResponseManager.ticketValidationExceptionResponse(request,response,e);
            return;
        }
        //ticket认证成功
        TicketValidationManager ticketValidationManager = casClientConfigurer.ticketValidationAssertionManager();
        ticketValidationManager.ticketValidationSuccess(request,response,assertion);
        String frontRoute = request.getParameter(CasClientConfigurer.FRONT_ROUTE);
        if(CommonUtils.isNotBlank(frontRoute)){
            String decode = URLDecoder.decode(frontRoute, "UTF-8");
            response.sendRedirect(decode);
            return;
        }
        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        return;
    }



}
