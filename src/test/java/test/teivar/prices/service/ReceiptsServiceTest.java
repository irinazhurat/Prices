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
    private ShopsService shopsService;

    @Before
    public void setUp() throws Exception {
        em = emf.createEntityManager();
    }

    @Test
    public void testSaveGoods() throws Exception {
        Receipts receipts = ReceiptsUtil.createReceipts();

        receiptsService.addReceipts(receipts);
        GregorianCalendar calen = new GregorianCalendar(2008, 3, 27, 14, 30, 59);
        Shops newShops = shopsService.getByName("Spar");
        Receipts receiptsNew = receiptsService.getByDateAndShops(calen.getTime(), newShops.getId());

        assertEquals(receipts.getId(), receiptsNew.getId());
    }




}