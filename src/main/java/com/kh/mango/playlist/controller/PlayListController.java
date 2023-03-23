package com.kh.mango.playlist.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kh.mango.playlist.domain.PlayList;
import com.kh.mango.playlist.service.PlayListService;
import com.kh.mango.user.domain.MyPage;
import com.kh.mango.user.domain.User;
import com.kh.mango.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
public class PlayListController {

    @Autowired
    private PlayListService pService;

    @Autowired
    private UserService uService;


    @GetMapping("/addPlaylist")
    public String addPlaylist() {
        return "addPlaylist";
    }


    @PostMapping("/ajaxAddPlaylist")
    @ResponseBody
    public String ajaxAddPlaylist(int userNo, String name, String artist) {
        PlayList playlistParam = new PlayList(userNo, name, artist);
        int playlist = pService.addPlaylist(playlistParam);
        return "success";
    }



    @GetMapping("/myPlaylist")
    public String myPlaylist(Model model, @SessionAttribute(value = "loginUser", required = false) User user) {
        List<PlayList> myPlaylist = pService.showMyPlaylist(user.getUserNo());
        model.addAttribute("myPlaylist", myPlaylist);
        return "myPlaylist";
    }

    @GetMapping("/followPlaylist")
    public String followPlaylist(Model model, @RequestParam("userNo") int userNo,
                                 @RequestParam("userName") String userName) {
        List<PlayList> followPlaylist = pService.showFollowPlaylist(userNo);
        model.addAttribute("followPlaylist",followPlaylist);
        model.addAttribute("userName", userName);
        return "followPlaylist";
    }

    @GetMapping("/followerPage")
    public String followerPage(Model model, @RequestParam("userNo") int userNo) {
        MyPage followerPage = uService.myPageInfo(userNo);
        List<PlayList> followPlaylist = pService.showFollowPlaylist(userNo);
        model.addAttribute("followerPage",followerPage);
        model.addAttribute("followPlaylist",followPlaylist);
        return "followerPage";
    }



//    @GetMapping("/library")
//    public String showLibrary(Model model, @SessionAttribute(value = "loginUser", required = false) User user) {
//        try {
//            if (user == null) {
//                throw new Exception("Session attribute 'loginUser' is missing");
//            }
//            MyPage myPage = uService.myPageInfo(user.getUserNo());
//            return "library";
//        } catch (Exception e) {
//            model.addAttribute("error", e.getMessage());
//            return "mypage";
//        }
//    }

    @PostMapping("/ajaxDeletePlaylist")
    @ResponseBody
    public String ajaxDeletePlaylist(int userNo, String playlistSong, String playlistArtist) {
        PlayList playlistParam = new PlayList(userNo, playlistSong, playlistArtist);
        int result = pService.deletePlaylist(playlistParam);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = "";

        try {
            jsonString = objectMapper.writeValueAsString("success");
        } catch (JsonProcessingException e) {
            return  jsonString;
        }
        return jsonString;
    }

}
