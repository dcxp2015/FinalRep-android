package com.dcxp.tone.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.dcxp.tone.R;
import com.dcxp.tone.activities.MainActivity;

/**
 * Created by Daniel on 7/18/2015.
 */
public class SelectWorkoutFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflatedView = inflater.inflate(R.layout.fragment_select_workout, container, false);
        final Context context = getActivity();

        final String[] workouts = new String[25];

        for(int i = 0; i < workouts.length; i++) {
            workouts[i] = "workout " + i;
        }

        ListView listView = (ListView) inflatedView.findViewById(R.id.lv_workouts);
        listView.setAdapter(new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, workouts));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Switch to the playlists fragment
                Bundle args = new Bundle();
                args.putString("workout", workouts[position]);
                ((MainActivity) getActivity()).setContent(PlaylistsFragment.class, args);
            }
        });

        return inflatedView;
    }
}
