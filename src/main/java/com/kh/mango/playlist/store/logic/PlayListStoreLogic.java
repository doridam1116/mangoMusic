package com.kh.mango.playlist.store.logic;

import com.kh.mango.playlist.domain.PlayList;
import com.kh.mango.playlist.store.PlayListStore;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PlayListStoreLogic implements PlayListStore {

    @Autowired
    private SqlSessionTemplate sqlSession;

    @Override
    public int addPlaylist(PlayList playlistParam) {
        return sqlSession.insert("PlaylistMapper.addPlaylist",playlistParam);
    }

    @Override
    public List<PlayList> showMyPlaylist(int userNo) {
        return sqlSession.selectList("PlaylistMapper.showMyPlaylist", userNo);
    }

    @Override
    public List<PlayList> showFollowPlaylist(int userNo) {
        return sqlSession.selectList("PlaylistMapper.showFollowPlaylist", userNo);
    }

    @Override
    public int deletePlaylist(PlayList playlistParam) {
        return sqlSession.delete("PlaylistMapper.deletePlaylist",playlistParam);
    }
}
