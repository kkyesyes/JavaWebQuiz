package src.com.kk.furns.service.impl;

import src.com.kk.furns.dao.MemberDAO;
import src.com.kk.furns.dao.impl.MemberDAOImpl;
import src.com.kk.furns.entity.Member;
import src.com.kk.furns.service.MemberService;

/**
 * @author KK
 * @version 1.0
 */
public class MemberServiceImpl implements MemberService {
    private MemberDAO memberDAO = new MemberDAOImpl();

    /**
     * 用户注册服务
     * @param member 传入的用户对象
     * @return 注册成功返回 true，否则返回 false
     */
    @Override
    public boolean userRegister(Member member) {
        if (isExistsUsername(member.getUsername())) {
            return false;
        }
        if (memberDAO.saveMember(member) == -1) {
            return false;
        }
        return true;
    }

    /**
     * 用户登录服务
     * @param member 传入的用户对象
     * @return 登录成功返回 true，否则返回 false
     */
    @Override
    public boolean userLogin(Member member) {
        Member resultMember = memberDAO.queryMemberByUsernameAndPassword(member);
        if (resultMember == null) {
            return false;
        }
        return true;
    }

    /**
     * 判断用户名是否存在
     * @param username 用户名
     * @return 若存在返回 true，否则返回 false
     */
    @Override
    public boolean isExistsUsername(String username) {
        return memberDAO.queryMemberByUsername(username) == null ? false : true;
    }
}
