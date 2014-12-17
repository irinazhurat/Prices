package com.teivar.prices.service.implementation;

/**
 * Created by Zalesskiy_K on 17.12.2014.
 */
import com.teivar.prices.entity.Receipts;
import com.teivar.prices.repository.ReceiptsRepository;
import com.teivar.prices.service.ReceiptsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReceiptsServiceImpl implements ReceiptsService {

    @Autowired
    private ReceiptsRepository receiptsRepository;

    @Override
    public Receipts addReceipts(Receipts receipts) {
        Receipts savedReceipts = receiptsRepository.saveAndFlush(receipts);

        return savedReceipts;
    }

    @Override
    public void delete(long id) {
        receiptsRepository.delete(id);
    }

    @Override
    public Receipts getByDateAndShops(Date date, long shopsId) {
        return null;

        // /return receiptsRepository.findByDateAndShops(shopsId);
    }

    @Override
    public Receipts editReceipts(Receipts receipts) {
        return receiptsRepository.saveAndFlush(receipts);
    }

    @Override
    public List<Receipts> getAll() {
        return receiptsRepository.findAll();
    }
}