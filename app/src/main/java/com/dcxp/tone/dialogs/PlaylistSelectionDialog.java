package com.dcxp.tone.dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.dcxp.tone.Phrase;
import com.dcxp.tone.R;
import com.dcxp.tone.RVPhraseAdapter;
import com.dcxp.tone.playlist.IPlaylistListener;
import com.dcxp.tone.playlist.Playlist;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 7/15/2015.
 */
public class PlaylistSelectionDialog extends AlertDialog.Builder {
    public static final String TAG = "com.dcxp.tone";
    private List<Phrase> phrases;
    private List<Phrase> checkedPhrases;
    private Playlist playlist;
    private RecyclerView recyclerView;
    private RVPhraseAdapter adapter;

    public PlaylistSelectionDialog(final Context context, final IPlaylistListener listener, final Playlist playlist){
        super(context);
        this.playlist = playlist;

        checkedPhrases = new ArrayList<Phrase>();
        phrases = new ArrayList<Phrase>();

        for(int i = 0; i < 25; i++) {
            phrases.add(new Phrase("phrase " + i, "Daniel Christopher"));
        }

        setTitle("Select phrases");

        // Playlist is initially being created
        if(playlist.getPhrases() == null) {
            setPositiveButton("Create", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    playlist.setPhrases(checkedPhrases);
                    listener.onPlaylistCreated(playlist);
                }
            });
        }
        else {
            // Playlist is being edited
            setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    playlist.setPhrases(checkedPhrases);
                    listener.onPlaylistEdited(playlist);
                }
            });
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        RecyclerView recyclerView = new RecyclerView(context);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(new RVPhraseAdapter(R.layout.phrase_selection_row, phrases));

        setView(recyclerView);
    }
}
