package com.example.music;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
 
public class ThirdActivity extends Fragment {
        ListView lv3;
        ListView lv4;
        static ArrayAdapter<String> files;
		public static List<String> values;
		public static List<String> values2;
		public static ArrayAdapter<String> files2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
            View rootView = inflater.inflate(R.layout.activity_third, container, false);
        lv3 = (ListView) rootView.findViewById(R.id.listview3);
        
         values = MainActivity.datasource.getFavoriteArtist();

           files = new ArrayAdapter<String>(getActivity(), 
                    android.R.layout.simple_list_item_1, 
                    values);

            lv3.setAdapter(files);
            lv4 = (ListView) rootView.findViewById(R.id.listview4);
            
             values2 = MainActivity.datasource.getFavoriteAlbums();

                files2 = new ArrayAdapter<String>(getActivity(), 
                        android.R.layout.simple_list_item_1, 
                        values2);

                lv4.setAdapter(files2);
        return rootView;
    }
    public static ArrayList<String> searchAndDisplay(ArrayList<String> arr) {

		ArrayList<String> list1 = new ArrayList();
		ArrayList<Integer> list2 = new ArrayList();
		ArrayList<String> list3 = new ArrayList();
		for (int i = 0; i < arr.size(); i++) {
			if (list1.contains(arr.get(i))) {
				int count = list2.get(list1.indexOf(arr.get(i)));
				list2.set(list1.indexOf(arr.get(i)),
						count + 1);
			} else {
				list1.add(arr.get(i));
				list2.add(1);
			}
		}

		while (list3.size() < 5 && list3.size() <= list2.size()) {
			if (!list2.isEmpty()) {

				int maxCount = 0;
				int index = -1;
				for (int i = 0; i < list2.size(); i++) {
					if (maxCount < list2.get(i)) {
						maxCount = list2.get(i);
						index = i;
					}
				}
				if (index != -1) {
					list3.add(list1.get(index));
					list1.remove(index);
					list2.remove(index);
				}
			}
		}
		return list3;
	}}