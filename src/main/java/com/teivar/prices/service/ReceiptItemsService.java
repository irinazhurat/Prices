package com.teivar.prices.service;

import com.teivar.prices.entity.ReceiptItems;
import java.util.List;

/**
 * Created by Teivar on 18.12.2014.
 */
public interface ReceiptItemsService {

    ReceiptItems addReceiptItems(ReceiptItems receiptItems);
    void delete(long id);
//    ReceiptItems getByDateAndShops(Shops shops, Date date);
    ReceiptItems editReceiptItems(ReceiptItems receiptItems);
    List<ReceiptItems> getAll();

}