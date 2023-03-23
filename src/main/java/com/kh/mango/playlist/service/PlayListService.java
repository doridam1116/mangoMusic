package com.kh.mango.playlist.service;

import com.kh.mango.playlist.domain.PlayList;

import java.util.List;

public interface PlayListService {

    int addPlaylist(PlayList playlistParam);

    List<PlayList> showMyPlaylist(int userNo);

    List<PlayList> showFollowPlaylist(int userNo);

    int deletePlaylist(PlayList playlistParam);
}
