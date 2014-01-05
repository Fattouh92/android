package com.example.music;

import java.io.IOException;
import java.util.List;

import android.os.Bundle;
import android.content.Intent;
import android.view.Menu;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.android.youtube.player.YouTubePlayer.Provider;

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
//		 try {
//	            // This object is used to make YouTube Data API requests. The last
//	            // argument is required, but since we don't need anything
//	            // initialized when the HttpRequest is initialized, we override
//	            // the interface and provide a no-op function.
////	            youtube = new YouTube.Builder(Auth.HTTP_TRANSPORT, Auth.JSON_FACTORY, new HttpRequestInitializer() {
////	                public void initialize(HttpRequest request) throws IOException {
////	                }
////	            }).setApplicationName("youtube-cmdline-search-sample").build();
//
//	            // Prompt the user to enter a query term.
//	            String queryTerm = song+" "+artist;
//
//	            // Define the API request for retrieving search results.
//	            //YouTube.Search.List search = youtube.search().list("id,snippet");
//
//	            // Set your developer key from the Google Cloud Console for
//	            // non-authenticated requests. See:
//	            // https://cloud.google.com/console
//	            search.setKey(DEVELOPER_KEY);
//	            search.setQ(queryTerm);
//
//	            // Restrict the search results to only include videos. See:
//	            // https://developers.google.com/youtube/v3/docs/search/list#type
//	            search.setType("video");
//
//	            // To increase efficiency, only retrieve the fields that the
//	            // application uses.
//	            search.setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url)");
//	            search.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);
//
//	            // Call the API and print results.
//	            SearchListResponse searchResponse = search.execute();
//	            List<SearchResult> searchResultList = searchResponse.getItems();
//	            if (searchResultList != null) {
//	            	SearchResult singleVideo = searchResultList.iterator().next();
//	                ResourceId rId = singleVideo.getId();
//	                VIDEO = rId.getVideoId();
//	            }
//	        } catch (GoogleJsonResponseException e) {
//	            System.err.println("There was a service error: " + e.getDetails().getCode() + " : "
//	                    + e.getDetails().getMessage());
//	        } catch (IOException e) {
//	            System.err.println("There was an IO error: " + e.getCause() + " : " + e.getMessage());
//	        } catch (Throwable t) {
//	            t.printStackTrace();
//	        }
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
	
//	private static void prettyPrint(Iterator<SearchResult> iteratorSearchResults, String query) {
//
//        System.out.println("\n=============================================================");
//        System.out.println(
//                "   First " + NUMBER_OF_VIDEOS_RETURNED + " videos for search on \"" + query + "\".");
//        System.out.println("=============================================================\n");
//
//        if (!iteratorSearchResults.hasNext()) {
//            System.out.println(" There aren't any results for your query.");
//        }
//
//        while (iteratorSearchResults.hasNext()) {
//
//            SearchResult singleVideo = iteratorSearchResults.next();
//            ResourceId rId = singleVideo.getId();
//
//            // Confirm that the result represents a video. Otherwise, the
//            // item will not contain a video ID.
//            if (rId.getKind().equals("youtube#video")) {
//                Thumbnail thumbnail = singleVideo.getSnippet().getThumbnails().getDefault();
//
//                System.out.println(" Video Id" + rId.getVideoId());
//                System.out.println(" Title: " + singleVideo.getSnippet().getTitle());
//                System.out.println(" Thumbnail: " + thumbnail.getUrl());
//                System.out.println("\n-------------------------------------------------------------\n");
//            }
//        }
//    }

}
