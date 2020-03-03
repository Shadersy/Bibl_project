package ru.gvozdilin.bibl.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.stereotype.Controller
@RequestMapping("/")
public class UserLoginController {


    @GetMapping("/login")
    public String getLoginPage(){

        return "login";
    }


}
