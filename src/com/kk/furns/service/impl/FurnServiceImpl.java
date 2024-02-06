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

    /**
     * 列出数据库中所有家居信息
     * @return 返回数据库中所有家居信息的集合
     */
    @Override
    public List<Furn> list() {
        return furnDAO.queryFurns();
    }

    /**
     * 根据传入的家居对象添加家居
     * @param furn 传入的家居对象
     * @return 返回受影响的行数
     */
    @Override
    public int add(Furn furn) {
        return furnDAO.addFurn(furn);
    }

    /**
     * 根据 id 删除对应家居
     * @param id 传入的家居 id
     * @return 返回受影响的行数
     */
    @Override
    public int deleteFurnById(int id) {
        return furnDAO.deleteFurnById(id);
    }

    /**
     * 根据 id 查询家居对象
     * @param id 传入的家居 id
     * @return 返回查到的家居对象
     */
    @Override
    public Furn queryFurnById(int id) {
        return furnDAO.queryFurnById(id);
    }

    /**
     * 根据传入的家居对象更新自己
     * @param furn 传入的家居对象
     * @return 返回受影响行数
     */
    @Override
    public int updateFurn(Furn furn) {
        return furnDAO.updateFurn(furn);
    }

    /**
     * 根据页索引和页大小封装分页对象
     * @param pageNo 页索引
     * @param pageSize 页大小
     * @return 返回封装好的分页对象
     */
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

    /**
     * 根据家居名封装分页 Page 对象
     * @param pageNo
     * @param pageSize
     * @param name 传入的家居名
     * @return
     */
    @Override
    public Page<Furn> pageByName(int pageNo, int pageSize, String name) {
        Page<Furn> page = new Page<>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        int totalRow = furnDAO.getPageTotalCountByName(name);
        page.setTotalRows(totalRow);
        int totalPages = totalRow / pageSize + (totalRow % pageSize == 0 ? 0 : 1);
        page.setTotalPages(totalPages);
        int begin = (pageNo - 1) * pageSize;
        List<Furn> pageFurns = furnDAO.getPageItemsByName(begin, pageSize, name);
        page.setItems(pageFurns);
        return page;
    }
}
