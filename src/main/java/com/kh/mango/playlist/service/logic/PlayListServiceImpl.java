package com.kh.mango.playlist.service.logic;

import com.kh.mango.playlist.domain.PlayList;
import com.kh.mango.playlist.service.PlayListService;
import com.kh.mango.playlist.store.PlayListStore;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayListServiceImpl implements PlayListService {

    @Autowired
    private PlayListStore playListStore;

    @Autowired
    private SqlSession session;


    @Override
    public int addPlaylist(PlayList playlistParam) {
        return playListStore.addPlaylist(playlistParam);
    }

    @Override
    public List<PlayList> showMyPlaylist(int userNo) {
        return playListStore.showMyPlaylist(userNo);
    }

    @Override
    public List<PlayList> showFollowPlaylist(int userNo) {
        return playListStore.showFollowPlaylist(userNo);
    }

    @Override
    public int deletePlaylist(PlayList playlistParam) {
        return playListStore.deletePlaylist(playlistParam);
    }
}
