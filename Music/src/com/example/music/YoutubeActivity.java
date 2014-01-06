package com.example.music;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import android.os.Bundle;
import android.widget.Toast;

public class YoutubeActivity extends YouTubeBaseActivity implements
YouTubePlayer.OnInitializedListener {
        static private final String DEVELOPER_KEY = "AIzaSyB6bkhkb_fVRpUyTmLnbsQUzYmeVxm1ovg";
         static private final String VIDEO = "If5MF4wm1T8";
         
        @Override
        protected void onCreate(Bundle savedInstanceState) {
                Intent intent = getIntent();
                String song = intent.getStringExtra("song");
                String artist = intent.getStringExtra("artist");
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_youtube);
                YouTubePlayerView youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
                youTubeView.initialize(DEVELOPER_KEY, this);
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
                // Inflate the menu; this adds items to the action bar if it is present.
                getMenuInflater().inflate(R.menu.youtube, menu);
                return true;
        }

        @Override
        public void onInitializationFailure(Provider arg0,
                        YouTubeInitializationResult arg1) {
                 Toast.makeText(this, "Oh no! "+arg1.toString(), Toast.LENGTH_LONG).show();
                
        }

        @Override
        public void onInitializationSuccess(Provider arg0, YouTubePlayer arg1,
                        boolean arg2) {
                arg1.loadVideo(VIDEO);
                
        }
        

}