package com.teivar.prices.service.implementation;

import com.teivar.prices.entity.ReceiptItems;
import com.teivar.prices.entity.Receipts;
import com.teivar.prices.repository.ReceiptItemsRepository;
import com.teivar.prices.service.ReceiptItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Teivar on 18.12.2014.
 */
@Service
public class ReceiptItemsServiceImpl implements ReceiptItemsService {

    @Autowired
    private ReceiptItemsRepository receiptItemsRepository;

    @Override
    public ReceiptItems addReceiptItems(ReceiptItems receipts) {
        ReceiptItems savedReceiptItems = receiptItemsRepository.saveAndFlush(receipts);

        return savedReceiptItems;
    }

    @Override
    public void delete(long id) {
        receiptItemsRepository.delete(id);
    }

    @Override
    public List<ReceiptItems> getByReceipts(Receipts receipts){
        return receiptItemsRepository.findByReceipts(receipts);
    }


    @Override
    public ReceiptItems editReceiptItems(ReceiptItems receiptItems) {
        return receiptItemsRepository.saveAndFlush(receiptItems);
    }

    @Override
    public List<ReceiptItems> getAll() {
        return receiptItemsRepository.findAll();
    }
}