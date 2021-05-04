package com.blockgame.crash.controller;

import com.blockgame.crash.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

    @Autowired
    private MemberService memberService;

    @GetMapping(value = "/")
    public String MainView(){
        return "board/main";
    }
    
}
