package com.kim.cas.client2;

import net.unicon.cas.client.configuration.EnableCasClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author huangjie
 * @description
 * @date 2020/5/20
 */
@SpringBootApplication
@EnableCasClient
public class CasClient2Application {


    public static void main(String[] args) {
        SpringApplication.run(CasClient2Application.class);
    }

}
