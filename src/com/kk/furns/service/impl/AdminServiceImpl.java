package src.com.kk.furns.service.impl;

import src.com.kk.furns.dao.AdminDAO;
import src.com.kk.furns.dao.impl.AdminDAOImpl;
import src.com.kk.furns.entity.Admin;
import src.com.kk.furns.service.AdminService;

/**
 * @author KK
 * @version 1.0
 */
public class AdminServiceImpl implements AdminService {
    private AdminDAO adminDAO = new AdminDAOImpl();

    /**
     * 管理员注册服务
     * @param admin 传入的管理员对象
     * @return 注册成功返回 true，否则返回 false
     */
    @Override
    public boolean adminRegister(Admin admin) {
        if (isExistsUsername(admin.getUsername())) {
            return false;
        }
        if (adminDAO.saveAdmin(admin) == -1) {
            return false;
        }
        return true;
    }

    /**
     * 管理员登录服务
     * @param admin 传入的管理员对象
     * @return 登录成功返回 true，否则返回 false
     */
    @Override
    public boolean adminLogin(Admin admin) {
        Admin resultAdmin = adminDAO.queryAdminByUsernameAndPassword(admin);
        if (resultAdmin == null) {
            return false;
        }
        return true;
    }

    /**
     * 判断管理员用户名是否存在
     * @param username 管理员用户名
     * @return 若存在返回 true，否则返回 false
     */
    @Override
    public boolean isExistsUsername(String username) {
        return adminDAO.queryAdminByUsername(username) == null ? false : true;
    }
}
