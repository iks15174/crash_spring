package com.blockgame.crash.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

    @GetMapping(value = "/")
    public String MainView(){
        return "board/main";
    }
    
}
