package com.blockgame.crash.controller;

import java.util.Map;

import javax.validation.Valid;

import com.blockgame.crash.model.MemberVo;
import com.blockgame.crash.service.AuthService;
import com.blockgame.crash.validator.MemberVoValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("account")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private MemberVoValidator memberVoValidator;

    @GetMapping(value = "/signup")
    public String signupView(){
        return "auth/signup";
    }

    @RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
    public String loginView(){
        return "auth/login";
    }

    @PostMapping(value = "/signup")
    public String signup(@Valid MemberVo memberVo, Errors errors, Model model){
        if(errors.hasErrors()){
            model.addAttribute("memberVo", memberVo);
            authService.validateHandling(errors, model);
            return "auth/signup";
        }
        memberVoValidator.validate(memberVo, errors);
        if(errors.hasErrors()){
            model.addAttribute("memberVo", memberVo);
            authService.validateHandling(errors, model);
            return "auth/signup";
        }
        authService.saveMember(memberVo);
        return "redirect:/";
    }
    
}
