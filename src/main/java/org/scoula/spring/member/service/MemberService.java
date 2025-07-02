package org.scoula.spring.member.service;

import org.scoula.spring.member.dto.ChangePasswordDTO;
import org.scoula.spring.member.dto.MemberDTO;
import org.scoula.spring.member.dto.MemberJoinDTO;
import org.scoula.spring.member.dto.MemberUpdateDTO;

public interface MemberService {
    boolean checkDuplicate(String username);
    MemberDTO get(String username);
    MemberDTO join(MemberJoinDTO member);
    MemberDTO update(MemberUpdateDTO member);
    void changePassword(ChangePasswordDTO changePassword);

}