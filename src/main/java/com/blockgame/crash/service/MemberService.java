package com.blockgame.crash.service;

import com.blockgame.crash.model.MemberVo;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface MemberService extends UserDetailsService {
    public String saveMember(MemberVo memberVo);
}
