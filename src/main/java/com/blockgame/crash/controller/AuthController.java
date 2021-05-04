package com.blockgame.crash.controller;

import com.blockgame.crash.model.MemberVo;
import com.blockgame.crash.service.MemberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("account")
public class AuthController {

    @Autowired
    private MemberService memberService;

    @GetMapping(value = "/signup")
    public String signupView(){
        return "auth/signup";
    }

    @PostMapping(value = "/signup")
    public String signup(MemberVo memberVo){
        System.out.println("POST REQUEST");
        memberService.saveMember(memberVo);
        return "redirect:/";
    }
    
}
