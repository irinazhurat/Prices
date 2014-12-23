package com.teivar.prices.repository;

import com.teivar.prices.entity.ReceiptItems;
import com.teivar.prices.entity.Receipts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Teivar on 18.12.2014.
 */
public interface ReceiptItemsRepository extends JpaRepository<ReceiptItems, Long> {

    @Query("select r from ReceiptItems r where r.receipts = :receipts")
    List<ReceiptItems> findByReceipts(@Param("receipts") Receipts receipts);

}