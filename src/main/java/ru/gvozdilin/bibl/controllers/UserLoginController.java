package ru.gvozdilin.bibl.controllers;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@org.springframework.stereotype.Controller
@RequestMapping("/login")
public class UserLoginController {


    @RequestMapping(method = RequestMethod.GET)
    public String getLoginPage(Model model){

        return "login";
    }
}
