package src.com.kk.furns.test;

import org.junit.Test;
import src.com.kk.furns.dao.FurnDAO;
import src.com.kk.furns.dao.impl.FurnDAOImpl;
import src.com.kk.furns.entity.Furn;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author KK
 * @version 1.0
 */
public class FurnDAOTest {
    FurnDAO furnDAO = new FurnDAOImpl();

    @Test
    public void furnDAOTest() {
        Furn furn1 = new Furn(null, "红木小板凳", "全友家居", new BigDecimal(500), 60, 413, null);
        furnDAO.addFurn(furn1);
        List<Furn> furns = furnDAO.queryFurns();
        for (Furn furn : furns) {
            System.out.println(furn);
        }
    }
}
