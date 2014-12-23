package com.teivar.prices.service;

import com.teivar.prices.entity.ReceiptItems;
import com.teivar.prices.entity.Receipts;

import java.util.List;

/**
 * Created by Teivar on 18.12.2014.
 */
public interface ReceiptItemsService {

    ReceiptItems addReceiptItems(ReceiptItems receiptItems);
    void delete(long id);
    ReceiptItems editReceiptItems(ReceiptItems receiptItems);
    List<ReceiptItems> getAll();
    List<ReceiptItems> getByReceipts(Receipts receipts);

}