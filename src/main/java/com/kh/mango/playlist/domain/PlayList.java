package com.kh.mango.playlist.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlayList {

    private int userNo;
    private String playlistSong;
    private String playlistArtist;

}
