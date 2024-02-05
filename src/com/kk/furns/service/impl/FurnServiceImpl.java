package src.com.kk.furns.service.impl;

import src.com.kk.furns.dao.FurnDAO;
import src.com.kk.furns.dao.impl.FurnDAOImpl;
import src.com.kk.furns.entity.Furn;
import src.com.kk.furns.entity.Page;
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

    @Override
    public int add(Furn furn) {
        return furnDAO.addFurn(furn);
    }

    @Override
    public int deleteFurnById(int id) {
        return furnDAO.deleteFurnById(id);
    }

    @Override
    public Furn queryFurnById(int id) {
        return furnDAO.queryFurnById(id);
    }

    @Override
    public int updateFurn(Furn furn) {
        return furnDAO.updateFurn(furn);
    }

    @Override
    public Page<Furn> page(int pageNo, int pageSize) {
        Page<Furn> page = new Page<>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        int totalRow = furnDAO.getTotalRow();
        page.setTotalRows(totalRow);
        int totalPages = totalRow / pageSize + (totalRow % pageSize == 0 ? 0 : 1);
        page.setTotalPages(totalPages);
        int begin = (pageNo - 1) * pageSize;
        List<Furn> pageFurns = furnDAO.getPageFurns(begin, pageSize);
        page.setItems(pageFurns);
        return page;
    }
}
