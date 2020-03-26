package com.perfume.controller;

import com.perfume.dto.CategoryDTO;
import com.perfume.dto.mapper.CategoryMapper;
import com.perfume.entity.Category;
import com.perfume.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ValidationException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CategoryMapper categoryMapper;

    @PostMapping("/add")
    public ResponseEntity<Category> create(@RequestBody Category body) {
        if (categoryRepository.existsByCode(body.getCode())) {
            throw new ValidationException("code already existed");
        }

        categoryRepository.save(body);
        return ResponseEntity.ok(body);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CategoryDTO>> getAll() {
        List<Category> categories = categoryRepository.findAll();
        return ResponseEntity.ok(
                categories.stream().map(x-> categoryMapper.toDTO(x)).collect(Collectors.toList())
        );
    }

    @PostMapping("/filter")
    public ResponseEntity<CategoryDTO> get(@RequestBody Category body) {
        List<Category> categories = categoryRepository.find(body);
        return ResponseEntity.ok(categoryMapper.toDTO(categories.get(0)));
    }
}
