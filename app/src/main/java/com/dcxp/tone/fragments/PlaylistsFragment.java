package com.dcxp.tone.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.dcxp.tone.playlist.PlaylistManager;
import com.dcxp.tone.R;
import com.dcxp.tone.dialogs.PlaylistNameDialog;
import com.dcxp.tone.playlist.PlaylistRowAdapter;
import com.melnykov.fab.FloatingActionButton;

/**
 * Created by Daniel on 7/18/2015.
 */
public class PlaylistsFragment extends Fragment {
    private PlaylistRowAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflatedView = inflater.inflate(R.layout.fragment_playlist, container, false);

        PlaylistManager.load(getActivity());

        final Context context = getActivity();

        ListView lv = (ListView) inflatedView.findViewById(R.id.lv_playlist);
        lv.setAdapter(adapter = adapter == null ? new PlaylistRowAdapter(context, PlaylistManager.getPlaylists()) : adapter);
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

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                new PlaylistNameDialog(context, PlaylistManager.getPlaylists().get(position));
            }
        });

        return inflatedView;
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }
}
