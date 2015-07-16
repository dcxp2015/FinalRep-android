package com.dcxp.tone.activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.dcxp.tone.playlist.PlaylistEditor;
import com.dcxp.tone.playlist.PlaylistLoader;
import com.dcxp.tone.playlist.PlaylistSaver;
import com.dcxp.tone.playlist.IPlaylistListener;
import com.dcxp.tone.playlist.Playlist;
import com.dcxp.tone.dialogs.PlaylistNameDialog;
import com.dcxp.tone.R;
import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class PlaylistActivity extends ActionBarActivity implements IPlaylistListener {
    private List<Playlist> playlists;
    private List<String> names;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);

        new PlaylistLoader(this).execute();

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
                // Create a playlist creation dialog, we are not editing so give it a null playlist name
                new PlaylistNameDialog(PlaylistActivity.this, PlaylistActivity.this, null);
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                new PlaylistNameDialog(PlaylistActivity.this, PlaylistActivity.this, playlists.get(position));
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
        registerPlaylist(playlist);

        // Save the playlist
        new PlaylistSaver(this).execute(playlist);
    }

    @Override
    public void onPlaylistImported(Playlist playlist) {
        registerPlaylist(playlist);
    }

    @Override
    public void onPlaylistEdited(Playlist playlist) {
        names.remove(playlist.getOldName());
        names.add(playlist.getName());
        adapter.notifyDataSetChanged();
        new PlaylistEditor(this).execute(playlist);
    }

    @Override
    public boolean isPlaylistNameTaken(String name) {
        return names.contains(name);
    }

    private void registerPlaylist(Playlist playlist) {
        names.add(playlist.getName());
        playlists.add(playlist);
        adapter.notifyDataSetChanged();
    }
}
