package com.security.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping(value = "/")
    public String index(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(user.getUsername());
        return "index.html";
    }

    @RequestMapping(value = "/login")
    public String login(){
        return "pages/login.html";
    }

    @RequestMapping(value = "/upload")
    public String upLoad(){
        return "pages/load.html";
    }
}
