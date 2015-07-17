package com.dcxp.tone.activities;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.dcxp.tone.Phrase;
import com.dcxp.tone.R;
import com.dcxp.tone.RVPhraseAdapter;
import com.dcxp.tone.dialogs.PlaylistNameDialog;
import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class PlaylistSelectionActivity extends ActionBarActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist_selection);

        List<Phrase> phrases = new ArrayList<Phrase>();

        for(int i = 0; i < 5; i++) {
            phrases.add(new Phrase("phrase", "daniel christopher"));
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_selection);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(new RVPhraseAdapter(R.layout.phrase_selection_row, phrases, this));

        FloatingActionButton next = (FloatingActionButton) findViewById(R.id.fab_next);
        next.attachToRecyclerView(recyclerView);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        // A row in the recycler view has been clicked, change cardview
        RelativeLayout wrapper = ((RelativeLayout) v.findViewById(R.id.rl_cv_wrapper));

        if(wrapper.getAlpha() == 0.25f) {
            wrapper.setAlpha(1);
        }
        else {
            wrapper.setAlpha(0.25f);
        }
    }
}
