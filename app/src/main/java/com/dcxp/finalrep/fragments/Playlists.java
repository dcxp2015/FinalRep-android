package com.dcxp.finalrep.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.dcxp.finalrep.R;
import com.dcxp.finalrep.activity.MainActivity;
import com.dcxp.finalrep.adapters.PlaylistAdapter;
import com.dcxp.finalrep.dialogs.PlaylistNameDialog;
import com.melnykov.fab.FloatingActionButton;

/**
 * Created by Daniel on 7/18/2015.
 */
public class Playlists extends Fragment {
    private PlaylistAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflatedView = inflater.inflate(R.layout.fragment_playlist, container, false);

        final MainActivity context = (MainActivity)getActivity();

        ListView lv = (ListView) inflatedView.findViewById(R.id.lv_playlist);

        lv.setAdapter(adapter = adapter == null ? new PlaylistAdapter(context, context.getPhraseManager().getPlaylists()) : adapter);
        adapter.notifyDataSetChanged();

        FloatingActionButton add = (FloatingActionButton) inflatedView.findViewById(R.id.fab_playlist_add);
        add.attachToListView(lv);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a playlist creation dialog, we are not editing so give it a null playlist name
                new PlaylistNameDialog(context, null);
            }
        });

        return inflatedView;
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    public PlaylistAdapter getAdapter() {
        return adapter;
    }
}
