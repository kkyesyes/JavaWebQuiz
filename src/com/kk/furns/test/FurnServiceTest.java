package src.com.kk.furns.test;

import jdk.nashorn.internal.ir.CallNode;
import org.junit.Test;
import src.com.kk.furns.entity.Furn;
import src.com.kk.furns.entity.Page;
import src.com.kk.furns.service.FurnService;
import src.com.kk.furns.service.impl.FurnServiceImpl;

/**
 * @author KK
 * @version 1.0
 */
public class FurnServiceTest {
    private FurnService furnService = new FurnServiceImpl();
    @Test
    public void pageByNameTest() {
        Page<Furn> furnPage = furnService.pageByName(1, 2, "5435");
        System.out.println(furnPage.getPageNo());
    }
}
