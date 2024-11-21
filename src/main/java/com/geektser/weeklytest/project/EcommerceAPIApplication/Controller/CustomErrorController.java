package com.geektser.weeklytest.project.EcommerceAPIApplication.Controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;

public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError() {
        return "Custom Error Message";
    }
}
