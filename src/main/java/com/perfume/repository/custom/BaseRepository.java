package com.perfume.repository.custom;

import com.perfume.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.MultiValueMap;

import java.util.List;

public interface BaseRepository<E> {
    Page<E> filterPage(MultiValueMap<String, Object> queryParams, Pageable pageable);
    List<E> filter(MultiValueMap<String, Object> queryParams, Pageable pageable);
    Long count(MultiValueMap<String, Object> queryParams);
    List<E> find(E e);
    boolean Update(E e);
    boolean UpdateFull(E e);
//    List<E> getAll();
//    List<E> getOne();
}
