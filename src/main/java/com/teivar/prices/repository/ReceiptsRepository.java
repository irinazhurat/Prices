package com.teivar.prices.repository;

import com.teivar.prices.entity.Receipts;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Zalesskiy_K on 17.12.2014.
 */
public interface ReceiptsRepository extends JpaRepository<Receipts, Long>{

    //@Query("select b from Receipts b where b.Shops_Id = (:Shops_Id)")
    //Receipts findByDateAndShops(@Param("Shops_Id") long id);
}
