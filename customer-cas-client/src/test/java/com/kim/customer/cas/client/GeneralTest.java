package com.kim.customer.cas.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kim.cas.client.starter.entity.PreCasAuthenticationRequest;
import com.kim.cas.client.starter.entity.Result;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

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
    public void testPathEncode() throws UnsupportedEncodingException, JsonProcessingException {
        String authenticationFailureResponse="{\"timestamp\":1590055112154,\"message\":\"返回data对象给前端，前端进行url拼接:前端操作步骤:1、service=data.servicePath + 前端路由url。2、encodedService=java.net.URLEncoder.encode(service, \\\"UTF-8\\\")。3、authPath=data.casAuthPath+encodedService。4、将authPath放在浏览器地址栏进行请求。\",\"data\":{\"casAuthPath\":\"http://localhost:8443/cas/login?service=\",\"servicePath\":\"http://localhost:9004/ticketValidity?frontRoute=\"},\"path\":\"/cas/index1\"}";


        ObjectMapper mapper=new ObjectMapper();
        Result<PreCasAuthenticationRequest> result = mapper
                .readValue(authenticationFailureResponse, new TypeReference<Result<PreCasAuthenticationRequest>>() {});
        System.out.println(result.getMessage());
        String frontRoute="http://localhost:9004/cas/frontRoute";   //模拟前端路由地址
        PreCasAuthenticationRequest data = result.getData();
        String servicePath=data.getServicePath();
        String service=servicePath+frontRoute;
        String casAuthPath=data.getCasAuthPath();
        String authPath=casAuthPath+ URLEncoder.encode(service,"UTF-8");
        System.out.println("authPath:"+authPath);

    }





}
