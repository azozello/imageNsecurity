package com.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping(value = "/")
    public String index(){
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
