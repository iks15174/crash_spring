package com.blockgame.crash.service;

import com.blockgame.crash.model.MemberVo;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;

public interface AuthService extends UserDetailsService {
    public String saveMember(MemberVo memberVo);
    public void validateHandling(Errors errors, Model model);
}
