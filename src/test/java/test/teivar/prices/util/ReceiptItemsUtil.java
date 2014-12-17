package test.teivar.prices.util;

import com.teivar.prices.entity.Goods;
import com.teivar.prices.entity.ReceiptItems;
import com.teivar.prices.entity.Receipts;

/**
 * Created by Teivar on 18.12.2014.
 */
public class ReceiptItemsUtil {

    public static ReceiptItems createReceipts(Receipts receipts, Goods goods) {
        ReceiptItems receiptItems = new ReceiptItems();
        receiptItems.setGoods(goods);
        receiptItems.setReceipts(receipts);
        receiptItems.setPrice(23.23);
        receiptItems.setQuan(23.23);
        return receiptItems;
    }

}
