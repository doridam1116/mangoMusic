package com.kh.mango.message.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kh.mango.message.domain.Message;
import com.kh.mango.message.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MessageController {
    @Autowired
    private MessageService mService;


    @PostMapping("/ajaxLoadChatRoom")
    @ResponseBody
    public String ajaxLoadChatRoom(int userNo) {
        List<Message> chatRoomList = mService.selectChatRoomList(userNo);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = "";

        try {
            jsonString = objectMapper.writeValueAsString(chatRoomList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return jsonString;
        }

        return jsonString;
    }

    @PostMapping("/ajaxFindByUserNo1")
    @ResponseBody
    public String ajaxFindByUserNo1(int userNo) {
        Message message = mService.selectUserByUserNo1(userNo);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = "";

        try {
            jsonString = objectMapper.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return jsonString;
        }

        return jsonString;
    }


    @PostMapping("/ajaxChatRemove")
    @ResponseBody
    public String ajaxChatRemove(int userNo1, int chatRoomNo) {
        int result = mService.deleteChatRoom(chatRoomNo);
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


    @PostMapping("/ajaxSearchByChatRoom")
    @ResponseBody
    public String ajaxSearchByChatRoom (int userNo1,int userNo2) {
        Message message = new Message(userNo1,userNo2);
        Message messageEmp = mService.selectChatRoom2(message);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = "";

        try {
            jsonString = objectMapper.writeValueAsString(messageEmp);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return jsonString;
        }
        return jsonString;
    }

    // 메시지 보낼시 채팅방 생성
    @PostMapping("/ajaxMsgSend")
    @ResponseBody
    public String ajaxMsgSend(int msgSendNo, int userNo1, String msgContent) {
        Message message = new Message(msgSendNo, msgContent, userNo1);
        Message messageEmp = mService.selectChatRoom(message);
        int result = 0;
        if (messageEmp == null) {
            result = mService.insertCreateToMsgSend(message);
        } else {
            result = mService.insertNotCreateToMsgSend(message);
        }

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

    @PostMapping("/ajaxChatRoom")
    @ResponseBody
    public String ajaxChatRoom(int chatRoomNo) {
        List<Message> mList = mService.selectMessageList(chatRoomNo);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = "";

        try {
            jsonString = objectMapper.writeValueAsString(mList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return jsonString;
        }

        return jsonString;
    }
}
