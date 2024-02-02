package src.com.kk.furns.service;

import src.com.kk.furns.entity.Admin;

/**
 * @author KK
 * @version 1.0
 */
public interface AdminService {
    // 管理员注册
    boolean adminRegister(Admin admin);

    // 管理员登录
    boolean adminLogin(Admin admin);

    // 判断管理员用户名是否存在
    boolean isExistsUsername(String username);
}
