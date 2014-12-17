package test.teivar.prices.util;

/**
 * Created by Zalesskiy_K on 17.12.2014.
 */

import com.teivar.prices.entity.Receipts;
import com.teivar.prices.entity.Shops;

import java.util.GregorianCalendar;

public class ReceiptsUtil {

    public static Receipts createReceipts(Shops shops) {
        Receipts receipts = new Receipts();
        GregorianCalendar calen = new GregorianCalendar(2008, 3, 27, 14, 30, 59);
        receipts.setTimeStamp(calen.getTime());
        receipts.setSumm(15354.00);
        receipts.setShops(shops);
        return receipts;
    }

}