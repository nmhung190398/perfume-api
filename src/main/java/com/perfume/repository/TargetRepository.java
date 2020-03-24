package com.perfume.repository;

import com.perfume.entity.Target;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TargetRepository extends JpaRepository<Target, Long> {
    Target findByName(String name);
}
