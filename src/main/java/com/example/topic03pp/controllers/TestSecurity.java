package com.example.topic03pp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestSecurity {


    @GetMapping("/admin")
    public String admin() {
        return "test-security/admin";
    }

    @GetMapping("/user")
    public String user() {
        return "test-security/user";
    }

    @GetMapping("/dba")
    public String dba() {
        return "test-security/dba";
    }
}
