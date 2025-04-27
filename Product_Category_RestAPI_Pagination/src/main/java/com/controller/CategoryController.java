package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.model.Category;
import com.service.CategoryRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/welcome")
    public String welcomeMessage() {
        return "Hi, welcome to the Category ";
    }

    @GetMapping()
    public List<Category> getAllCategories(@RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Category> categoryPage = categoryRepository.findAll(pageable);
        return categoryPage.getContent();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("id") int id) {
        Optional<Category> category = categoryRepository.findById(id);
        return category.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Category createCategory( @RequestBody Category category) {
        return categoryRepository.save(category);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable("id") int categoryId, @RequestBody Category updatedCategory) {
        return categoryRepository.findById(categoryId)
                .map(existingCategory -> {
                    existingCategory.setCategoryName(updatedCategory.getCategoryName());
                    Category savedCategory = categoryRepository.save(existingCategory);
                    return ResponseEntity.ok(savedCategory);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCategory(@PathVariable("id") int categoryId) {
        return categoryRepository.findById(categoryId)
                .map(existingCategory -> {
                    categoryRepository.delete(existingCategory);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
