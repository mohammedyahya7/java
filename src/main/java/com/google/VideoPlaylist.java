package com.google;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/** A class used to represent a Playlist */
class VideoPlaylist {

    private final String playlistName;
    //private final String videoId ;
    private final List<String> listOfVideos;

   /* public VideoPlaylist(String playlistName, List<String> videoList) {

    }*/

    public VideoPlaylist(String playlistName, List<String> listOfVideos) {
        this.playlistName = playlistName;
        //this.videoId = videoId;
        this.listOfVideos = Collections.unmodifiableList(listOfVideos);
    }

    public VideoPlaylist(String playlistName) {
        this.playlistName = playlistName;
       // videoId = null;
        listOfVideos = null;

    }

    public String getPlaylistName() {
        return playlistName;
    }


    public List<String> getListOfVideos() {
        return listOfVideos;
    }
}
