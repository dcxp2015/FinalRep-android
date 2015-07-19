package com.dcxp.tone;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 7/18/2015.
 */
public class GlobalNavbarFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflatedView = inflater.inflate(R.layout.activity_navigation_test, container, false);

        List<String> list = new ArrayList<String>();
        list.add("Explore");
        list.add("My Playlists");
        list.add("My Phrases");
        list.add("Start Workout");

        ListView listView = (ListView) inflatedView.findViewById(R.id.lv_nav);
        listView.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, list));

        return inflatedView;
    }

}
