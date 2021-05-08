package com.blockgame.crash.config.auth;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.Collection;

import com.blockgame.crash.model.MemberVo;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class PrincipalDetails implements UserDetails{

    private MemberVo memberVo;

    public PrincipalDetails(MemberVo memberVo){
        this.memberVo = memberVo;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new GrantedAuthority(){
            
            @Override
            public String getAuthority(){
                return memberVo.getRole();
            }
        });
        return collect;
    }

    @Override
    public String getPassword() {
        return memberVo.getPassword();
    }

    @Override
    public String getUsername() {
        return memberVo.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
}
