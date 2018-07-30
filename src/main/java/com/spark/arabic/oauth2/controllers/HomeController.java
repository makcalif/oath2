package com.spark.arabic.oauth2.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping (value = "/")
    public String home () {
        return "home  xis here";
    }

    @GetMapping (value = "/private")
    public String getPrivate () {
        return "private is here";
    }
}
