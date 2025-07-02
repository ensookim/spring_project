package org.scoula.spring.security.account.mapper;

import org.scoula.spring.security.account.domain.MemberVO;

public interface UserDetailsMapper {

    public MemberVO get(String username);
}