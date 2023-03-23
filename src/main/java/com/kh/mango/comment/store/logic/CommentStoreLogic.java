package com.kh.mango.comment.store.logic;

import com.kh.mango.comment.domain.Comment;
import com.kh.mango.comment.store.CommnetStore;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CommentStoreLogic implements CommnetStore {
    @Autowired
    private SqlSession session;

    @Override
    public int insertCommentWrite(Comment comment) {
        return session.insert("CommentMapper.insertCommentByTrade",comment);
    }

    @Override
    public int deleteByCommentNo(int commentNo) {
        return session.delete("CommentMapper.deleteComment",commentNo);
    }
}
