package com.dcxp.tone.activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Menu;
import android.widget.Filter;
import android.widget.SearchView;

import com.dcxp.tone.R;
import com.dcxp.tone.RVPhraseAdapter;
import com.dcxp.tone.Phrase;

import java.util.ArrayList;
import java.util.List;

public class PhraseLibaryActivity extends ActionBarActivity implements SearchView.OnQueryTextListener {
    private RecyclerView lv;
    private SearchView searchView;
    private Filter filter;
    private RVPhraseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phrase_libary);

        List<Phrase> phrases = new ArrayList<Phrase>();

        phrases.add(new Phrase("Push harder", "Daniel Christopher"));
        phrases.add(new Phrase("You got it", "Daniel Christopher"));

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        lv = (RecyclerView) findViewById(R.id.rv_phrases);
        //lv.setTextFilterEnabled(true);
        lv.setLayoutManager(layoutManager);
        lv.setAdapter(adapter = new RVPhraseAdapter(R.layout.phrase_library_row, phrases));

        searchView = (SearchView) findViewById(R.id.sv_search);
        searchView.setQueryHint("Search");
        searchView.setFocusable(false);
        searchView.setOnQueryTextListener(this);

        filter = adapter.getFilter();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_phrase_libary, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if(TextUtils.isEmpty(newText)) {
            filter.filter(null);
        }
        else {
            filter.filter(newText);
        }

        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }
}
