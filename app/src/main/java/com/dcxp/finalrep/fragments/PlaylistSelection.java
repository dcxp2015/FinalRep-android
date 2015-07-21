package com.dcxp.finalrep.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.dcxp.finalrep.R;
import com.dcxp.finalrep.RVPhraseAdapter;
import com.dcxp.finalrep.activity.MainActivity;
import com.dcxp.finalrep.models.Phrase;
import com.dcxp.finalrep.models.Playlist;
import com.dcxp.finalrep.utils.PhraseManager;
import com.melnykov.fab.FloatingActionButton;

/**
 * Created by Daniel on 7/18/2015.
 */
public class PlaylistSelection extends Fragment implements View.OnClickListener, PhraseManager.IPlaylistListener {
    private Playlist playList;
    private RVPhraseAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflatedView = inflater.inflate(R.layout.fragment_playlist_selection, container, false);

        final MainActivity context = (MainActivity) getActivity();

        // The index of the playlist being edited/created was passed into the intent
        playList = context.getPhraseManager().getPlaylists().get(context.getIntent().getIntExtra("playlist", 0));

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        final RecyclerView recyclerView = (RecyclerView) inflatedView.findViewById(R.id.rv_selection);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter = new RVPhraseAdapter(R.layout.row_base_phrase, context.getPhraseManager().getPhrases(), this) {

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
                //PlaylistManager.addPlaylist(playList);
                // Go back to the playlist screen
                context.onBackPressed();
            }
        });

        // Register for playlist events
        context.getPhraseManager().register(this);

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

    @Override
    public void onPlaylistCreated(Playlist playlist) {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onPlaylistDeleted(Playlist playlist) {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onPlaylistEdited(Playlist playlist) {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onPlaylistViewed(Playlist playlist) {

    }
}
