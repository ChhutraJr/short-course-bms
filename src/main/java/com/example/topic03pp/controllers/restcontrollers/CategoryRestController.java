package com.example.topic03pp.controllers.restcontrollers;

import com.example.topic03pp.models.Category;
import com.example.topic03pp.services.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryRestController {

    private CategoryService categoryService;

    public CategoryRestController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/all")
    public Map<String, Object> getCategory() {

        Map<String, Object> response = new HashMap<>();

        List<Category> categories = this.categoryService.getAll();

        response.put("categories", categories);
        response.put("status", true);
        response.put("record_count", categories.size());

        return response;
    }



    @PostMapping("")
    public Category create(@RequestBody Category category) {

        this.categoryService.create(category);
        System.out.println(category);

        return category;
    }



    @PostMapping("/multi")
    public boolean creates(@RequestBody List<Category> categories) {
        this.categoryService.creates(categories);

        System.out.println(categories);
        return true;
    }



}
