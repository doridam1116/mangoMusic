package com.kh.mango.user.service.logic;

import com.kh.mango.point.domain.PointRecord;
import com.kh.mango.user.domain.*;
import com.kh.mango.user.service.UserService;
import com.kh.mango.user.store.UserStore;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserStore uStore;
    @Autowired
    SqlSession session;


    @Override
    public int insertUser(User user) {
        int result = uStore.insertUser(session, user);
        return result;
    }

    @Override
    public User checkUserLogin(User user) {
        User uOne = uStore.checkUserLogin(session, user);
        return uOne;

    }

    @Override
    public User findUserId(User user) {
        User uOne = uStore.findUserId(session, user);
        return uOne;
    }

    @Override
    public User findUserPw(User user) { return uStore.findUserPw(session, user); }

    @Override
    public List<User> searchUser(String searchValue) {
        List<User> searchList = uStore.searchUser(searchValue);
        return searchList;
    }

    @Override
    public User selectOneByNumber(int userNo) {
        User user = uStore.selectOneByNumber(userNo);
        return user;
    }

    @Override
    public List<MyPageFollow> myPageFollow(int userNo) {
        return uStore.selectMyPageFollower(userNo);
    }

    @Override
    public List<MyPageDeals> myPageDeals(int userNo) {
        return uStore.selectMyPageDeals(userNo);
    }

    @Override
    public List<Like> myPageLikes(int userNo) {
        return uStore.selectMyPageLikes(userNo);
    }

    @Override
    public List<PointRecord> selectPointRecord(int userNo) {
        return uStore.selectPointRecord(userNo);
    }

    @Override
    public int addAllPoint() {
        return uStore.addAllPoint();
    }

    @Override
    public int updateUserPw(User uParam) {
        return uStore.updateUserPw(session,uParam);
    }

    @Override
    public User selectUserPw(User uParam) {
        return uStore.selectUserPw(session,uParam);
    }

    @Override
    public int getListCount() {
        return uStore.getListCount(session);
    }

    @Override
    public List<UserSearch> selectUserList(String userId) {
        return uStore.selectUserList(session,userId);
    }

    @Override
    public List<UserSearch> selectUserList(int userNo) {
        return uStore.selectUserList(session,userNo);
    }

    @Override
    public User updateUserProfile(User user) {
        return uStore.updateUserProfile(user);
    }

    @Override
    public int updateUserProfilePic(User user) {
        return uStore.updateUserProfilePic(user);
    }

    @Override
    public List<User> selectMember() {
        List<User> userList = uStore.selectMember(session);
        return userList;
    }

    @Override
    public MyPage myPageInfo(int userNo) {
        return uStore.selectMyPageUser(userNo);
    }

}
