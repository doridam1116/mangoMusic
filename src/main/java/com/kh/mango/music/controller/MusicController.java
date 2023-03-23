package com.kh.mango.music.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MusicController {
    @GetMapping("/search")
    public String search(){
        return "search";
    }
}
