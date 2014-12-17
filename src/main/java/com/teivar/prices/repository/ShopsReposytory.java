package com.teivar.prices.repository;

/**
 * Created by Zalesskiy_K on 17.12.2014.
 */
import com.teivar.prices.entity.Shops;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ShopsReposytory extends JpaRepository<Shops, Long> {

    @Query("select b from Shops b where b.name = :name")
    Shops findByName(@Param("name") String name);
}