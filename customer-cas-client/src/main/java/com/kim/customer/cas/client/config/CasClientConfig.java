package com.kim.customer.cas.client.config;

import com.kim.cas.client.starter.config.CasClientConfigurerAdapter;
import org.springframework.boot.SpringBootConfiguration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huangjie
 * @description
 * @date 2020/5/21
 */
//@SpringBootConfiguration
public class CasClientConfig extends CasClientConfigurerAdapter {


    @Override
    public List<String> configurerAuthenticationUrls() {
        List<String> urls=new ArrayList<>();
        urls.add("/cas/index1");
        urls.add("/cas/index2");
        return urls;
    }

    @Override
    public List<String> configrerIgnoreUrls() {
        List<String> urls=new ArrayList<>();
        urls.add("/cas/index1");
        return urls;
    }



}
