package com.teivar.prices.service.implementation;

/**
 * Created by zalesskiy_k on 16.12.2014.
 */

import com.teivar.prices.entity.Goods;
import com.teivar.prices.repository.GoodsReposytory;
import com.teivar.prices.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsReposytory goodsReposytory;

    @Override
    public Goods addGoods(Goods goods) {
        Goods savedGoods = goodsReposytory.saveAndFlush(goods);

        return savedGoods;
    }

    @Override
    public void delete(long id) {
        goodsReposytory.delete(id);
    }

    @Override
    public Goods getByName(String name) {
        return goodsReposytory.findByName(name);
    }

    @Override
    public Goods editGoods(Goods goods) {
        return goodsReposytory.saveAndFlush(goods);
    }

    @Override
    public List<Goods> getAll() {
        return goodsReposytory.findAll();
    }
}
