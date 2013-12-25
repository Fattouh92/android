package com.example.music;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

  public static final String TABLE_SONGS = "songs";
  public static final String COLUMN_ID = "_id";
  public static final String SONG_NAME = "song";
  public static final String ARTIST_NAME = "artist";
  public static final String ALBUM_NAME = "album";

  private static final String DATABASE_NAME = "songs.db";
  private static final int DATABASE_VERSION = 1;

  // Database creation sql statement
  private static final String DATABASE_CREATE = "create table "
      + TABLE_SONGS + "(" + COLUMN_ID
      + " integer primary key autoincrement, " + SONG_NAME
      + " text not null, "  + ARTIST_NAME
      + " text not null, " + ALBUM_NAME
      + " text not null);";

  public MySQLiteHelper(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase database) {
    database.execSQL(DATABASE_CREATE);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    Log.w(MySQLiteHelper.class.getName(),
        "Upgrading database from version " + oldVersion + " to "
            + newVersion + ", which will destroy all old data");
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_SONGS);
    onCreate(db);
  }

} 