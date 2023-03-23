package com.kh.mango.comment.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kh.mango.comment.domain.Comment;
import com.kh.mango.comment.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CommentController {
    @Autowired
    private CommentService cmService;


    @PostMapping("/tradeCommentWrite")
    public String tradeCommentWrite(
            @RequestParam("tradeNo") int tradeNo
            , @RequestParam("commentContent") String commentContent
            ,@RequestParam("userNo") int userNo
    ){
        Comment comment = new Comment(userNo,commentContent,tradeNo);
        int result = cmService.insertCommentWrite(comment);
        return "redirect:tradeDetail?tradeNo="+tradeNo;
    }

    @PostMapping("/tradeCmRemove")
    @ResponseBody
    public String tradeCmRemove(int commentNo){
        int result = cmService.deleteByCommentNo(commentNo);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = "";

        try {
            jsonString = objectMapper.writeValueAsString(result);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return jsonString;
        }

        return jsonString;
    }
}
