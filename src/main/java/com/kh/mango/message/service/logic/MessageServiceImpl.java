package com.kh.mango.message.service.logic;

import com.kh.mango.message.domain.Message;
import com.kh.mango.message.service.MessageService;
import com.kh.mango.message.store.MessageStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageStore mStore;


    @Override
    public List<Message> selectChatRoomList(int userNo) {
        return mStore.selectChatRoomList(userNo);
    }

    @Override
    public int deleteChatRoom(int chatRoomNo) {
        return mStore.deleteChatRoom(chatRoomNo);
    }

    @Override
    public Message selectUserByUserNo1(int userNo) {
        return mStore.selectUserByUserNo1(userNo);
    }

    // 채팅방 있는지 체크
    @Override
    public Message selectChatRoom(Message message) {
        return mStore.selectChatRoom(message);
    }


    // 채팅방이 없다면 생성 후 메시지 발송
    @Override
    public int insertCreateToMsgSend(Message message) {
        int result = mStore.insertChatRoomUser(message);
        if(result > 0){
            result = mStore.insertChatRoom(message);
            if(result > 0){
                return mStore.insertSendMsg(message);
            }
        }
        return 0;
    }


    // 채팅방이 있다면 그대로 발송
    @Override
    public int insertNotCreateToMsgSend(Message message) {
        return mStore.insertSendMsg(message);
    }

    @Override
    public List<Message> selectMessageList(int chatRoomNo) {
        return mStore.selectMessageList(chatRoomNo);
    }

    @Override
    public Message selectChatRoom2(Message message) {
        return mStore.selectChatRoom2(message);
    }


}
