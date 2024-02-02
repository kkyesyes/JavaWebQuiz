package src.com.kk.furns.dao.impl;

import src.com.kk.furns.dao.AdminDAO;
import src.com.kk.furns.dao.BasicDAO;
import src.com.kk.furns.entity.Admin;
import src.com.kk.furns.entity.Member;

/**
 * @author KK
 * @version 1.0
 */
public class AdminDAOImpl extends BasicDAO implements AdminDAO {
    /**
     * 通过管理员用户名查询返回对应的 Admin
     * @param username 管理员用户名
     * @return 对应的 Admin, 若无 Admin 则返回 null
     */
    @Override
    public Admin queryAdminByUsername(String username) {
        String sql = "select `id`, `username`, `password`, `email` from `admin` " +
                "where `username`=?";
        return (Admin) querySingle(sql, Admin.class, username);
    }

    /**
     * 通过管理员用户名和密码查询
     * @param admin 传入的 Admin 对象
     * @return
     */
    @Override
    public Admin queryAdminByUsernameAndPassword(Admin admin) {
        String sql = "select `id`, `username`, `password`, `email` from `admin` " +
                "where `username`=? and `password`=md5(?)";
        return (Admin) querySingle(sql, Admin.class, admin.getUsername(), admin.getPassword());
    }

    /**
     * 保存一个管理员
     * @param admin 传入的 Admin 对象
     * @return 返回 -1 就是失败，返回其它数字则是受影响的行数
     */
    @Override
    public int saveAdmin(Admin admin) {
        String sql = "insert into `admin` (`username`, `password`, `email`)" +
                "values(?, md5(?), ?)";

        return update(sql, admin.getUsername(),
                admin.getPassword(), admin.getEmail());
    }
}
