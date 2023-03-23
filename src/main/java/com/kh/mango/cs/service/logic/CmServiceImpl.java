package com.kh.mango.cs.service.logic;


import com.kh.mango.cs.domain.Comment;
import com.kh.mango.cs.service.CmService;
import com.kh.mango.cs.store.CmStore;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CmServiceImpl implements CmService {
    @Autowired
    private CmStore cmStore;
    @Autowired
    private SqlSession session;

    @Override
    public int insertComment(Comment comment){
        return cmStore.insertComment(session, comment);
    }

    @Override
    public int deleteComment(int commentNo) {
        return cmStore.deleteComment(session, commentNo);
    }

    @Override
    public List<Comment> selectCommentList(int csNo) {
        return cmStore.insertComment(session, csNo);
    }



}
