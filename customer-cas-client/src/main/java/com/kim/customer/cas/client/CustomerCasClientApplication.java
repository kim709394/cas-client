package com.kim.customer.cas.client;

import com.kim.cas.client.starter.annotation.EnableCustomerCasClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author huangjie
 * @description
 * @date 2020/5/21
 */

@SpringBootApplication
@EnableCustomerCasClient
public class CustomerCasClientApplication {


    public static void main(String[] args) {
        SpringApplication.run(CustomerCasClientApplication.class);
    }
}
