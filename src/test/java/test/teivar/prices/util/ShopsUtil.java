package test.teivar.prices.util;

/**
 * Created by Zalesskiy_K on 17.12.2014.
 */
import com.teivar.prices.entity.Shops;

public class ShopsUtil {

    public static Shops createShops() {
        Shops shops = new Shops();
        shops.setName("Spar");
        shops.setDesc("test");
        return shops;
    }

}