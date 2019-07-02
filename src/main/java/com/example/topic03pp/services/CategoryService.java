package com.example.topic03pp.services;

import com.example.topic03pp.models.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getAll();

    Integer count();

    boolean create(Category category);

    boolean creates(List<Category> categories);
}
