package test.teivar.prices.service;

/**
 * Created by Zalesskiy_K on 17.12.2014.
 */
import com.teivar.prices.entity.Receipts;
import com.teivar.prices.entity.Shops;
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
import test.teivar.prices.util.ReceiptsUtil;
import test.teivar.prices.util.ShopsUtil;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.GregorianCalendar;
import static org.junit.Assert.assertEquals;

@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestDataBaseConfig.class)
@WebAppConfiguration
public class ReceiptsServiceTest {

    @Resource
    private EntityManagerFactory emf;
    protected EntityManager em;

    @Resource
    private ReceiptsService receiptsService;
    @Resource
    private ShopsService shopsService;

    @Before
    public void setUp() throws Exception {
        em = emf.createEntityManager();
    }

    @Test
    public void testSaveReceipts() throws Exception {

        Shops shops = ShopsUtil.createShops();
        shops = shopsService.addShops(shops);

        Receipts receipts = ReceiptsUtil.createReceipts(shops);
        receipts = receiptsService.addReceipts(receipts);
        GregorianCalendar calen = new GregorianCalendar(2008, 3, 27, 14, 30, 59);
        Receipts receiptsNew = receiptsService.getByDateAndShops(shops,calen.getTime());

        assertEquals(receipts.getId(), receiptsNew.getId());
    }




}