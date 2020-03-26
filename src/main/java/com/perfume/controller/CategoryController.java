package com.perfume.controller;

import com.perfume.constant.StatusEnum;
import com.perfume.dto.CategoryDTO;
import com.perfume.dto.PagingDTO;
import com.perfume.dto.ResponsePaging;
import com.perfume.dto.mapper.CategoryMapper;
import com.perfume.entity.Category;
import com.perfume.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
        body.setStatus(StatusEnum.ACTIVE.getValue());

        categoryRepository.save(body);
        return ResponseEntity.ok(body);
    }

    @GetMapping("/all")
    public ResponseEntity<ResponsePaging<CategoryDTO>> getAll(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer limit
    ) {
        Pageable paging = PageRequest.of(page - 1, limit);
        Page<Category> pagedResult = categoryRepository.findAll(paging);
        List<CategoryDTO> categories = new ArrayList<>();

        if(pagedResult.hasContent()) {
            categories = pagedResult.getContent().stream().map(categoryMapper::toDTO).collect(Collectors.toList());
        }

        return ResponseEntity.ok(
                new ResponsePaging<>(categories, new PagingDTO(pagedResult.getTotalPages(), page, limit, paging.getOffset()))
        );
    }

    @GetMapping("/one/{id}")
    public ResponseEntity<CategoryDTO> getById(@PathVariable Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (!category.isPresent()) {
            throw new ValidationException("category does not exist");
        }
        return ResponseEntity.ok(categoryMapper.toDTO(category.get()));
    }

    @PostMapping("/filter")
    public ResponseEntity<List<CategoryDTO>> get(@RequestBody Category body) {
        body.setStatus(StatusEnum.ACTIVE.getValue());
        List<Category> categories = categoryRepository.find(body);

        return ResponseEntity.ok(
                categories.stream().map(x-> categoryMapper.toDTO(x)).collect(Collectors.toList())
        );
    }

    @PostMapping("/update")
    public ResponseEntity<String> update(@RequestBody Category body) {
        body.setStatus(null);
        categoryRepository.updateE(body);
        return ResponseEntity.ok("Update Success");
    }

    @PostMapping("/delete")
    public ResponseEntity<String> delete(@RequestBody Category body) {
        body.setStatus(StatusEnum.DELETED.getValue());
        categoryRepository.updateE(body);
        return ResponseEntity.ok("Delete Success");
    }
}
