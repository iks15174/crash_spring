package com.blockgame.crash.controller;

import java.util.Map;

import javax.validation.Valid;

import com.blockgame.crash.model.MemberVo;
import com.blockgame.crash.service.MemberService;
import com.blockgame.crash.validator.MemberVoValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("account")
public class AuthController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberVoValidator memberVoValidator;

    @GetMapping(value = "/signup")
    public String signupView(){
        return "auth/signup";
    }

    @GetMapping(value = "/login")
    public String loginView(){
        return "auth/login";
    }

    @PostMapping(value = "/signup")
    public String signup(@Valid MemberVo memberVo, Errors errors, Model model){
        if(errors.hasErrors()){
            model.addAttribute("memberVo", memberVo);
            memberService.validateHandling(errors, model);
            return "auth/signup";
        }
        memberVoValidator.validate(memberVo, errors);
        if(errors.hasErrors()){
            model.addAttribute("memberVo", memberVo);
            memberService.validateHandling(errors, model);
            return "auth/signup";
        }
        memberService.saveMember(memberVo);
        return "redirect:/";
    }
    
}
