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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
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
        System.out.println(memberVo);
        if (memberVo != null) {
            return new PrincipalDetails(memberVo);
        }
        return null;
    }

    public Map<String, String> validateHandling(Errors errors) {
        Map<String, String> validatorResult = new HashMap<>();

        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = error.getField();
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }

        return validatorResult;
    }

    @Transactional
    @Override
    public String saveMember(MemberVo memberVo) {
        memberVo.setRole("ROLE_USER");
        memberVo.setPassword(bCryptPasswordEncoder.encode(memberVo.getPassword()));
        return memberRepository.save(memberVo).getId();
    }

}
