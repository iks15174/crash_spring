package com.blockgame.crash.service;

import com.blockgame.crash.config.auth.PrincipalDetails;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl implements BoardService{

    @Override
    public void saveScore(String score) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        PrincipalDetails user = (PrincipalDetails)auth.getPrincipal();
        if(user != null){
            
        }
    }
    
}
