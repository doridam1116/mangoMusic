package com.kh.mango.comment.service;

import com.kh.mango.comment.domain.Comment;

public interface CommentService {
    int insertCommentWrite(Comment comment);

    int deleteByCommentNo(int commentNo);
}
