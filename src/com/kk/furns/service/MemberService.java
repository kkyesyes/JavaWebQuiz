package src.com.kk.furns.service;

import src.com.kk.furns.entity.Member;

/**
 * @author KK
 * @version 1.0
 */
public interface MemberService {
    // 用户注册
    boolean userRegister(Member member);

    // 用户登录
    boolean userLogin(Member member);

    // 判断用户名是否存在
    boolean isExistsUsername(String username);
}
