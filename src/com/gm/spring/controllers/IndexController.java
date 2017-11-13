package com.gm.spring.controllers;


import com.gm.spring.services.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;

@Controller
public class IndexController {

    @Autowired
    IndexService indexService;


    @GetMapping("/")
    public String welcome(Model model) {
        model.addAttribute("message", indexService.welcomeMessage());
        return "index";
    }

}
