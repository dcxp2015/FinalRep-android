package com.dcxp.tone;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 7/15/2015.
 */
public class PlaylistSelectionDialog extends AlertDialog.Builder {
    public static final String TAG = "com.dcxp.tone";
    private String[] phrases = {"work harder", "push", "you got it"};
    private String playlistName;
    private ListView listView;
    private PhraseAdapter adapter;

    public PlaylistSelectionDialog(final Context context, final IPlaylistCreationListener listener, final String playlistName){
        super(context);
        this.playlistName = playlistName;

        setTitle("Select phrases");

        setPositiveButton("Create", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.onPlaylistCreated(new Playlist(playlistName, getSelectedPhrases()));
            }
        });

        listView = new ListView(context);
        listView.setAdapter(adapter = new PhraseAdapter(context, phrases));

        setView(listView);
    }

    private List<String> getSelectedPhrases() {
        List<String> selectedPhrases = new ArrayList<String>();

        int count = adapter.getCount();
        for(int i = 0; i < count; i++) {
            CheckBox checkBox = (CheckBox) listView.getChildAt(i).findViewById(R.id.cb_include);

            if(checkBox.isSelected()) {
                selectedPhrases.add(checkBox.getText().toString());
            }
        }

        return selectedPhrases;
    }

}
