package test.teivar.prices.service;

/**
 * Created by Teivar on 18.12.2014.
 */
import com.teivar.prices.entity.Goods;
import com.teivar.prices.entity.ReceiptItems;
import com.teivar.prices.entity.Receipts;
import com.teivar.prices.entity.Shops;
import com.teivar.prices.service.GoodsService;
import com.teivar.prices.service.ReceiptItemsService;
import com.teivar.prices.service.ReceiptsService;
import com.teivar.prices.service.ShopsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import test.teivar.prices.config.TestDataBaseConfig;
import test.teivar.prices.util.GoodsUtil;
import test.teivar.prices.util.ReceiptItemsUtil;
import test.teivar.prices.util.ReceiptsUtil;
import test.teivar.prices.util.ShopsUtil;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestDataBaseConfig.class)
@WebAppConfiguration
public class ReceiptItemsServiceTest {

    @Resource
    private EntityManagerFactory emf;
    protected EntityManager em;

    @Resource
    private ReceiptItemsService receiptItemsService;
    @Resource
    private ReceiptsService receiptsService;
    @Resource
    private ShopsService shopsService;
    @Resource
    private GoodsService goodsService;

    @Before
    public void setUp() throws Exception {
        em = emf.createEntityManager();
    }

    @Test
    public void testSaveReceiptItems() throws Exception {

        Shops shops = ShopsUtil.createShops();
        shops = shopsService.addShops(shops);

        Receipts receipts = ReceiptsUtil.createReceipts(shops);
        receipts = receiptsService.addReceipts(receipts);

        Goods goods = GoodsUtil.createGoods();
        goods = goodsService.addGoods(goods);

        ReceiptItems receiptItems = ReceiptItemsUtil.createReceipts(receipts, goods);
        receiptItems = receiptItemsService.addReceiptItems(receiptItems);

    }




}