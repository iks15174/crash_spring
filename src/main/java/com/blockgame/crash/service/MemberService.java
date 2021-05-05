package com.blockgame.crash.service;

import java.util.Map;

import com.blockgame.crash.model.MemberVo;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.Errors;

public interface MemberService extends UserDetailsService {
    public String saveMember(MemberVo memberVo);
    public Map<String, String> validateHandling(Errors errors);
}
