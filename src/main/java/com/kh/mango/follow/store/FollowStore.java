package com.kh.mango.follow.store;

import com.kh.mango.follow.domain.Follow;
import com.kh.mango.follow.domain.FollowYn;
import com.kh.mango.follow.domain.SearchUser;
import com.kh.mango.user.domain.User;

import java.util.List;

public interface FollowStore {

    int followUser(Follow followUser);


    List<Follow> followingUser(int userNo);

    List<FollowYn> searchUser(SearchUser searchUser);
}
