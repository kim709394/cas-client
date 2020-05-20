package com.kim.cas.client.starter.config;

import net.unicon.cas.client.configuration.EnableCasClient;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.validation.constraints.NotNull;

import static net.unicon.cas.client.configuration.EnableCasClient.ValidationType.CAS3;

/**
 * @author huangjie
 * @description
 * @date 2020/5/20
 */
@ConfigurationProperties(prefix = "cas")
public class CasClientConfigurationProperties {

    @NotNull
    private String serverUrlPrefix;


    @NotNull
    private String serverLoginUrl;


    @NotNull
    private String clientHostUrl;

    private EnableCasClient.ValidationType validationType = CAS3;

    public String getServerUrlPrefix() {
        return serverUrlPrefix;
    }

    public void setServerUrlPrefix(String serverUrlPrefix) {
        this.serverUrlPrefix = serverUrlPrefix;
    }

    public String getServerLoginUrl() {
        return serverLoginUrl;
    }

    public void setServerLoginUrl(String serverLoginUrl) {
        this.serverLoginUrl = serverLoginUrl;
    }

    public String getClientHostUrl() {
        return clientHostUrl;
    }

    public void setClientHostUrl(String clientHostUrl) {
        this.clientHostUrl = clientHostUrl;
    }

    public EnableCasClient.ValidationType getValidationType() {
        return validationType;
    }

    public void setValidationType(EnableCasClient.ValidationType validationType) {
        this.validationType = validationType;
    }
}
