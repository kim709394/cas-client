package com.kim.cas.client1;

import net.unicon.cas.client.configuration.EnableCasClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author huangjie
 * @description   cas-client-demo
 * @date 2020/5/20
 */
@SpringBootApplication
@EnableCasClient
public class CasClient1Application {

    public static void main(String[] args) {
        SpringApplication.run(CasClient1Application.class);
    }
}
