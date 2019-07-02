package com.example.topic03pp.controllers;

import com.example.topic03pp.models.Category;
import com.example.topic03pp.services.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {


    private CategoryService categoryService;


    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/all")
    public String showAllCategory(Model model) {
        List<Category> categories = this.categoryService.getAll();
        model.addAttribute("categories", categories);

        System.out.println(categories);
        return "category/all-category";

    }


    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("category", new Category());
        return "category/add-category";
    }


    @PostMapping("/create/submit")
    public String createSubmit(Category category) {

        Date created_at = new Date();


        System.out.println(category);

        boolean b = this.categoryService.create(category);



        return "redirect:/category/all";
    }
}
