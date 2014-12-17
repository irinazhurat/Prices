package com.teivar.prices.repository;

import com.teivar.prices.entity.Receipts;
import com.teivar.prices.entity.Shops;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

/**
 * Created by Zalesskiy_K on 17.12.2014.
 */
public interface ReceiptsRepository extends JpaRepository<Receipts, Long>{

    @Query("select r from Shops s inner join s.receipts r where s.shops = :shops and r.timeStamp = :TimeStamp")
    Receipts findByDateAndShops(@Param("shops") Shops shops, @Param("TimeStamp") Date date);
}
