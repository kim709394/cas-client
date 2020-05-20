package com.kim.cas.client2.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author huangjie
 * @description   演示接口
 * @date 2020/5/20
 */
@RestController
@RequestMapping("/cas")
public class CasController {

    @Value("${cas.server-url-prefix}")
    private String casServerUrlPrefix;


    @GetMapping("/index")
    public String index(){
        return "需要cas认证的接口";
    }

    @GetMapping("/ignore1")
    public String ignore1(){
        return "忽略cas认证的接口1";
    }

    @GetMapping("/logout")
    public void logout(HttpServletResponse response) throws IOException {

        response.sendRedirect(casServerUrlPrefix+"/logout");
    }
}
