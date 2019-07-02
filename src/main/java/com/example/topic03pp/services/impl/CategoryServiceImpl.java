package com.example.topic03pp.services.impl;

import com.example.topic03pp.models.Category;
import com.example.topic03pp.repositories.CategoryRepository;
import com.example.topic03pp.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAll() {
        return this.categoryRepository.getAll();
    }

    @Override
    public Integer count() {
        return this.categoryRepository.count();
    }

    @Override
    public boolean create(Category category) {
        return this.categoryRepository.create(category);
    }

    @Override
    public boolean creates(List<Category> categories) {
        return this.categoryRepository.creates(categories);
    }
}
