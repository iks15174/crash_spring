package com.blockgame.crash.controller;

import com.blockgame.crash.service.BoardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping(value = "/")
    public String mainView(Model model){
        model.addAttribute("recordList", boardService.getAllRecord());
        return "board/main";
    }

    @GetMapping(value = "/game")
    public String game(){
        return "board/game";
    }

    @GetMapping(value = "/game/finished")
    public String gameFinished(Long score){

        boardService.saveScore(score);

        return "redirect:/game";
    }
}
