package com.kh.mango.message.service;

import com.kh.mango.message.domain.Message;


import java.util.List;

public interface MessageService {


    List<Message> selectChatRoomList(int userNo);


    int deleteChatRoom(Message message);

    Message selectUserByUserNo1(int userNo);

    int insertCreateToMsgSend(Message message);

    Message selectChatRoom(Message message);

    int insertNotCreateToMsgSend(Message message);
}
