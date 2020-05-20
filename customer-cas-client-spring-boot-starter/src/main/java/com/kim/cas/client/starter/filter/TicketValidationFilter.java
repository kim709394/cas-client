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

/**
 * @author huangjie
 * @description    ticket认证过滤器
 * @date 2020/5/20
 */
public class TicketValidationFilter implements Filter {


    private CasClientConfigurer casClientConfigurer;
    private CasClientConfigurationProperties properties;

    public TicketValidationFilter(CasClientConfigurer casClientConfigurer,CasClientConfigurationProperties properties){
        this.casClientConfigurer=casClientConfigurer;
        this.properties=properties;
    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        //验证ticket
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        EnableCasClient.ValidationType validationType = properties.getValidationType();
        Protocol protocol=validationType != EnableCasClient.ValidationType.CAS && validationType != EnableCasClient.ValidationType.CAS3 ?  Protocol.SAML11: Protocol.CAS2;

        String ticket = CommonUtils.safeGetParameter(request, protocol.getArtifactParameterName());
        if(CommonUtils.isBlank(ticket)){
            filterChain.doFilter(request,response);
            return;
        }

        String service=properties.getClientHostUrl()+request.getRequestURI()+"?frontRoute="+request.getParameter("frontRoute");
        TicketValidator ticketValidator=null;
        switch(properties.getValidationType()) {
            case CAS:
                ticketValidator = new Cas20ServiceTicketValidator(properties.getServerUrlPrefix());
                break;
            case CAS3:
                ticketValidator = new Cas30ServiceTicketValidator(properties.getServerUrlPrefix());
                break;
            case SAML:
                ticketValidator = new Saml11TicketValidator(properties.getServerUrlPrefix());
                break;
            default:
                throw new IllegalStateException("Unknown CAS validation type");
        }
        Assertion assertion=null;
        try {
            assertion = ticketValidator.validate(ticket, service);
        } catch (TicketValidationException e) {
            //ticket认证失败
            TicketValidationFailureResponseManager ticketValidationFailureResponseManager = casClientConfigurer.ticketValidationFailureResponseManager();
            ticketValidationFailureResponseManager.ticketValidationExceptionResponse(request,response,e);
            return;
        }
        //ticket认证成功
        TicketValidationManager ticketValidationManager = casClientConfigurer.ticketValidationAssertionManager();
        ticketValidationManager.ticketValidationSuccess(request,response,assertion);
        filterChain.doFilter(request,response);
        return;
    }



}
