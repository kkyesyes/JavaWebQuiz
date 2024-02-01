package src.com.kk.furns.test;

import org.junit.Test;
import src.com.kk.furns.entity.Member;
import src.com.kk.furns.service.MemberService;
import src.com.kk.furns.service.impl.MemberServiceImpl;

/**
 * @author KK
 * @version 1.0
 */
public class MemberServiceTest {
    MemberService memberService = new MemberServiceImpl();

    @Test
    public void userRegister() {
        if (memberService.userRegister(new Member(null, "bbyesyes",
                "123456", "bbyesyes@qq.com"))) {
            System.out.println("注册成功");
        } else {
            System.out.println("注册失败");
        }
    }
}
