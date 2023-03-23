package com.kh.mango.user.service;

import com.kh.mango.point.domain.PointRecord;
import com.kh.mango.user.domain.*;

import java.util.List;

public interface UserService {



    List<User> selectMember();

    MyPage myPageInfo(int userNo);

    int insertUser(User user);

    User checkUserLogin(User user);

    User findUserId(User user);

    User findUserPw(User user);

    List<User> searchUser(String searchValue);

    User selectOneByNumber(int userNo);

    List<MyPageFollow> myPageFollow(int userNo);

    List<MyPageDeals> myPageDeals(int userNo);

    List<Like> myPageLikes(int userNo);

    List<PointRecord> selectPointRecord(int userNo);


    int addAllPoint();

    int updateUserPw(User uParam);

    User selectUserPw(User uParam);

    int getListCount();
    List<UserSearch> selectUserList(String userId);

    List<UserSearch> selectUserList(int userNo);

    User updateUserProfile(User user);

    int updateUserProfilePic(User user);
}
