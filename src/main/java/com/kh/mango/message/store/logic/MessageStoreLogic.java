package com.kh.mango.message.store.logic;

import com.kh.mango.message.domain.Message;
import com.kh.mango.message.store.MessageStore;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MessageStoreLogic implements MessageStore {
        @Autowired
    private SqlSession session;






    // 채팅방 리스트 로딩
    @Override
    public List<Message> selectChatRoomList(int userNo) {
        return session.selectList("MessageMapper.selectChatRoomList",userNo);
    }



    // 채팅방 삭제
    @Override
    public int deleteChatRoom(int chatRoomNo) {
        return session.delete("MessageMapper.deleteChatRoom", chatRoomNo);
    }

    @Override
    public Message selectUserByUserNo1(int userNo) {
        return session.selectOne("MessageMapper.selectUserByUserNo1",userNo);
    }

    // 유저 정보가 있는 채팅방 생성
    @Override
    public int insertChatRoomUser(Message message) {
        return session.insert("MessageMapper.insertChatRoomUser",message);
    }
    // 위 메소드를 기반으로 채팅방 정보 입력
    @Override
    public int insertChatRoom(Message message) {
        return session.insert("MessageMapper.insertChatRoom",message);
    }
    // 채팅방 정보를 가지고 메시지 발송
    @Override
    public int insertSendMsg(Message message) {
        return session.insert("MessageMapper.insertSendMsg", message);
    }

    // 채팅방이 있는지 체크
    @Override
    public Message selectChatRoom(Message message) {
        return session.selectOne("MessageMapper.selectChatRoom",message);
    }

    @Override
    public List<Message> selectMessageList(int chatRoomNo) {
        return session.selectList("MessageMapper.selectMessageList",chatRoomNo);
    }

    @Override
    public Message selectChatRoom2(Message message) {
        return session.selectOne("MessageMapper.selectChatRoom2",message);
    }



}
