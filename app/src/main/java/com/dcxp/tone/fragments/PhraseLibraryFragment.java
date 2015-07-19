package com.dcxp.tone.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.SearchView;

import com.dcxp.tone.Phrase;
import com.dcxp.tone.R;
import com.dcxp.tone.RVPhraseAdapter;
import com.dcxp.tone.dialogs.PhraseCreationDialog;
import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 7/18/2015.
 */
public class PhraseLibraryFragment extends Fragment implements SearchView.OnQueryTextListener {
    private RecyclerView lv;
    private SearchView searchView;
    private Filter filter;
    private RVPhraseAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflatedView = inflater.inflate(R.layout.fragment_phrase_libary, container, false);

        List<Phrase> phrases = new ArrayList<Phrase>();

        phrases.add(new Phrase("Push harder", "Daniel Christopher"));
        phrases.add(new Phrase("You got it", "Daniel Christopher"));

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        lv = (RecyclerView) inflatedView.findViewById(R.id.rv_phrases);
        //lv.setTextFilterEnabled(true);
        lv.setLayoutManager(layoutManager);
        lv.setAdapter(adapter = new RVPhraseAdapter(R.layout.phrase_library_row, phrases));

        searchView = (SearchView) inflatedView.findViewById(R.id.sv_search);
        searchView.setQueryHint("Search");
        searchView.setFocusable(false);
        searchView.setOnQueryTextListener(this);

        filter = adapter.getFilter();

        FloatingActionButton add = (FloatingActionButton) inflatedView.findViewById(R.id.fab_phrases_add);
        add.attachToRecyclerView(lv);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new PhraseCreationDialog(getActivity());
            }
        });

        return inflatedView;
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
