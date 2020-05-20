package com.kim.cas.client2.config;

import net.unicon.cas.client.configuration.CasClientConfigurerAdapter;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;

/**
 * @author huangjie
 * @description   配置忽略cas验证的url
 * @date 2020/5/20
 */
@SpringBootConfiguration
public class CasClientConfig extends CasClientConfigurerAdapter {

    @Override
    public void configureAuthenticationFilter(FilterRegistrationBean authenticationFilter) {
        authenticationFilter.getInitParameters().put("ignorePattern","/cas/ignore1|/cas/ignore2");
    }

}
