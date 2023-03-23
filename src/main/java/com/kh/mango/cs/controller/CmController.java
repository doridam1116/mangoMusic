package com.kh.mango.cs.controller;

import com.kh.mango.cs.domain.Comment;
import com.kh.mango.cs.domain.Cs;
import com.kh.mango.cs.service.CmService;
import com.kh.mango.cs.service.CsService;
import com.kh.mango.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CmController {

    @Autowired
    private CmService cmService;

    // 댓글 등록
    @PostMapping("/commentWrite")
    private String insertComment(
            HttpSession session
            , @ModelAttribute Comment comment
            , HttpServletRequest request
            , Model model
            , @RequestParam("csNo") int csNo) {
            User user = (User)session.getAttribute("loginUser");
            comment.setUserNo(user.getUserNo());
            comment.setCsNo(csNo);
            int result = cmService.insertComment(comment);
            if (result > 0) {
                return "redirect:/qnaDetail?csNo=" + csNo;
            } else {
                model.addAttribute("msg", "로그인");
                model.addAttribute("url", "/qnaDetail?csNo=" + comment.getCsNo());
                return "/common/error";
            }
    }
    // 댓글 삭제
    @GetMapping("/commentRemove")
    private String DeleteComment(
            @RequestParam("commentNo") int commentNo
            , @ModelAttribute Comment comment
            , @ModelAttribute Cs cs
            , @RequestParam("csNo") int csNo
            , Model model) {
        int result = cmService.deleteComment(commentNo);
        if(result > 0) {
            return "redirect:/qnaDetail?csNo="+csNo;
        }else {
            model.addAttribute("error", "삭제가 완료되지 않았습니다.");
            return "/common/error";
        }
    }
//    @GetMapping(value = "/commentList")
//    public String commentListView(Model model){
//        List<Comment> commentList = cService.selectCommentList();
//        model.addAttribute("commentList", commentList);
//        return "/qnaDetail";
//    }
}
