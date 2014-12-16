package com.teivar.prices.repository;

/**
 * Created by zalesskiy_k on 16.12.2014.
 */

import com.teivar.prices.entity.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GoodsReposytory extends JpaRepository<Goods, Long> {

    @Query("select b from Goods b where b.name = :name")
    Goods findByName(@Param("name") String name);
}