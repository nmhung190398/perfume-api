package com.perfume.repository.custom.impl;

import com.nmhung.sql.BaseDAO;
import com.nmhung.sql.model.ResponseBaseDAO;
import com.perfume.entity.Product;
import com.perfume.repository.custom.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseRepositoryCustom<E> extends BaseDAO<E> implements BaseRepository<E> {
//    protected String nameTable = getType(0).getName();
//
//    private Class<E> getType(int index) {
//        Class<E> type = (Class<E>)
//                ((ParameterizedType) getClass()
//                        .getGenericSuperclass())
//                        .getActualTypeArguments()[index];
//        return type;
//    }
//
//    protected String asName = "T";


    public BaseRepositoryCustom(String asName) {
        super(asName);
        this.asName = asName;
    }

    private MultiValueMap<String, Object> toMultiValueMap(Map<String, List<Object>> map) {
        MultiValueMap<String, Object> valueMap = new LinkedMultiValueMap<>();
        map.forEach((key, value) -> {
            valueMap.addAll(key, map.get(key));
        });
        return valueMap;
    }

    @PersistenceContext
    protected EntityManager entityManager;

    @Override
    public Page<E> filterPage(MultiValueMap<String, Object> queryParams, Pageable pageable) {
        List<E> list = filter(queryParams, pageable);
        Long count = count(queryParams);
        return new PageImpl<E>(list, pageable, count);
    }

    @Override
    public List<E> filter(MultiValueMap<String, Object> queryParams, Pageable pageable) {
//        String sql = String.join(" ", "SELECT", asName, "FROM", nameTable, asName, " ");
//        Map<String, Object> values = new HashMap<>();
//        sql += createWhereQuery(queryParams, values);
//        sql += createOrderQuery(queryParams);
        ResponseBaseDAO responseBaseDAO = super.createQuery(toMultiValueMap((queryParams)));
        Query query = entityManager.createQuery(responseBaseDAO.getSql(), type);
        responseBaseDAO.getValues().forEach(query::setParameter);
        if (pageable != null) {
            query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
            query.setMaxResults(pageable.getPageSize());
        }
        List<E> list = query.getResultList();
        return list;
//        Long count = count(queryParams);
//        return new PageImpl<E>(list, pageable, count);
    }

    @Override
    public Long count(MultiValueMap<String, Object> queryParams) {
//        String sql = String.join(" ", "SELECT", "COUNT(", asName, ")", "FROM", nameTable, asName, " ");
//        Map<String, Object> values = new HashMap<>();
//        sql += createWhereQuery(queryParams, values);
        ResponseBaseDAO responseBaseDAO = super.createQueryCount(toMultiValueMap((queryParams)));
        Query query = entityManager.createQuery(responseBaseDAO.getSql(), Long.class);
        responseBaseDAO.getValues().forEach(query::setParameter);
        return (Long) query.getSingleResult();
    }

    @Override
    public List<E> find(E e) {
        ResponseBaseDAO responseBaseDAO = super.createQueryByEntity(e);
        Query query = entityManager.createQuery(responseBaseDAO.getSql(), type);
        responseBaseDAO.getValues().forEach(query::setParameter);
        List<E> list = query.getResultList();
        return list;
    }

    @Override
    public boolean Update(E e) {
        ResponseBaseDAO responseBaseDAO = super.update(e);
        Query query = this.entityManager.createQuery(responseBaseDAO.getSql());
        int rs = query.executeUpdate();
        return rs > 0;
    }

    @Override
    public boolean UpdateFull(E e) {
        ResponseBaseDAO responseBaseDAO = super.updateFull(e);
        Query query = this.entityManager.createQuery(responseBaseDAO.getSql());
        int rs = query.executeUpdate();
        return rs > 0;
    }


}
