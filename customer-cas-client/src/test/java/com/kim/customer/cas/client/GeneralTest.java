package com.kim.customer.cas.client;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author huangjie
 * @description
 * @date 2020/5/21
 */

public class GeneralTest {


    /**
     * 模拟前端对认证失败返回的认证信息进行处理
     * */
    @Test
    public void testPathEncode() throws UnsupportedEncodingException {

        String frontRoute="http://localhost:9004/cas/frontRoute";   //模拟前端路由地址

        String servicePath="http://localhost:9004/ticketValidity?frontRoute=";
        String service=servicePath+frontRoute;
        String casAuthPath="http://localhost:8443/cas/login?service=";
        String authPath=casAuthPath+ URLEncoder.encode(service,"UTF-8");
        System.out.println(authPath);

    }





}
