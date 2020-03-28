package com.perfume.repository.custom.impl;

import com.nmhung.sql.model.ResponseBaseDAO;
import com.perfume.constant.StatusEnum;
import com.perfume.dto.search.ProductSearch;
import com.perfume.entity.Product;
import com.perfume.entity.User;
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

    private String asCategory = "Category";

    @Override
    public Page<Product> getAll(Pageable pageable) {
        ProductSearch productSearch = new ProductSearch();
        productSearch.setStatus(StatusEnum.ACTIVE.getValue());
        return findPage(productSearch, pageable);
    }

//    @Override
//    protected Query findBase(Object e) {
//        ProductSearch productSearch = (ProductSearch) e;
//        Map<String, Object> map = super.converEntityToMapQuery(productSearch);
//        if (productSearch.getMaxPrice() != null) {
//            map.put("maxPrice", productSearch.getMaxPrice());
//        }
//        if (productSearch.getMaxPrice() != null) {
//            map.put("minPrice", productSearch.getMinPrice());
//        }
//        ResponseBaseDAO responseBaseDAO = super.createQuery(map);
//        Query query = entityManager.createQuery(responseBaseDAO.getSql(), type);
//        responseBaseDAO.getValues().forEach(query::setParameter);
//        return query;
//    }


    @Override
    public String createJoinQuery(Map<String, Object> queryParams, Map<String, Object> values) {
        String sql = super.createJoinQuery(queryParams, values);
        sql += " JOIN P.category as " + asCategory + " ";
        return sql;
    }

    @Override
    public Map<String, Object> converEntityToMapQuery(Object e) {
        ProductSearch productSearch = (ProductSearch) e;
        Map<String, Object> map = super.converEntityToMapQuery(productSearch);
        if (productSearch.getMaxPrice() != null) {
            map.put("maxPrice", productSearch.getMaxPrice());
        }
        if (productSearch.getMaxPrice() != null) {
            map.put("minPrice", productSearch.getMinPrice());
        }
        if (productSearch.getHighlights() != null) {
            map.put("highlights", productSearch.getHighlights());
        }
        if (productSearch.getCategoryCode() != null) {
            map.put("categoryCode", productSearch.getCategoryCode());
        }
        if(productSearch.getVersionId() != null){
            map.put("versionId",productSearch.getVersionId());
        }
        return map;
    }


    @Override
    public String createWhereQuery(Map<String, Object> queryParams, Map<String, Object> values) {
        String sql = super.createWhereQuery(queryParams, values);

        if (queryParams.get("maxPrice") != null) {
            sql += " AND " + asName + ".id IN ( SELECT distinct V.product.id FROM Version V WHERE V.price <= :maxPrice ) ";
            values.put("maxPrice", queryParams.get("maxPrice"));
        }
        if (queryParams.get("minPrice") != null) {
            sql += " AND " + asName + ".id IN ( SELECT distinct V.product.id FROM Version V WHERE V.price >= :minPrice ) ";
            values.put("minPrice", queryParams.get("minPrice"));
        }

        if (queryParams.get("highlights") != null) {
            String[] highlights = (String[]) queryParams.get("highlights");
            for (int i = 0; i < highlights.length; ++i) {
                String tmp = "highlight" + i;
                sql += " AND highlight LIKE :" + tmp + " ";
                values.put(tmp, "%" + highlights[i].toUpperCase() + "%");
            }
        }
        if (queryParams.get("categoryCode") != null) {
            sql += " AND " + asCategory + ".code = :categoryCode ";
            values.put("categoryCode", queryParams.get("categoryCode"));
        }

        if (queryParams.get("versionId") != null) {
            sql += " AND " + asName + ".id IN ( SELECT distinct V.product.id FROM Version V WHERE V.id = :versionId ) ";
            values.put("versionId", queryParams.get("versionId"));
        }

        return sql;
    }
}
