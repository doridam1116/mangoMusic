package com.kh.mango.comment.store;

import com.kh.mango.comment.domain.Comment;

public interface CommnetStore {
    int insertCommentWrite(Comment comment);

    int deleteByCommentNo(int commentNo);
}
