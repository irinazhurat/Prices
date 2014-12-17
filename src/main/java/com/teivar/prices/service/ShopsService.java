package com.teivar.prices.service;

/**
 * Created by Zalesskiy_K on 17.12.2014.
 */

import com.teivar.prices.entity.Shops;
import java.util.List;

public interface ShopsService {

    Shops addShops(Shops shops);
    void delete(long id);
    Shops getByName(String name);
    Shops editShops(Shops shops);
    List<Shops> getAll();

}