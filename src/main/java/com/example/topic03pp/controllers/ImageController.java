package com.example.topic03pp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ImageController {


    @GetMapping("/image/create")
    public String showCreateImageForm() {
        return "images/add-image";
    }



}
