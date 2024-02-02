package src.com.kk.furns.service.impl;

import src.com.kk.furns.dao.FurnDAO;
import src.com.kk.furns.dao.impl.FurnDAOImpl;
import src.com.kk.furns.entity.Furn;
import src.com.kk.furns.service.FurnService;

import java.util.List;

/**
 * @author KK
 * @version 1.0
 */
public class FurnServiceImpl implements FurnService {
    private FurnDAO furnDAO = new FurnDAOImpl();

    @Override
    public List<Furn> list() {
        return furnDAO.queryFurns();
    }
}
