package com.kim.cas.client.starter.entity;

/**
 * @author huangjie
 * @description    预请求cas服务端认证路径对象
 * @date 2020/5/21
 */
public class PreCasAuthenticationRequest {


    private String casAuthPath;
    private String servicePath;


    public String getCasAuthPath() {
        return casAuthPath;
    }

    public void setCasAuthPath(String casAuthPath) {
        this.casAuthPath = casAuthPath;
    }

    public String getServicePath() {
        return servicePath;
    }

    public void setServicePath(String servicePath) {
        this.servicePath = servicePath;
    }
}
