package com.dcxp.tone.activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.dcxp.tone.utils.Divider;
import com.dcxp.tone.R;
import com.dcxp.tone.Phrase;
import com.dcxp.tone.RVPhraseAdapter;

import java.util.ArrayList;
import java.util.List;

public class ExploreActivity extends ActionBarActivity {
    private List<Phrase> uploads;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);

        uploads = new ArrayList<Phrase>();
        uploads.add(new Phrase("Push harder", "Daniel Christopher"));
        uploads.add(new Phrase("You got it", "Daniel Christopher"));
        uploads.add(new Phrase("Lets go", "Daniel Christopher"));
        uploads.add(new Phrase("No pain no gain", "Daniel Christopher"));
        uploads.add(new Phrase("Pain is temporary", "Daniel Christopher"));
        uploads.add(new Phrase("Never give up", "Daniel Christopher"));
        uploads.add(new Phrase("Work harder", "Daniel Christopher"));

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        RecyclerView rvExplore = (RecyclerView) findViewById(R.id.rv_explore_content);
        rvExplore.setAdapter(new RVPhraseAdapter(R.layout.phrase_explore_row, uploads));
        rvExplore.setHasFixedSize(true);
        rvExplore.setLayoutManager(layoutManager);
        rvExplore.addItemDecoration(new Divider(25));
    }
}
