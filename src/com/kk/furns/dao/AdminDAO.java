package src.com.kk.furns.dao;

import src.com.kk.furns.entity.Admin;

/**
 * @author KK
 * @version 1.0
 */
public interface AdminDAO {
    // 根据管理员用户名查询
    Admin queryAdminByUsername(String username);

    // 根据管理员用户名和密码查询
    Admin queryAdminByUsernameAndPassword(Admin admin);

    // 保存管理员
    int saveAdmin(Admin admin);
}
