package src.com.kk.furns.dao;

import src.com.kk.furns.entity.Member;

/**
 * @author KK
 * @version 1.0
 */
public interface MemberDAO {
    public Member queryMemberByUsername(String username);
    public int saveMember(Member member);
}
