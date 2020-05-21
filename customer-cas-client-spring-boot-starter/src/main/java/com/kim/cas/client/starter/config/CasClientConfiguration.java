package com.kim.cas.client.starter.config;

import com.kim.cas.client.starter.filter.AuthenticationFilter;
import com.kim.cas.client.starter.filter.TicketValidationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;

/**
 * @author huangjie
 * @description
 * @date 2020/5/20
 */
@SpringBootConfiguration
@EnableConfigurationProperties(CasClientConfigurationProperties.class)
public class CasClientConfiguration {

    @Autowired
    private CasClientConfigurationProperties properties;

    @Autowired(required = false)
    private CasClientConfigurer casClientConfigurer;

    @PostConstruct
    public void initCasClientConfigurer(){
        if(casClientConfigurer==null){
            casClientConfigurer=new DefaultCasClientConfigurer();
        }
    }

    @Bean
    public FilterRegistrationBean ticketValidationFilter(){
        FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new TicketValidationFilter(casClientConfigurer,properties));
        filterRegistrationBean.addUrlPatterns(CasClientConfigurer.TICKET_VALIDITY);
        filterRegistrationBean.setOrder(10);
        return filterRegistrationBean;

    }

    @Bean
    public FilterRegistrationBean authenticationFilter() {
        FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new AuthenticationFilter(casClientConfigurer,properties));
        filterRegistrationBean.setUrlPatterns(casClientConfigurer.configurerAuthenticationUrls());

        filterRegistrationBean.setOrder(20);
        return filterRegistrationBean;
    }


}
