package com.SpringMVC.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @PostMapping("/login")
    public String login(){
        System.out.println("UserController.login");
        return "login success!!";
    }

    @RequestMapping(value = {"/register"},method = {RequestMethod.POST,RequestMethod.GET})
    public String register(){
        System.out.println("UserController.register");
        return "register success!!";
    }

    @GetMapping("/{id}/{name}")
    public String getUser(@PathVariable Long id,
                          @PathVariable("name") String uname) {
        System.out.println("id = " + id + ", uname = " + uname);
        return "user_detail";
    }
}