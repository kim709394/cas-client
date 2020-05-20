package com.kim.cas.client.starter.config;

import java.util.List;

/**
 * @author huangjie
 * @description
 * @date 2020/5/20
 */
public interface CasClientConfigurer {

    /**
     * cas要验证的接口url集合
     * */
    List<String> configurerAuthenticationUrls();

    /**
     * 忽略cas验证的接口url集合
     * */
    List<String> configrerIgnoreUrls();




}
