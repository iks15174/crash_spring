package com.blockgame.crash.service;

import java.util.Optional;

import javax.transaction.Transactional;

import com.blockgame.crash.model.MemberVo;
import com.blockgame.crash.repository.MemberRepository;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService{

    @Autowired
    private MemberRepository memberRepository;

    //@Override
    //public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
    //    //Optional<MemberVo> userEntityWrapper = memberRepository.findById(id);
    //    return null;
    //}

    @Transactional
    @Override
    public String saveMember(MemberVo memberVo){

        //BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        //memberVo.setPassword(passwordEncoder.encode(memberVo.getPassword()));
        //return memberRepository.save(memberVo).getId();
        return "temp";
    }
    
}
