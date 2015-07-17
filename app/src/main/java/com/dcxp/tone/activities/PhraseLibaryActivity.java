package com.dcxp.tone.activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.Filter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.dcxp.tone.R;
import com.dcxp.tone.library.PhraseLibaryRowAdapter;
import com.dcxp.tone.uploadexplorer.UploadRVAdapter;
import com.dcxp.tone.uploadexplorer.UploadedPhrase;

import java.util.ArrayList;
import java.util.List;

public class PhraseLibaryActivity extends ActionBarActivity implements SearchView.OnQueryTextListener {
    private RecyclerView lv;
    private SearchView searchView;
    private Filter filter;
    private UploadRVAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phrase_libary);

        List<UploadedPhrase> phrases = new ArrayList<UploadedPhrase>();

        phrases.add(new UploadedPhrase("Push harder", "Daniel Christopher"));
        phrases.add(new UploadedPhrase("You got it", "Daniel Christopher"));

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        lv = (RecyclerView) findViewById(R.id.rv_phrases);
        //lv.setTextFilterEnabled(true);
        lv.setLayoutManager(layoutManager);
        lv.setAdapter(adapter = new UploadRVAdapter(phrases));

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
