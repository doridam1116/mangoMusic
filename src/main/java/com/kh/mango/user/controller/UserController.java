package com.kh.mango.user.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kh.mango.message.domain.Message;
import com.kh.mango.playlist.domain.PlayList;
import com.kh.mango.playlist.service.PlayListService;
import com.kh.mango.point.domain.PointRecord;
import com.kh.mango.user.domain.*;
import com.kh.mango.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.Calendar;
import java.util.List;

@Controller
@Slf4j
public class UserController {
    @Autowired
    private UserService uService;

    @Autowired
    private PlayListService pService;

    // 회원가입 화면
    @RequestMapping(method = RequestMethod.GET, value = "/register")
    public String register() {
        return "register";
    }
    // 유저 회원가입
    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public String userRegister(
            HttpServletRequest request
            , @ModelAttribute User user
            , Model model) {
        try {
           uService.insertUser(user);
            model.addAttribute("success", "성공");
           return "index";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("msg", e.getMessage());
            return "/register";
        }
    }

    // 로그인 화면
    @RequestMapping(method = RequestMethod.GET, value = "/login")
    public String loginView() {
        return "login";
    }

    // 유저 로그인
    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public String userLogin(
            HttpServletRequest request
            , @RequestParam("user-id") String userId
            , @RequestParam("user-pw") String userPw
            , Model model) {
        try {
            User uParam = new User(userId, userPw);
            User user = uService.checkUserLogin(uParam);
            HttpSession session = request.getSession();
            if (user != null) {
                session.setAttribute("loginUser", user);
//                model.addAttribute("success", 1);
                return "index";
            }else {
                model.addAttribute("error", "에러");
                return "login";
            }

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("msg", e.getMessage());
            return "error";
        }
    }
    // 아이디 찾기
    @RequestMapping(method = RequestMethod.GET, value = "/findId")
    public String findIdView() {
        return "findId";
    }

    @RequestMapping(value = "/findId", method = RequestMethod.POST)
    public String findId(
            HttpServletRequest request
            , @RequestParam("userName") String userName
            , @RequestParam("userEmail") String userEmail
            , Model model) {

        User uParam = new User(userName, userEmail, null);
        User user = uService.findUserId(uParam);
        if(user != null) {
            model.addAttribute("findId", "성공");
            model.addAttribute("user", user);
            return "login";
        }else {
            model.addAttribute("error", "에러");
            return "findId";
        }
    }

    @GetMapping("/newFind")
    public String newFindId(
            @ModelAttribute User userParam
            ,Model model){
//        User uParam = new User(userParam.getUserName(), userParam.getUserEmail(), null);
        User user = uService.findUserId(userParam);
        model.addAttribute("user", user);
        return "newfind";
    }

    // 비밀번호 찾기
    @RequestMapping(method = RequestMethod.GET, value = "/findPw")
    public String findPwView() {
        return "findPw";
    }

    @RequestMapping(value = "/findPw", method = RequestMethod.POST)
    public String findPw(
            HttpServletRequest request
            , @RequestParam("userId") String userId
            , @RequestParam("userName") String userName
            , @RequestParam("userEmail") String userEmail
            , Model model) {
        User uParam = new User(userId,null ,userName , userEmail);
        User user = uService.selectUserPw(uParam);
        if(user != null) {
            model.addAttribute("findPw", "성공");
            model.addAttribute("user", user);
            return "login";
        }else {
            model.addAttribute("error", "에러");
            return "findPw";
        }
    }

    // 비밀번호 변경
    @GetMapping("/updatePw")
    public String updatePwView(
            @ModelAttribute User user
            , Model model){
        model.addAttribute("user", user);
        return "updatePw";
    }
    @PostMapping("/updatePw")
    public String updatePw(
            @ModelAttribute("user")User user
            , @ModelAttribute("newPw") String userPw
            ,Model model){
        User uParam = new User(user.getUserId(),userPw);
        int result = uService.updateUserPw(uParam);
        if(result > 0){
            model.addAttribute("su","성공");
        }
        return "login";
    }

    // 로그아웃
    @GetMapping("/logout")
    public String userLogout(HttpSession session, Model model) {
        if(session != null) {
            session.invalidate();
            return "index";
        }else {
            model.addAttribute("msg", "로그아웃 처리 하지 못하였습니다.");
            return "error";
        }
    }


