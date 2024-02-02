package src.com.kk.furns.dao;

import src.com.kk.furns.entity.Member;

/**
 * @author KK
 * @version 1.0
 */
public interface MemberDAO {
    // 根据用户名查询
    Member queryMemberByUsername(String username);

    // 根据用户名和密码查询
    Member queryMemberByUsernameAndPassword(Member member);

    // 保存用户
    int saveMember(Member member);
}
