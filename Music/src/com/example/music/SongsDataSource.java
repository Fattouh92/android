package com.example.music;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class SongsDataSource {

  // Database fields
  private SQLiteDatabase database;
  private MySQLiteHelper dbHelper;
  private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
      MySQLiteHelper.SONG_NAME, MySQLiteHelper.ARTIST_NAME,
      MySQLiteHelper.ALBUM_NAME };

  public SongsDataSource(Context context) {
    dbHelper = new MySQLiteHelper(context);
  }

  public void open() throws SQLException {
    database = dbHelper.getWritableDatabase();
  }

  public void close() {
    dbHelper.close();
  }

  public Song createSong(String song_name, String artist_name, String album_name) {
    ContentValues values = new ContentValues();
    //if (song_name.equals(null) || album_name.equals(null) || artist_name.equals(null)) {
    	//return null;
   //}
    values.put(MySQLiteHelper.SONG_NAME, song_name);
    values.put(MySQLiteHelper.ARTIST_NAME, artist_name);
    values.put(MySQLiteHelper.ALBUM_NAME, album_name);
    long insertId = database.insert(MySQLiteHelper.TABLE_SONGS, null,
        values);
    Cursor cursor = database.query(MySQLiteHelper.TABLE_SONGS,
        allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
        null, null, null);
    cursor.moveToFirst();
    Song newSong = cursorToSong(cursor);
    cursor.close();
    return newSong;
  }

  public void deleteSong(Song song) {
    long id = song.getId();
    System.out.println("Song deleted with id: " + id);
    database.delete(MySQLiteHelper.TABLE_SONGS, MySQLiteHelper.COLUMN_ID
        + " = " + id, null);
  }

  public List<Song> getAllSongs() {
    List<Song> songs = new ArrayList<Song>();

    Cursor cursor = database.query(MySQLiteHelper.TABLE_SONGS,
        allColumns, null, null, null, null, null);

    cursor.moveToFirst();
    while (!cursor.isAfterLast()) {
      Song song = cursorToSong(cursor);
      songs.add(song);
      cursor.moveToNext();
    }
    // make sure to close the cursor
    cursor.close();
    return songs;
  }

  private Song cursorToSong(Cursor cursor) {
    Song song = new Song();
    song.setId(cursor.getLong(0));
    song.setSong_name(cursor.getString(1));
    song.setArtist_name(cursor.getString(2));
    song.setAlbum_name(cursor.getString(3));
    return song;
  }
  public List<String> getMostPlayedSongs() {
      ArrayList<String> allsongs = new ArrayList<String>();
      Cursor c = database.query( MySQLiteHelper.TABLE_SONGS, new String[]{MySQLiteHelper.SONG_NAME},null,null,null,null,
              MySQLiteHelper.SONG_NAME);

      allsongs.clear();
      if (c != null)
          c.moveToFirst();
      for (int i = 0; c.getCount() > i; i++) {

          String song1 = c.getString(0);

          allsongs.add(song1);
          c.moveToNext();

      }
      if(allsongs.isEmpty()){
    	  allsongs.add("No Songs");
    	  return allsongs;
      }
      return MostPlayedActivity.searchAndDisplay(allsongs);
      }
  
  public List<String> getFavoriteArtist() {
      ArrayList<String> allsongs = new ArrayList<String>();
      Cursor c = database.query( MySQLiteHelper.TABLE_SONGS, new String[]{MySQLiteHelper.ARTIST_NAME},null,null,null,null,
              MySQLiteHelper.ARTIST_NAME);

      allsongs.clear();
      if (c != null)
          c.moveToFirst();
      for (int i = 0; c.getCount() > i; i++) {

          String song1 = c.getString(0);

          allsongs.add(song1);
          c.moveToNext();

      }
      if(allsongs.isEmpty()){
    	  allsongs.add("No Artists");
    	  return allsongs;
      }
      return ThirdActivity.searchAndDisplay(allsongs);
      }
public List<String> getFavoriteAlbums() {
      ArrayList<String> allsongs = new ArrayList<String>();
      Cursor c = database.query( MySQLiteHelper.TABLE_SONGS, new String[]{MySQLiteHelper.ALBUM_NAME},null,null,null,null,
              MySQLiteHelper.ALBUM_NAME);

      allsongs.clear();
      if (c != null)
          c.moveToFirst();
      for (int i = 0; c.getCount() > i; i++) {

          String song1 = c.getString(0);

          allsongs.add(song1);
          c.moveToNext();

      }
      if(allsongs.isEmpty()){
    	  allsongs.add("No Albums");
    	  return allsongs;
      }
      return ThirdActivity.searchAndDisplay(allsongs);
      }
} 
