package com.blockgame.crash.validator;

import com.blockgame.crash.model.MemberVo;
import com.blockgame.crash.repository.MemberRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class MemberVoValidator implements Validator{

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return MemberVo.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MemberVo memberVo = (MemberVo) target;
        if(memberRepository.existsByName(memberVo.getName())){
            errors.rejectValue("name", "" ,"이미 존재하는 이름입니다.");
            return;
        }
        if(memberRepository.existsById(memberVo.getId())){
            errors.rejectValue("id", "","이미 존재하는 아이디입니다.");
            return;
        }
    }

    
}
