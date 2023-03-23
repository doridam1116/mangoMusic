package com.kh.mango.follow.store.logic;

import com.kh.mango.follow.domain.Follow;
import com.kh.mango.follow.domain.FollowYn;
import com.kh.mango.follow.domain.SearchUser;
import com.kh.mango.follow.store.FollowStore;
import com.kh.mango.user.domain.User;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FollowStoreLogic implements FollowStore {

    @Autowired
    private SqlSessionTemplate sqlSession;


    @Override
    public int followUser(Follow followUser) {
        int result = sqlSession.insert("FollowMapper.insertFollowUser", followUser);
        return result;
    }

    @Override
    public List<Follow> followingUser(int userNo) {
        return sqlSession.selectList("FollowMapper.followingUserList", userNo);
    }

    @Override
    public List<FollowYn> searchUser(SearchUser searchUser) {
        return sqlSession.selectList("FollowMapper.searchUser", searchUser);
    }


}
