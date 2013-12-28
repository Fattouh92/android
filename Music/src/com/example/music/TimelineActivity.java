package com.example.music;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
 
public class TimelineActivity extends Fragment {
	ListView lv1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.activity_timeline, container, false);
        lv1 = (ListView) rootView.findViewById(R.id.listview);
        
//        String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
//                "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
//           "Linux", "OS/2" };
        
        List<Song> values = MainActivity.datasource.getAllSongs();

           ArrayAdapter<Song> files = new ArrayAdapter<Song>(getActivity(), 
                    android.R.layout.simple_list_item_1, 
                    values);

            lv1.setAdapter(files);
         
        return rootView;
    }
 
}
