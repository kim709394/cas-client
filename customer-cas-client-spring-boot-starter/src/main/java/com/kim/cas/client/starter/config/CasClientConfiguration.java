package com.kim.cas.client.starter.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author huangjie
 * @description
 * @date 2020/5/20
 */
@SpringBootConfiguration
@EnableConfigurationProperties(CasClientConfigurationProperties.class)
public class CasClientConfiguration {

    @Autowired
    private CasClientConfigurationProperties casClientConfigurationProperties;

    @Autowired
    private List<CasClientConfigurer> casClientConfigurers;



}
