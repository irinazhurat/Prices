package test.teivar.prices.util;

/**
 * Created by zalesskiy_k on 17.12.2014.
 */
import com.teivar.prices.entity.Goods;

public class GoodsUtil {

    public static Goods createGoods() {
        Goods goods = new Goods();
        goods.setName("New apple");
        goods.setBarcode("1234567890");
        return goods;
    }

}