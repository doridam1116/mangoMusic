package com.kh.mango.message.service;

import com.kh.mango.message.domain.Message;


import java.util.List;

public interface MessageService {


    List<Message> selectChatRoomList(int userNo);


    int deleteChatRoom(int chatRoomNo);

    Message selectUserByUserNo1(int userNo);

    int insertCreateToMsgSend(Message message);

    Message selectChatRoom(Message message);

    int insertNotCreateToMsgSend(Message message);

    List<Message> selectMessageList(int chatRoomNo);


    Message selectChatRoom2(Message message);
}
