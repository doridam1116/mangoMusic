package com.kh.mango.cs.store;


import com.kh.mango.cs.domain.Comment;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public interface CmStore {


    int insertComment(SqlSession session, Comment comment);

    int deleteComment(SqlSession session, int commentNo);

    List<Comment> insertComment(SqlSession session, int csNo);

}
