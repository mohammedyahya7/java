package com.google;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;
/**
 *
 * A class used to represent a Video Library.
 */
class VideoLibrary {

  private final HashMap<String, Video>  videos;
  private final HashMap<String, VideoPlaylist>  playlist;



  VideoLibrary() {
    this.videos = new HashMap<>();
    this.playlist=new HashMap<>();
    try {
      File file = new File(this.getClass().getResource("/videos.txt").getFile());

      Scanner scanner = new Scanner(file);
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        String[] split = line.split("\\|");
        String title = split[0].strip();
        String id = split[1].strip();
        List<String> tags;
        if (split.length > 2) {
          tags = Arrays.stream(split[2].split(",")).map(String::strip).collect(
              Collectors.toList());
        } else {
          tags = new ArrayList<>();
        }
        this.videos.put(id, new Video(title, id, tags));
      }
    } catch (FileNotFoundException e) {
      System.out.println("Couldn't find videos.txt");
      e.printStackTrace();
    }

  }

  public void setplaylistname(String newName) {
    if (getplaylist(newName) == null)
    {
      playlist.put(newName, new VideoPlaylist(newName));
      System.out.printf("Successfully created new playlist: %s%n",getplaylist(newName).getPlaylistName());
      //return this.playlist.put(newName, new VideoPlaylist(newName));
    }
    else
    {
      System.out.println("Cannot create playlist: A playlist with the same name already exists");
    }
  }

  public void addToPlaylist(String playlistName, String videoId){


    List<String> videoList=new ArrayList<String>();
    //videoList.add(videoId);
    //videoList= Collections.singletonList(videoId);
    //=new ArrayList<>();
    //videoList.add(videoId);
    String newVideoID="["+videoId+"]";
    String videoFromPlaylist=showPlaylist(playlistName);
    if (!videoFromPlaylist.equals("No videos here yet")) {
      videoList.add(videoFromPlaylist);
    }
      if (!videoFromPlaylist.equals(newVideoID)) {
        videoList.add(videoId);
        playlist.put(playlistName, new VideoPlaylist(playlistName, videoList));
        System.out.printf("Added video to %s", getplaylist(playlistName).getPlaylistName());
        List<String> videosFromPlaylist = playlist.get(playlistName).getListOfVideos();
        //System.out.println("zzzzzzzzzz"+videoFromPlaylist+"zzzzzzzzzzzzzzzzz");
        String results = videosFromPlaylist.stream().map(n -> String.valueOf(n)).collect(Collectors.joining(" "));
        //results = "[" + results + "]";
        results=results.replaceAll("[\\p{Ps}\\p{Pe}]", "");


        /*results = Arrays.stream(split[2].split(",")).map(String::strip).collect(
                Collectors.toList());*/
        System.out.println(videosFromPlaylist+"zz");
       // System.out.printf(": %s%n", getVideo(results).getTitle());

      } else {
        System.out.printf("Cannot add video to %s", getplaylist(playlistName).getPlaylistName());
        System.out.println(": Video already added ");
      }

    }


  public Boolean removeFromPlaylist(String playlistName, String videoId)
  {
    List<String> x=playlist.get(playlistName).getListOfVideos();
    System.out.println(x);
    return true;
  }

  public String showPlaylist(String playlistname){
    if (playlist.get(playlistname).getListOfVideos()!=null)

    {
      return String.valueOf(playlist.get(playlistname).getListOfVideos());
    }
    else
    {
      return "No videos here yet";
    }
  }


  List<Video> getVideos() {
    return new ArrayList<>(this.videos.values());
  }

  VideoPlaylist getplaylist(String playlistName) {
    return this.playlist.get(playlistName);
  }


  List<VideoPlaylist> getPlaylists()
  {
    return new ArrayList<> (this.playlist.values());
  }
  /*
int getPlaylistSize()
{
  return }*/

  /**
   * Get a video by id. Returns null if the video is not found.
   */
  Video getVideo(String videoId) {
    return this.videos.get(videoId);
  }
}
