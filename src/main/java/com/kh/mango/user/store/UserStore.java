package com.kh.mango.user.store;

import com.kh.mango.point.domain.PointRecord;
import com.kh.mango.user.domain.*;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public interface UserStore {



    MyPage selectMyPageUser(int user);

    // 회원가입
    int insertUser(SqlSession session, User user);

    //    로그인
    User checkUserLogin(SqlSession session, User user);

    User findUserId(SqlSession session, User user);

    User findUserPw(SqlSession session, User user);

    List<User> selectMember(SqlSession session);


    List<User> searchUser(String searchValue);

    User selectOneByNumber(int userNo);
    List<MyPageFollow> selectMyPageFollower(int userNo);

    List<MyPageDeals> selectMyPageDeals(int userNo);

    List<Like> selectMyPageLikes(int userNo);

    List<PointRecord> selectPointRecord(int userNo);

    int addAllPoint();

    int updateUserPw(SqlSession session, User uParam);

    User selectUserPw(SqlSession session, User uParam);

    List<UserSearch> selectUserList(SqlSession session, String userId);
    int getListCount(SqlSession session);

    List<UserSearch> selectUserList(SqlSession session, int userNo);

    User updateUserProfile(User user);

    int updateUserProfilePic(User user);
}
