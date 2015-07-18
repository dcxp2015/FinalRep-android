package com.dcxp.tone.activities;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.dcxp.tone.PlaylistManager;
import com.dcxp.tone.playlist.PlaylistRowAdapter;
import com.dcxp.tone.playlist.Playlist;
import com.dcxp.tone.dialogs.PlaylistNameDialog;
import com.dcxp.tone.R;
import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class PlaylistActivity extends ActionBarActivity {
    private PlaylistRowAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);

        PlaylistManager.load(this);

        ListView lv = (ListView) findViewById(R.id.lv_playlist);
        lv.setAdapter(adapter = adapter == null ? new PlaylistRowAdapter(this, PlaylistManager.getPlaylists()) : adapter);
        adapter.notifyDataSetChanged();

        FloatingActionButton add = (FloatingActionButton) findViewById(R.id.fab_playlist_add);
        add.attachToListView(lv);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a playlist creation dialog, we are not editing so give it a null playlist name
                new PlaylistNameDialog(PlaylistActivity.this, null);
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                new PlaylistNameDialog(PlaylistActivity.this, PlaylistManager.getPlaylists().get(position));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }
}
