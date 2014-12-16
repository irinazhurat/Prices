package com.teivar.prices.service;

/**
 * Created by zalesskiy_k on 16.12.2014.
 */

import com.teivar.prices.entity.Goods;
import java.util.List;

public interface GoodsService {

    Goods addGoods(Goods goods);
    void delete(long id);
    Goods getByName(String name);
    Goods editGoods(Goods goods);
    List<Goods> getAll();

}