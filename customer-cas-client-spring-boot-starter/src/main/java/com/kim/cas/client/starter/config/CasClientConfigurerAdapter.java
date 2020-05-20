package com.kim.cas.client.starter.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kim.cas.client.starter.entity.Result;
import com.kim.cas.client.starter.manager.AuthenticationManager;
import com.kim.cas.client.starter.manager.TicketValidationManager;
import com.kim.cas.client.starter.response.AuthenticationFailureResponseManager;
import com.kim.cas.client.starter.response.TicketValidationFailureResponseManager;
import org.jasig.cas.client.Protocol;
import org.jasig.cas.client.util.AbstractCasFilter;
import org.jasig.cas.client.validation.Assertion;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author huangjie
 * @description
 * @date 2020/5/21
 */
public abstract class CasClientConfigurerAdapter implements CasClientConfigurer {


    @Override
    public List<String> configurerAuthenticationUrls() {
        List<String> urls=new ArrayList<>();
        urls.add("/**");
        return urls;
    }

    @Override
    public List<String> configrerIgnoreUrls() {
        return null;
    }

    @Override
    public AuthenticationManager authenticationAssertionManager() {
        return ( request ,response)->{
            HttpSession session = request.getSession(false);
            return session != null ? (Assertion) session.getAttribute(AbstractCasFilter.CONST_CAS_ASSERTION) : null;
        };
    }

    @Override
    public TicketValidationManager ticketValidationAssertionManager() {
        return (request,response,assertion)->{
            request.getSession().setAttribute(AbstractCasFilter.CONST_CAS_ASSERTION, assertion);
        };
    }

    @Override
    public AuthenticationFailureResponseManager authenticationFailureResponseManager() {
        return (request,response,preCasAuthenticationRequest)->{
            PrintWriter writer = null;
            try {
                response.setContentType("application/json;charset=utf-8");
                response.setCharacterEncoding("UTF-8");
                writer = response.getWriter();
                writer.write(new ObjectMapper().writeValueAsString(preCasAuthenticationRequest));
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                writer.close();
            }


        };
    }

    @Override
    public TicketValidationFailureResponseManager ticketValidationFailureResponseManager() {
        return (request,response,ticketValidationException)->{
            PrintWriter writer = null;
            try {
                response.setContentType("application/json;charset=utf-8");
                response.setCharacterEncoding("UTF-8");
                writer = response.getWriter();
                Result<String> error=new Result<>();
                error.setTimestamp(System.currentTimeMillis());
                error.setData(ticketValidationException.getMessage());
                error.setMessage("ticket验证失败");
                error.setPath(request.getRequestURI());
                writer.write(new ObjectMapper().writeValueAsString(error));
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                writer.close();
            }
        };
    }
}
