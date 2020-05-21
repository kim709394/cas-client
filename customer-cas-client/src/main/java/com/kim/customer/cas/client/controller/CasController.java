package com.kim.customer.cas.client.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huangjie
 * @description
 * @date 2020/5/21
 */
@RestController
@RequestMapping("/cas")
public class CasController {


    @GetMapping("/index1")
    public String index1(){
        return "需要cas认证的接口1";
    }

    @GetMapping("/index2")
    public String index2(){
        return "需要cas认证的接口2";
    }


    @GetMapping("/ignore1")
    public String ignore1(){
        return "忽略cas认证的接口1";
    }

    /**模拟前端路由*/
    @GetMapping("/frontRoute")
    public String frontRoute(){
        return "模拟前端路由页面";
    }

}
