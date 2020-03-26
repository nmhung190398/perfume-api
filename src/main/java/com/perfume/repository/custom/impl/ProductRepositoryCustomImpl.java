package com.perfume.repository.custom.impl;

import com.perfume.entity.Product;
import com.perfume.repository.custom.BaseRepository;
import com.perfume.repository.custom.ProductRepositoryCustom;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.MultiValueMap;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductRepositoryCustomImpl extends BaseRepositoryCustom<Product> implements ProductRepositoryCustom {

    public ProductRepositoryCustomImpl() {
        super("P");
    }
}
