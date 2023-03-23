package com.kh.mango.message.store;

import com.kh.mango.message.domain.Message;

import java.util.List;

public interface MessageStore {

    List<Message> selectChatRoomList(int userNo);


    int insertChatRoom(Message message);

    int insertChatRoomUser(Message message);


    int deleteChatRoom(int chatRoomNo);

    Message selectUserByUserNo1(int userNo);

    int insertSendMsg(Message message);

    Message selectChatRoom(Message message);

    List<Message> selectMessageList(int chatRoomNo);

    Message selectChatRoom2(Message message);
}
