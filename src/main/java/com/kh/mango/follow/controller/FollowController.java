package com.kh.mango.follow.controller;

import com.kh.mango.follow.domain.Follow;
import com.kh.mango.follow.domain.FollowYn;
import com.kh.mango.follow.domain.SearchUser;
import com.kh.mango.follow.service.FollowService;
import com.kh.mango.point.domain.Point;
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
public class FollowController {

    @Autowired
    private UserService uService;

    @Autowired
    private FollowService followService;


    @GetMapping("/userSearch")
    public String userSearch() {
        return "userSearch";
    }

//    @GetMapping("/userList")
//    public String userList(@RequestParam("searchValue") String searchValue
//            , @SessionAttribute("loginUser") User user
//            , Model model) {
//        SearchUser searchUser = new SearchUser(searchValue, user.getUserNo());
//        List<User> searchList = followService.searchUser(searchUser);
//        List<Follow> followList = followService.followingUser(user.getUserNo());
//        model.addAttribute("user", searchList);
//        model.addAttribute("followList", followList);
//        return "userList";
//        }



    @GetMapping("/userList")
    public String userList(@RequestParam("searchValue") String searchValue
             , @SessionAttribute("loginUser") User user
                         , Model model) {
        SearchUser searchUser = new SearchUser(searchValue, user.getUserId());
//        List<User> searchList = followService.searchUser(searchUser);
        List<FollowYn> searchList = followService.searchUser(searchUser);
        model.addAttribute("user", searchList);
        return "userList";
    }





//    @GetMapping("/userFollow")
//    public String followUser(Model model
//            , @SessionAttribute("loginUser") User user
//            ,@RequestParam("userNo")int followNo) {
//        Follow followUser = new Follow(user.getUserNo(), followNo);
//        int result = followService.followUser(followUser);
//        if(result > 0) {
//            return "/userSearch";
//        } else {
//            return "/index";
//        }
//    }

    @PostMapping("/ajaxFollowUser")
    @ResponseBody
    public String ajaxFollowUser(
            //@SessionAttribute("loginUser") User user
             int userNo
            , int followNo){
        Follow followUser = new Follow(userNo, followNo);
        int result = followService.followUser(followUser);

        if(result > 0) {
            return "/userSearch";
        } else {
            return "/index";
        }
    }

    
}
