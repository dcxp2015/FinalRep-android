package com.dcxp.tone.activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.dcxp.tone.playlist.IPlaylistCreationListener;
import com.dcxp.tone.playlist.Playlist;
import com.dcxp.tone.dialogs.PlaylistNameDialog;
import com.dcxp.tone.R;
import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class PlaylistActivity extends ActionBarActivity implements IPlaylistCreationListener {
    private List<Playlist> playlists;
    private List<String> names;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);

        names = new ArrayList<String>();
        playlists = new ArrayList<Playlist>();

        for(Playlist playlist : playlists) {
            names.add(playlist.getName());
        }

        ListView lv = (ListView) findViewById(R.id.lv_playlist);
        lv.setAdapter(adapter = new ArrayAdapter<String>(this, R.layout.custom_listview_item, names));

        FloatingActionButton add = (FloatingActionButton) findViewById(R.id.fab_playlist_add);
        add.attachToListView(lv);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new PlaylistNameDialog(PlaylistActivity.this, PlaylistActivity.this);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_playlist, menu);
        return true;
    }

    @Override
    public void onPlaylistCreated(Playlist playlist) {
        names.add(playlist.getName());
        playlists.add(playlist);
        adapter.notifyDataSetChanged();
    }
}
