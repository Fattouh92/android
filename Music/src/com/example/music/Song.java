package com.example.music;

public class Song {
  private long id;
  private String song_name;
  private String artist_name;
  private String album_name;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }
  
  public String getSong_name() {
	return song_name;
  }
	
	public void setSong_name(String song_name) {
		this.song_name = song_name;
	}
	
	public String getArtist_name() {
		return artist_name;
	}
	
	public void setArtist_name(String artist_name) {
		this.artist_name = artist_name;
	}
	
	public String getAlbum_name() {
		return album_name;
	}
	
	public void setAlbum_name(String album_name) {
		this.album_name = album_name;
	}

// Will be used by the ArrayAdapter in the ListView
  @Override
  public String toString() {
    return "You Listened to " + song_name+" by "+artist_name+" from album: "+album_name;
  }
} 