    @GetMapping("/mypage")
    public String myPageView(Model model, @SessionAttribute(value = "loginUser", required = false) User user) {
        try {
            if(user == null) {
                throw new Exception("Session attribute 'loginUser' is missing");
            }
            MyPage myPage = uService.myPageInfo(user.getUserNo());
            List<MyPageFollow> followList = uService.myPageFollow(user.getUserNo());
            List<MyPageDeals> deals = uService.myPageDeals(user.getUserNo());
            List<Like> like = uService.myPageLikes(user.getUserNo());
            List<PointRecord> pointRecordsList = uService.selectPointRecord(user.getUserNo());
            model.addAttribute("myPage",myPage);
            model.addAttribute("followers",followList);
            model.addAttribute("deals",deals);
            model.addAttribute("likes",like);
            model.addAttribute("pointRecord",pointRecordsList);
            return "mypage";
        } catch(Exception e) {
            model.addAttribute("error", e.getMessage());
            return "mypage";
        }
    }

    @PostMapping("/ajaxProfileModify")
    @ResponseBody
    public String ajaxProfileModify(int userNo, String userName, String userLetter,@SessionAttribute("loginUser") User loginUser,HttpSession session){
        User user = new User(userNo,null,userName,null,userLetter);
        user = uService.updateUserProfile(user);
        loginUser.setUserName(user.getUserName());
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = "";

        try {
            jsonString = objectMapper.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return jsonString;
        }
        session.setAttribute("loginUser",loginUser);
        return jsonString;
    }



    @PostMapping("/ajaxMsgUserSearch")
    @ResponseBody
    public String ajaxMsgUserSearch(String userId){
        List<UserSearch> userList = null;
        if(userId != null && userId != "") {
            userList = uService.selectUserList(userId);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = "";
        
        try {
            jsonString = objectMapper.writeValueAsString(userList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return jsonString;
        }
        return jsonString;
    }

    @PostMapping("/ajaxMsgUserAdd")
    @ResponseBody
    public String ajaxMsgUserAdd(int userNo){
        List<UserSearch> uList = uService.selectUserList(userNo);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = "";

        try {
            jsonString = objectMapper.writeValueAsString(uList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return jsonString;
        }
        return jsonString;
    }

    @GetMapping("/library")
    public String showLibrary(Model model, @SessionAttribute(value = "loginUser", required = false) User user) {
        try {
            if (user == null) {
                throw new Exception("Session attribute 'loginUser' is missing");
            }
            MyPage myPage = uService.myPageInfo(user.getUserNo());
            model.addAttribute("myPage",myPage);
            List<PlayList> myPlaylist = pService.showMyPlaylist(user.getUserNo());
            model.addAttribute("myPlaylist", myPlaylist);
            return "library";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "library";
        }
    }
    private String path="C:\\study\\programing\\project holder\\mangoMusic\\src\\main\\resources\\static\\profilePic";
    //파일 업로드
    @PostMapping("/ajaxFileUpload")
    @ResponseBody
    public String result(@RequestParam("file") MultipartFile multi, HttpServletRequest request, HttpServletResponse response,@SessionAttribute("loginUser") User loginUser,HttpSession session ) {
        String url = null;

        String jsonString = null;
        try {

            //String uploadpath = request.getServletContext().getRealPath(path);
            String uploadpath = path;

            String originFilename = multi.getOriginalFilename();
            String extName = originFilename.substring(originFilename.lastIndexOf("."), originFilename.length());
            String saveFileName = genSaveFileName("_"+originFilename.replace(extName,"")+extName);

            ObjectMapper objectMapper = new ObjectMapper();
            jsonString = "";

            System.out.println("uploadpath : " + uploadpath);
            System.out.println("originFilename : " + originFilename);
            System.out.println("extensionName : " + extName);
            System.out.println("saveFileName : " + saveFileName);
            if (!multi.isEmpty()) {
                User user = new User(loginUser.getUserNo(),saveFileName, uploadpath);
                int result = uService.updateUserProfilePic(user);
                if (result > 0) {
                    File file = new File(uploadpath.substring(0,uploadpath.length()-10), saveFileName);
                    multi.transferTo(file);
                }
            }
            loginUser.setUserFilename(saveFileName);
            session.setAttribute("loginUser",loginUser);
            jsonString = saveFileName;
        } catch (Exception e) {
            e.printStackTrace();
            return jsonString;
        }
        return jsonString;

    }

    // 현재 시간을 기준으로 파일 이름 생성
    private String genSaveFileName(String extName) {
        String fileName = "";

        Calendar calendar = Calendar.getInstance();
        fileName += "profilePic/";
        fileName += calendar.get(Calendar.YEAR);
        fileName += calendar.get(Calendar.MONTH);
        fileName += calendar.get(Calendar.DATE);
        fileName += calendar.get(Calendar.HOUR);
        fileName += calendar.get(Calendar.MINUTE);
        fileName += calendar.get(Calendar.SECOND);
        fileName += calendar.get(Calendar.MILLISECOND);
        fileName += extName;

        return fileName;
    }


}
