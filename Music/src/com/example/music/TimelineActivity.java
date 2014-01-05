package com.example.music;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
 
public class TimelineActivity extends Fragment {
	ListView lv1;
	public static ArrayAdapter<Song> files;
	public static List<Song> values;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.activity_timeline, container, false);
        lv1 = (ListView) rootView.findViewById(R.id.listview);
        
//        String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
//                "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
//           "Linux", "OS/2" };
        
        values = MainActivity.datasource.getAllSongs();

           files = new ArrayAdapter<Song>(getActivity(), 
                    android.R.layout.simple_list_item_1, 
                    values);

            lv1.setAdapter(files);
          lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             public void onItemClick(AdapterView<?> av, View view, int i, long l) {
                 //Toast.makeText(getActivity(), "mysong "+values.get(i).getSong_name(), Toast.LENGTH_LONG).show();
                 //long id = values.get(i).getId();
                 Intent intent = new Intent(getActivity(), YoutubeActivity.class);
                 intent.putExtra("song", values.get(i).getSong_name());
                 intent.putExtra("artist", values.get(i).getArtist_name());
                 startActivity(intent);
             }
         });
        return rootView;
    }
 
}
