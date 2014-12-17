package com.teivar.prices.service.implementation;

/**
 * Created by Zalesskiy_K on 17.12.2014.
 */
import com.teivar.prices.entity.Shops;
import com.teivar.prices.repository.ShopsReposytory;
import com.teivar.prices.service.ShopsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopsServiceImpl implements ShopsService {

    @Autowired
    private ShopsReposytory shopsReposytory;

    @Override
    public Shops addShops(Shops shops) {
        Shops savedShops = shopsReposytory.saveAndFlush(shops);

        return savedShops;
    }

    @Override
    public void delete(long id) {
        shopsReposytory.delete(id);
    }

    @Override
    public Shops getByName(String name) {
        return shopsReposytory.findByName(name);
    }

    @Override
    public Shops editShops(Shops shops) {
        return shopsReposytory.saveAndFlush(shops);
    }

    @Override
    public List<Shops> getAll() {
        return shopsReposytory.findAll();
    }
}
