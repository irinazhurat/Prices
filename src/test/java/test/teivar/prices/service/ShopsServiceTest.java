package test.teivar.prices.service;

/**
 * Created by Zalesskiy_K on 17.12.2014.
 */
import com.teivar.prices.entity.Shops;
import com.teivar.prices.service.ShopsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import test.teivar.prices.config.TestDataBaseConfig;
import test.teivar.prices.util.ShopsUtil;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static org.junit.Assert.assertEquals;

@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestDataBaseConfig.class)
@WebAppConfiguration
public class ShopsServiceTest {

    @Resource
    private EntityManagerFactory emf;
    protected EntityManager em;

    @Resource
    private ShopsService shopsService;

    @Before
    public void setUp() throws Exception {
        em = emf.createEntityManager();
    }

    @Test
    public void testSaveShops() throws Exception {
        Shops shops = ShopsUtil.createShops();

        shopsService.addShops(shops);
        Shops newShops = shopsService.getByName("Spar");

        assertEquals(newShops.getName(), shops.getName());
        assertEquals(newShops.getDesc(), shops.getDesc());
    }




}