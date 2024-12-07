package com.SpringMVC.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

// 使用@Controller标记而不是@Component
@Controller
public class HelloController {

    // handler就是controller内部的具体方法
    // 用来向handlerMapping中注册的方法注解
    @RequestMapping( "/hello")
    @ResponseBody // 代表向浏览器直接返回数据
    public String hello(){
        System.out.println("HelloController.hello");
        return "hello springmvc!!";
    }

}
