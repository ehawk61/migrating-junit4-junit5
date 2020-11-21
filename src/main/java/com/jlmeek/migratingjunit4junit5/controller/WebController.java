package com.jlmeek.migratingjunit4junit5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WebController {

    @GetMapping("/")
    public @ResponseBody String greeting(){
        return "Hello JUnit Testing!"; 
    }
}
