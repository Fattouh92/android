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
 
public class MostPlayedActivity extends Fragment {
        ListView lv2;
        static ArrayAdapter<String> files;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
            View rootView = inflater.inflate(R.layout.activity_most_played, container, false);
        lv2 = (ListView) rootView.findViewById(R.id.listview2);
        
        List<String> values = MainActivity.datasource.getMostPlayedSongs();

           files = new ArrayAdapter<String>(getActivity(), 
                    android.R.layout.simple_list_item_1, 
                    values);
System.out.println(files.getItem(0));
            lv2.setAdapter(files);
         
        return rootView;
    }
    public static List<String> searchAndDisplay(ArrayList<String> arr) {

        ArrayList<String> list1 = new ArrayList();
        ArrayList<Integer> list2 = new ArrayList();
        ArrayList<String> list3 = new ArrayList();
        for (int i = 0; i < arr.size(); i++) {
            int index = list1.indexOf(arr.get(i));
            if (index != -1) {
                    int newCount = list2.get(index)+1;
              list2.set(index,newCount);
            } else {
                       list1.add(arr.get(i));
                   list2.set(list1.size()-1, 1);
            }
        }
        while(list3.size()<25 && list3.size()<= list2.size()){
                if(!list2.isEmpty()){
                        
        int maxCount = 0;
        int index = -1;
        for (int i = 0; i < list2.size(); i++) {
            if (maxCount < list2.get(i)) {
                maxCount = list2.get(i);
                index = i;
            }
        }
        if(index != -1){
        list3.add(list1.get(index));
        list1.remove(index);
        list2.remove(index);
        }
        }}
 return list3;
}}