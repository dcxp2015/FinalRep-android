package com.dcxp.tone.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dcxp.tone.Phrase;
import com.dcxp.tone.R;
import com.dcxp.tone.RVPhraseAdapter;
import com.dcxp.tone.utils.Divider;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 7/19/2015.
 */
public class ExploreFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflatedView = inflater.inflate(R.layout.fragment_explore, container, false);

        List<Phrase> uploads = new ArrayList<Phrase>();
        uploads.add(new Phrase("Push harder", "Daniel Christopher"));
        uploads.add(new Phrase("You got it", "Daniel Christopher"));
        uploads.add(new Phrase("Lets go", "Daniel Christopher"));
        uploads.add(new Phrase("No pain no gain", "Daniel Christopher"));
        uploads.add(new Phrase("Pain is temporary", "Daniel Christopher"));
        uploads.add(new Phrase("Never give up", "Daniel Christopher"));
        uploads.add(new Phrase("Work harder", "Daniel Christopher"));

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        RecyclerView rvExplore = (RecyclerView) inflatedView.findViewById(R.id.rv_explore_content);
        rvExplore.setAdapter(new RVPhraseAdapter(R.layout.phrase_explore_row, uploads));
        rvExplore.setHasFixedSize(true);
        rvExplore.setLayoutManager(layoutManager);
        rvExplore.addItemDecoration(new Divider(25));

        return inflatedView;
    }

}
