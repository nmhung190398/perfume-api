package com.perfume.repository;

import com.perfume.entity.Category;
import com.perfume.repository.custom.CategoryRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long>, CategoryRepositoryCustom {
    boolean existsByCode(String code);
}
