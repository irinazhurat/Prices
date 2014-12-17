package test.teivar.prices.service;

/**
 * Created by zalesskiy_k on 17.12.2014.
 */

import com.teivar.prices.entity.Goods;
import com.teivar.prices.service.GoodsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import test.teivar.prices.config.TestDataBaseConfig;
import test.teivar.prices.util.GoodsUtil;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import static org.junit.Assert.assertEquals;

@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestDataBaseConfig.class)
@WebAppConfiguration
public class GoodsServiceTest {

    @Resource
    private EntityManagerFactory emf;
    protected EntityManager em;

    @Resource
    private GoodsService goodsService;

    @Before
    public void setUp() throws Exception {
        em = emf.createEntityManager();
    }

    @Test
    public void testSaveGoods() throws Exception {
        Goods goods = GoodsUtil.createGoods();

        goodsService.addGoods(goods);
        Goods newgoods = goodsService.getByName("New apple");

        assertEquals(newgoods.getName(), goods.getName());
        assertEquals(newgoods.getBarcode(), goods.getBarcode());
    }




}
