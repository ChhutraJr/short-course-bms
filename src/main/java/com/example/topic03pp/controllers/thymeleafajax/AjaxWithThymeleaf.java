package com.example.topic03pp.controllers.thymeleafajax;


import com.example.topic03pp.services.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/thymeleaf-ajax")
@Controller
public class AjaxWithThymeleaf {


    private BookService bookService;

    public AjaxWithThymeleaf(BookService bookService) {
        this.bookService = bookService;
    }


    @GetMapping("")
    public String thymeleafAjax() {
        return "thymeleaf-ajax/thymeleaf-ajax";
    }



}
