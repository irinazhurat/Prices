package com.teivar.prices.repository;

import com.teivar.prices.entity.ReceiptItems;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Teivar on 18.12.2014.
 */
public interface ReceiptItemsRepository extends JpaRepository<ReceiptItems, Long> {

}