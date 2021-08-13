package com.google;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class VideoPlayer {
  public static Video playingVideoTitle=null;
  public static String playing="not playing";
  public static String videoStatus="";
  public static Video holdPlayingVideo;
  private final VideoLibrary videoLibrary;
  public static VideoPlaylist playlist;

  public VideoPlayer() {
    this.videoLibrary = new VideoLibrary();

  }


  public void numberOfVideos() {
    System.out.printf("%s videos in the library%n", videoLibrary.getVideos().size());
  }

  public void showAllVideos() {
    System.out.println("Here's a list of all available videos:");
    int librarySize =videoLibrary.getVideos().size();
    /*String firstId=(videoLibrary.getVideos().get(0)).getVideoId();
    String secondId=(videoLibrary.getVideos().get(0+1)).getVideoId();
    System.out.println(firstId.compareTo(secondId));
    */
    ArrayList <String> arrayOne=new <String> ArrayList();

    for (    int loop=0; loop<librarySize; loop++)
    {
      arrayOne.add((videoLibrary.getVideos().get(loop)).getVideoId());

      //System.out.println((videoLibrary.getVideos().get(loop)).getTitle()+" ("+(videoLibrary.getVideos().get(loop)).getVideoId()+") "+(videoLibrary.getVideos().get(loop)).getTags());
    }
    Collections.sort(arrayOne);
    for (    int loop=0; loop<librarySize; loop++)
    {
      List<String> tags = videoLibrary.getVideo(arrayOne.get(loop)).getTags();

      String results = tags.stream().map(n -> String.valueOf(n)).collect(Collectors.joining(" "));
      results = "[" + results + "]";
      System.out.println(videoLibrary.getVideo(arrayOne.get(loop)).getTitle()+" ("+videoLibrary.getVideo(arrayOne.get(loop)).getVideoId()+") "+results);
    }

    //System.out.println((videoLibrary.getVideos().get(0)).getTitle());
    //System.out.println(videoLibrary.getVideo("amazing_cats_video_id"));

  }

  public void playVideo(String videoId) {

    playingVideoTitle= videoLibrary.getVideo(videoId);
    videoStatus="";

    if(playingVideoTitle!=null) {

      if (playing=="playing")
      {stopVideoToPlayNewVideo();
      }
      else {
        System.out.printf("Playing video: %s%n", videoLibrary.getVideo(videoId).getTitle());
        playing ="not playing";
        holdPlayingVideo=playingVideoTitle;
      }
    }
    else
      {
        playing = "not playing";
        System.out.printf("Cannot play video: Video does not exist");
      }

  }
  //public void stopVideo(String videoId) {System.out.println(videoId);}

  public void stopVideo() {
    if (playingVideoTitle!=null)
    {
      System.out.println("Stopping video: "+playingVideoTitle.getTitle());
      playingVideoTitle=null;
     /* if (holdPlayingVideo==playingVideoTitle){
        System.out.println("Stopping video: "+playingVideoTitle.getTitle());
        playingVideoTitle=null;
      }*/
    }
    else
    {
      System.out.println("Cannot stop video: No video is currently playing");
    }
    playing="notplaying";
  }
  public void stopVideoToPlayNewVideo() {
    System.out.println("Stopping video: "+holdPlayingVideo.getTitle());
    playing="notplaying";
    playVideo(playingVideoTitle.getVideoId());

  }

  public void playRandomVideo() {

    Random random=new Random();

    int select = random.nextInt(4-0);
    String randomTitle=videoLibrary.getVideos().get(select).getVideoId();


    System.out.println(    videoLibrary.getVideo(randomTitle).getTitle());
  }



  public void pauseVideo() {
    if (playingVideoTitle != null) {
      if (videoStatus == " - PAUSED") {
        System.out.printf("Video already paused: %s%n", playingVideoTitle.getTitle());

      } else {
        System.out.printf("Pausing video: %s%n", playingVideoTitle.getTitle());
        videoStatus = " - PAUSED";
      }
    }else
    {
      System.out.printf("Cannot pause video: No video is currently playing");
    }
  }


  public void continueVideo() {
    if (playingVideoTitle!=null) {
      if (videoStatus == " - PAUSED") {
        System.out.printf("Continuing video: %s%n", playingVideoTitle.getTitle());
        videoStatus = "";
      } else {
        System.out.println("Cannot continue video: Video is not paused");
      }
    }else
    {System.out.println("Cannot continue video: No video is currently playing");}
  }

  public void showPlaying() {
    if (playingVideoTitle!=null) {
      List<String> tags = playingVideoTitle.getTags();
      String results = tags.stream().map(n -> String.valueOf(n)).collect(Collectors.joining(" "));
      results = "[" + results + "]";
      System.out.printf("Currently playing: %s%n", playingVideoTitle.getTitle() + " (" + playingVideoTitle.getVideoId() + ") " + results+videoStatus);
    }
    else{        System.out.println("No video is currently playing");
    }
  }
  public void createPlaylist(String playlistName)
    {
      //VideoPlaylist checkplaylist=videoLibrary.getplaylist(playlistName);

    videoLibrary.setplaylistname(playlistName);
    //System.out.printf("Successfully created new playlist: %s%n",videoLibrary.getplaylist(playlistName).getPlaylistName());


  }

  public void addVideoToPlaylist(String playlistName, String videoId) {
    if (videoLibrary.getplaylist(playlistName) != null) {
      if (videoLibrary.getVideo(videoId) != null) {
        videoLibrary.addToPlaylist(playlistName, videoId);
        }
      else
        {
        System.out.println("Cannot add video to " + playlistName + ": Video does not exist");
        }
    }
    else
    {
      System.out.println("Cannot add video to " + playlistName + ": Playlist does not exist");
    }
  }

  public void showAllPlaylists() {
    int numberOfPlaylists = videoLibrary.getPlaylists().size();
    if (numberOfPlaylists!=0) {
      System.out.println("Showing all playlists:");
      ArrayList<String> arrayOne = new <String>ArrayList();
      for (int loop = 0; loop < numberOfPlaylists; loop++) {
        arrayOne.add((videoLibrary.getPlaylists().get(loop)).getPlaylistName());
        System.out.println(videoLibrary.getplaylist(arrayOne.get(loop)).getPlaylistName());
      }
    }
    else
    {
      System.out.println("No playlists exist yet");
    }
  }


  public void showPlaylist(String playlistName) {
    if(videoLibrary.getplaylist(playlistName)!=null) {
      System.out.printf("Showing playlist: %s%n", playlistName);
      String videoId = videoLibrary.showPlaylist(playlistName);
      videoId = videoId.replaceAll("[\\p{Ps}\\p{Pe}]", "");
      //System.out.println("Showing playlist: xxxxxxx"+videoId+"xxxxxx");
      //videoId=videoId.replaceAll("\\[]","");
      //String videoId ="amazing_cats_video_id";
      if (videoId != "No videos here yet") {
        List<String> tags = videoLibrary.getVideo(videoId).getTags();
        String results = tags.stream().map(n -> String.valueOf(n)).collect(Collectors.joining(" "));
        results = "[" + results + "]";
        System.out.printf("Currently playing: %s%n", videoLibrary.getVideo(videoId).getTitle() + " (" + videoId + ") " + results);
      } else {
        System.out.println("No videos here yet");
      }
    }
    else
    {
      System.out.println("Cannot show playlist my_playlist: Playlist does not exist");
    }
  }

  public void removeFromPlaylist(String playlistName, String videoId) {

    Boolean removalStatus=videoLibrary.removeFromPlaylist(playlistName,videoId);
    if (removalStatus==true)
    {

    }
    else
    {

    }
    System.out.println("removeFromPlaylist needs implementation");
  }

  public void clearPlaylist(String playlistName) {
    System.out.println("clearPlaylist needs implementation");
  }

  public void deletePlaylist(String playlistName) {
    System.out.println("deletePlaylist needs implementation");
  }

  public void searchVideos(String searchTerm) {
    System.out.println("searchVideos needs implementation");
  }

  public void searchVideosWithTag(String videoTag) {
    System.out.println("searchVideosWithTag needs implementation");
  }

  public void flagVideo(String videoId) {
    System.out.println("flagVideo needs implementation");
  }

  public void flagVideo(String videoId, String reason) {
    System.out.println("flagVideo needs implementation");
  }

  public void allowVideo(String videoId) {
    System.out.println("allowVideo needs implementation");
  }
}