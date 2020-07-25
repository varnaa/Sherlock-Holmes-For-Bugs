package com.varnaa.abm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping({"/", "/main", ""})
    public String homePage() {
        return "index";
    }

    @GetMapping("/navbar")
    public String navBar() {
        return "navbar";
    }
}
