package com.dcxp.tone.dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.dcxp.tone.playlist.IPlaylistListener;
import com.dcxp.tone.PhraseAdapter;
import com.dcxp.tone.playlist.Playlist;

/**
 * Created by Daniel on 7/15/2015.
 */
public class PlaylistSelectionDialog extends AlertDialog.Builder {
    public static final String TAG = "com.dcxp.tone";
    private String[] phrases;
    private Playlist playlist;
    private ListView listView;
    private PhraseAdapter adapter;

    public PlaylistSelectionDialog(final Context context, final IPlaylistListener listener, final Playlist playlist){
        super(context);
        this.playlist = playlist;

        phrases = new String[25];

        for(int i = 0; i < phrases.length; i++) {
            phrases[i] = "phrase: " + i;
        }

        setTitle("Select phrases");

        // Playlist is initially being created
        if(playlist.getPhrases() == null) {
            setPositiveButton("Create", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    playlist.setPhrases(adapter.getCheckedPhrases());
                    listener.onPlaylistCreated(playlist);
                }
            });
        }
        else {
            // Playlist is being edited
            setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    playlist.setPhrases(adapter.getCheckedPhrases());
                }
            });
        }

        listView = new ListView(getContext());
        listView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        listView.setAdapter(adapter = new PhraseAdapter(context, phrases, playlist.getPhrases()));

        setView(listView);
    }
}
