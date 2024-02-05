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
        Furn furn1 = new Furn(34, "红木小板凳", "全友家居", new BigDecimal(500), 60, 413, null);
        furnDAO.updateFurn(furn1);
        List<Furn> furns = furnDAO.queryFurns();
        for (Furn furn : furns) {
            System.out.println(furn);
        }
    }

    @Test
    public void getTotalRowTest() {
        System.out.println(furnDAO.getTotalRow());
    }

    @Test
    public void getPageFurnsTest() {
        List<Furn> pageFurns = furnDAO.getPageFurns(2, 2);
        for (Furn furn : pageFurns) {
            System.out.println(furn);
        }
    }

}
