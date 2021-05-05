package com.blockgame.crash.service;

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

@Service
public class MemberServiceImpl implements MemberService{

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        MemberVo memberVo = memberRepository.findById(id);
        if(memberVo != null){
            return new PrincipalDetails(memberVo);
        }
        return null;
    }

    @Transactional
    @Override
    public String saveMember(MemberVo memberVo){
        memberVo.setRole("ROLE_USER");
        memberVo.setPassword(bCryptPasswordEncoder.encode(memberVo.getPassword()));
        return memberRepository.save(memberVo).getId();
    }
    
}
