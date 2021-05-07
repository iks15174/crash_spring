package com.blockgame.crash.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import com.blockgame.crash.config.auth.PrincipalDetails;
import com.blockgame.crash.model.MemberVo;
import com.blockgame.crash.repository.MemberRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        MemberVo memberVo = memberRepository.findById(id);
        if (memberVo != null) {
            return new PrincipalDetails(memberVo);
        }
        return null;
    }

    public void validateHandling(Errors errors, Model model) {

        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = error.getField();
            model.addAttribute(validKeyName, error.getDefaultMessage());
        }
    }

    @Transactional
    @Override
    public String saveMember(MemberVo memberVo) {
        memberVo.setRole("ROLE_USER");
        //memberVo.setPassword(bCryptPasswordEncoder.encode(memberVo.getPassword())); -> 비밀번호 4자리 조건에 걸림. 바꿀 것
        return memberRepository.save(memberVo).getId();
    }

}
