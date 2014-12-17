package com.teivar.prices.service;

import com.teivar.prices.entity.Receipts;

import java.util.Date;
import java.util.List;


/**
 * Created by Zalesskiy_K on 17.12.2014.
 */
public interface ReceiptsService {

    Receipts addReceipts(Receipts receipts);
    void delete(long id);
    Receipts getByDateAndShops(Date date, long shopsId);
    Receipts editReceipts(Receipts receipts);
    List<Receipts> getAll();

}
