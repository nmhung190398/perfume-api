package com.perfume.repository.custom;

import com.perfume.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.MultiValueMap;

@Repository
public interface ProductRepositoryCustom {
    Page<Product> filter(MultiValueMap<String, String> queryParams, Pageable pageable);
}
