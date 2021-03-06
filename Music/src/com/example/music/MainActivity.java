package com.example.music;

import java.util.HashSet;
import java.util.Set;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements
ActionBar.TabListener {

	public static SongsDataSource datasource;
	private ViewPager viewPager;
	private TabsPagerAdapter mAdapter;
	private ActionBar actionBar;
	// Tab titles
	private String[] tabs = { "Timeline", "Most Played", "Stats" };
	public static final String SERVICECMD = "com.android.music.musicservicecommand";
	public static final String CMDNAME = "command";
	public static final String CMDTOGGLEPAUSE = "togglepause";
	public static final String CMDSTOP = "stop";
	public static final String CMDPAUSE = "pause";
	public static final String CMDPREVIOUS = "previous";
	public static final String CMDNEXT = "next";
	public static String lastSong = "";
	public static final String PREFS_NAME = "MyPrefsFile";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		lastSong = settings.getString("storedString", "");
		//Toast.makeText(getApplicationContext(), "last song: "+lastSong,Toast.LENGTH_LONG).show();
		setContentView(R.layout.activity_main);
		IntentFilter iF = new IntentFilter();


		iF.addCategory("ComponentInfo");
		iF.addCategory("com.spotify.mobile.android.service.SpotifyIntentService");
		iF.addCategory("com.spotify.mobile.android.service.SpotifyService");


		iF.addAction("com.spotify.mobile.android.ui.widget.SpotifyWidget");
		iF.addAction("ComponentInfo");
		iF.addAction("com.spotify");
		iF.addAction("com.spotify.mobile.android.service.SpotifyIntentService");
		iF.addAction("com.spotify.mobile.android.service.SpotifyService");


		iF.addAction("com.android.music.metachanged");
		iF.addAction("com.android.music.playstatechanged");
		iF.addAction("com.android.music.playbackcomplete");
		iF.addAction("com.android.music.queuechanged");
		iF.addAction("com.spotify.mobile.android.ui");

		registerReceiver(mReceiver, iF);

		// albumtext = (TextView) findViewById(R.id.albumText);
		datasource = new SongsDataSource(this);
		datasource.open();


		// Initilization
		viewPager = (ViewPager) findViewById(R.id.pager);
		actionBar = getActionBar();
		mAdapter = new TabsPagerAdapter(getSupportFragmentManager());

		viewPager.setAdapter(mAdapter);
		actionBar.setHomeButtonEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);        

		// Adding Tabs
		for (String tab_name : tabs) {
			actionBar.addTab(actionBar.newTab().setText(tab_name)
					.setTabListener(this));
		}

		/**
		 * on swiping the viewpager make respective tab selected
		 * */
		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				// on changing the page
				// make respected tab selected
				actionBar.setSelectedNavigationItem(position);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
	}

	private BroadcastReceiver mReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent)
		{
			//String action = intent.getAction();
			//String cmd = intent.getStringExtra("command");

			//String com = intent.getStringExtra("ComponentInfo");

			//Log.d("mIntentReceiver.onReceive ", action + " / " + cmd+ " / "+ com);

			String artist = intent.getStringExtra("artist");
			String album = intent.getStringExtra("album");
			String track = intent.getStringExtra("track");
			//Log.d("Music",artist+":"+album+":"+track);
			//Toast.makeText(context, "Artist : "+artist+" Album :"+album+" Track : "+track+" " , Toast.LENGTH_SHORT).show();
			try{
				if (!lastSong.equals(track)) {
					datasource.createSong(track, artist, album);
					TimelineActivity.values.clear(); 
					TimelineActivity.values.addAll(datasource.getAllSongs()); 
					TimelineActivity.files.notifyDataSetChanged();
					lastSong = track;
				}
			} catch (Exception e) {
				Toast.makeText(context, "Cannot save this song" , Toast.LENGTH_SHORT).show();
			}
		}
	};

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// on tab selected
		// show respected fragment view
		viewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
	}

	@Override
	protected void onResume() {
		datasource.open();
		super.onResume();
		try{ 
			TimelineActivity.values.clear(); 
			TimelineActivity.values.addAll(datasource.getAllSongs()); 
			TimelineActivity.files.notifyDataSetChanged(); 
			/*MostPlayedActivity.values.clear(); 
			MostPlayedActivity.values.addAll(datasource.getMostPlayedSongs()); 
			MostPlayedActivity.files.notifyDataSetChanged(); 
			ThirdActivity.values.clear(); 
			ThirdActivity.values2.clear(); 
			ThirdActivity.values.addAll(datasource.getFavoriteArtist()); 
			ThirdActivity.values2.addAll(datasource.getFavoriteAlbums()); 
			ThirdActivity.files.notifyDataSetChanged(); 
			ThirdActivity.files2.notifyDataSetChanged();*/
		} catch (Exception e) { 
			Toast.makeText(getApplicationContext(), "error",Toast.LENGTH_LONG).show(); }
	}

	protected void onStop(){
		super.onStop();

		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		SharedPreferences.Editor editor = settings.edit();
		editor.putString("storedString", lastSong); // value to store
		editor.commit();
	}

	/*@Override
    protected void onPause() {
      datasource.close();
      super.onPause();
    }*/
}