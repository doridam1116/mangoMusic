package com.kh.mango.comment.service.logic;

import com.kh.mango.comment.domain.Comment;
import com.kh.mango.comment.service.CommentService;
import com.kh.mango.comment.store.CommnetStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommnetStore cmStore;

    @Override
    public int insertCommentWrite(Comment comment) {
        return cmStore.insertCommentWrite(comment);
    }

    @Override
    public int deleteByCommentNo(int commentNo) {
        return cmStore.deleteByCommentNo(commentNo);
    }
}
