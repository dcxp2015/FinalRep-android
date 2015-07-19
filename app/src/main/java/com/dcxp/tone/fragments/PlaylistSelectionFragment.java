package com.dcxp.tone.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.dcxp.tone.Phrase;
import com.dcxp.tone.PlaylistManager;
import com.dcxp.tone.R;
import com.dcxp.tone.RVPhraseAdapter;
import com.dcxp.tone.playlist.Playlist;
import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 7/18/2015.
 */
public class PlaylistSelectionFragment extends Fragment implements View.OnClickListener {
    private Playlist playList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflatedView = inflater.inflate(R.layout.fragment_playlist_selection, container, false);

        final Activity context = getActivity();

        // The index of the playlist being edited/created was passed into the intent
        playList = PlaylistManager.getPlaylist(context.getIntent().getIntExtra("playlist", 0));

        List<Phrase> phrases = new ArrayList<Phrase>();

        for(int i = 0; i < 5; i++) {
            phrases.add(new Phrase("phrase", "daniel christopher"));
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        final RecyclerView recyclerView = (RecyclerView) inflatedView.findViewById(R.id.rv_selection);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(new RVPhraseAdapter(R.layout.phrase_selection_row, phrases, this) {

            @Override
            public void onBindViewHolder(ViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
            }

        });

        FloatingActionButton next = (FloatingActionButton) inflatedView.findViewById(R.id.fab_next);
        next.attachToRecyclerView(recyclerView);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //        PlaylistManager.addPlaylist(playList);

                // Go back to the playlist screen
                context.onBackPressed();
            }
        });

        return inflatedView;
    }

    @Override
    public void onClick(View v) {
        // A row in the recycler view has been clicked, change cardview
        RelativeLayout wrapper = ((RelativeLayout) v.findViewById(R.id.rl_cv_wrapper));

        if(wrapper.getAlpha() == 0.25f) {
            // The phrase was removed
            wrapper.setAlpha(1);
        }
        else {
            // The phrase was added
            wrapper.setAlpha(0.25f);

            // Grab the phrase for this view and add it to the playlist
            playList.addPhrase((Phrase) v.getTag());
        }
    }
}
