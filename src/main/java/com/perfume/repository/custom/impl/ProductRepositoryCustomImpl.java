package com.perfume.repository.custom.impl;

import com.perfume.entity.Product;
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

public class ProductRepositoryCustomImpl implements ProductRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<Product> filter(MultiValueMap<String, String> queryParams, Pageable pageable) {
        String sql = "select T from TeeDesign T";
        Map<String, Object> values = new HashMap<>();
        sql += createWhereQuery(queryParams, values);
        sql += createOrderQuery(queryParams);
        Query query = entityManager.createQuery(sql, Product.class);
        values.forEach(query::setParameter);
        query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());
        List<Product> list = query.getResultList();
        Long count = count(queryParams);
        return new PageImpl<Product>(list, pageable, count);
    }

    public Long count(MultiValueMap<String, String> queryParams) {
        String sql = "select count(T) FROM Product T";
        Map<String, Object> values = new HashMap<>();
        sql += createWhereQuery(queryParams, values);
        Query query = entityManager.createQuery(sql, Long.class);
        values.forEach(query::setParameter);
        return (Long) query.getSingleResult();
    }

    private String createOrderQuery(MultiValueMap<String, String> queryParams) {
        String sql = " order by ";
        if (queryParams.containsKey("sort")) {
            List<String> orderByList = queryParams.get("sort");
            for (String i : orderByList) {
                sql += "P." + i.replace(",", " ") + ", ";
            }
            sql = sql.substring(0, sql.lastIndexOf(","));
        } else {
            sql += " P.createdDate desc ";
        }
        return sql;
    }

    private String createWhereQuery(MultiValueMap<String, String> queryParams, Map<String, Object> values) {
        StringBuilder sql = new StringBuilder();

        if (queryParams.containsKey("gender") || queryParams.containsKey("typeCode")) {
            Integer gender = Integer.valueOf(queryParams.get("gender").get(0));
            if (gender > 0) {
                sql.append(" join ClothingTemplate CT on T.templateCode = CT.templateCode and CT.status <> 0 and CT.gender = :gender ");
                values.put("gender", gender);
            }
            if (!StringUtils.isBlank(queryParams.get("typeCode").get(0))) {
                sql.append(" join ClothingTemplate CT on T.templateCode = CT.templateCode and CT.status <> 0 and CT.productTypeCode = :typeCode ");
                values.put("typeCode", queryParams.get("typeCode").get(0));
            }
        }

        sql.append(" where T.status = 1 ");
//
//        if (queryParams.containsKey("hashtags")) {
//            StringBuilder filter = new StringBuilder();
//            filter.append(" (select T2.id from TeeDesign T2");
//            filter.append(" inner join TeeDesignHashtag TH on T2.id = TH.teeDesignId and (1 = 0");
//            String[] hashtags = queryParams.get("hashtags").get(0).split(",");
//            for (int i = 0; i < hashtags.length; i++) {
//                String hashtag = "hashtag" + i;
//                filter.append(" or lower(TH.keyword) like lower(:").append(hashtag).append(")");
//                values.put(hashtag, "%" + hashtags[i] + "%");
//            }
//            filter.append(") group by T2.id having count(T2) >= :count_ )");
//
//            sql.append(" and T.id in").append(filter);
//            values.put("count_", (long) hashtags.length);
//        }


        return sql.toString();
    }
}
