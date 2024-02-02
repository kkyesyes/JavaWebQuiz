package src.com.kk.furns.dao.impl;

import src.com.kk.furns.dao.BasicDAO;
import src.com.kk.furns.dao.MemberDAO;
import src.com.kk.furns.entity.Member;

/**
 * @author KK
 * @version 1.0
 */
public class MemberDAOImpl extends BasicDAO<Member> implements MemberDAO {

    /**
     * 通过用户名查询返回对应的 Member
     * @param username 用户名
     * @return 对应的 Member, 若无 Member 则返回 null
     */
    @Override
    public Member queryMemberByUsername(String username) {
        String sql = "select `id`, `username`, `password`, `email` from `member` where `username`=?";
        return querySingle(sql, Member.class, username);
    }

    /**
     * 通过用户名和密码查询
     * @param member 传入的 Member 对象
     * @return
     */
    @Override
    public Member queryMemberByUsernameAndPassword(Member member) {
        String sql = "select `id`, `username`, `password`, `email` from `member` " +
                "where `username`=? and `password`=md5(?)";
        return querySingle(sql, Member.class, member.getUsername(), member.getPassword());
    }

    /**
     * 保存一个会员
     * @param member 传入的 Member 对象
     * @return 返回 -1 就是失败，返回其它数字则是受影响的行数
     */
    @Override
    public int saveMember(Member member) {
        String sql = "insert into `member` (`username`, `password`, `email`)" +
                "values(?, md5(?), ?)";

        return update(sql, member.getUsername(),
                member.getPassword(), member.getEmail());
    }
}
