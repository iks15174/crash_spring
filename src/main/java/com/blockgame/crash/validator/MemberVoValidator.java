package com.blockgame.crash.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        Pattern p = Pattern.compile("(?=.*[0-9]).{4,20}");
        Matcher m = p.matcher(memberVo.getPassword());
        boolean passwordExp = m.matches();
        if(!passwordExp){
            //@Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{4,20}",
            //message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 20자의 비밀번호여야 합니다.")
            errors.rejectValue("password", "", "비밀번호는 숫자가 4자 ~ 20자의 비밀번호여야 합니다.");
            return;
        }
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
