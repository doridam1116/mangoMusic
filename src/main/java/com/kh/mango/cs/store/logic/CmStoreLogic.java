package com.kh.mango.cs.store.logic;


import com.kh.mango.cs.domain.Comment;
import com.kh.mango.cs.store.CmStore;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CmStoreLogic implements CmStore {


    @Override
    public int insertComment(SqlSession session, Comment comment) {
        return session.insert("CommentMapper.insertComment", comment);
    }

    @Override
    public int deleteComment(SqlSession session, int commentNo) {
        return session.delete("CommentMapper.deleteComment", commentNo);
    }

    @Override
    public List<Comment> insertComment(SqlSession session, int csNo) {
        return session.selectList("CommentMapper.selectCommentList", csNo);
    }
}
