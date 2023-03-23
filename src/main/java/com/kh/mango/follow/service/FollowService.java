package com.kh.mango.follow.service;

import com.kh.mango.follow.domain.Follow;
import com.kh.mango.follow.domain.FollowYn;
import com.kh.mango.follow.domain.SearchUser;
import com.kh.mango.user.domain.User;

import java.util.List;

public interface FollowService {

    int followUser(Follow followUser);


    List<Follow> followingUser(int userNo);


    List<FollowYn> searchUser(SearchUser searchUser);
}
