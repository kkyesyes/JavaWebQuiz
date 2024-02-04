package src.com.kk.furns.dao.impl;

import src.com.kk.furns.dao.BasicDAO;
import src.com.kk.furns.dao.FurnDAO;
import src.com.kk.furns.entity.Furn;

import java.util.List;

/**
 * @author KK
 * @version 1.0
 */
public class FurnDAOImpl extends BasicDAO implements FurnDAO {
    /**
     * 查询所有家居信息
     * @return 含有所有家居信息的集合
     */
    @Override
    public List<Furn> queryFurns() {
        String sql = "select * from `furn`";
        return queryMulti(sql, Furn.class);
    }

    /**
     * 根据传入的 id 查询家居
     * @param id 传入的 id
     * @return 返回家居信息
     */
    @Override
    public Furn queryFurnById(int id) {
        String sql = "select `id`, `name`, `maker`, `price`, `sales`, `inventory`, `picture`" +
                "from `furn` where id=?";
        return (Furn) querySingle(sql, Furn.class, id);
    }

    /**
     * 添加家居
     * @param furn 传入的家居对象
     * @return 返回受影响的行数
     */
    @Override
    public int addFurn(Furn furn) {
        String sql = "insert into `furn`(`id`, `name`, `maker`, `price`, `sales`, `inventory`, `picture`)" +
                "values(null, ?, ?, ?, ?, ?, ?)";
        return update(sql, furn.getName(), furn.getMaker(), furn.getPrice(),
                furn.getSales(), furn.getInventory(), furn.getPicture());
    }

    /**
     * 根据 id 删除家居
     * @param id 家居的 id
     * @return 受影响的行数
     */
    @Override
    public int deleteFurnById(int id) {
        String sql = "delete from `furn` where id=?";
        return update(sql, id);
    }

    @Override
    public int updateFurn(Furn furn) {
        String sql = "update `furn` " +
                "set `name`=?, " +
                "`maker`=?, " +
                "`price`=?, " +
                "`sales`=?, " +
                "`inventory`=?, " +
                "`picture`=? " +
                "where id=?";
        return update(sql, furn.getName(), furn.getMaker(), furn.getPrice(), furn.getSales(),
                furn.getInventory(), furn.getPicture(), furn.getId());
    }
}
