package com.dcxp.tone.activities;

import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.dcxp.tone.PlaylistNameDialog;
import com.dcxp.tone.PlaylistSelectionDialog;
import com.dcxp.tone.R;
import com.melnykov.fab.FloatingActionButton;


public class PlaylistActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);

        String[] playlist = new String[25];

        for(int i = 0; i < playlist.length; i++) {
            playlist[i] = "playlist " + i;
        }

        ListView lv = (ListView) findViewById(R.id.lv_playlist);
        lv.setAdapter(new ArrayAdapter<String>(this, R.layout.custom_listview_item, playlist));

        FloatingActionButton add = (FloatingActionButton) findViewById(R.id.fab_playlist_add);
        add.attachToListView(lv);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new PlaylistNameDialog(PlaylistActivity.this).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_playlist, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
