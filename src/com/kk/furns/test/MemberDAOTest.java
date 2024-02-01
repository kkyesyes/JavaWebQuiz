package src.com.kk.furns.test;

import org.junit.Test;
import src.com.kk.furns.dao.MemberDAO;
import src.com.kk.furns.dao.impl.MemberDAOImpl;
import src.com.kk.furns.entity.Member;

/**
 * @author KK
 * @version 1.0
 */
public class MemberDAOTest {
    MemberDAO memberDAO = new MemberDAOImpl();
    @Test
    public void queryMemberByUsername() {
        String username = "aayesyes";
        Member member = memberDAO.queryMemberByUsername(username);
        if (member != null) {
            System.out.println(username + " 存在");
            System.out.println("其邮箱为：" + member.getEmail());
        } else {
            System.out.println("该用户不存在");
        }
    }

    @Test
    public void saveMember() {
        Member member = new Member(null, "aayesyes", "123456", "aayesyes@qq.com");
        if (memberDAO.saveMember(member) != -1) {
            System.out.println("添加成功");
        } else {
            System.out.println("添加失败");
        }
    }
}
