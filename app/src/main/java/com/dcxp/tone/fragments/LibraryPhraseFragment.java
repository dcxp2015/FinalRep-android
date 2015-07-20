package com.dcxp.tone.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.dcxp.tone.Phrase;
import com.dcxp.tone.PhraseManager;
import com.dcxp.tone.R;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Daniel on 7/17/2015.
 */
public class LibraryPhraseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflatedView = inflater.inflate(R.layout.phrase_row, container, false);

        final TextView tv = ((TextView) inflatedView.findViewById(R.id.txtv_title));

        ImageButton delete = new ImageButton(getActivity());
        delete.setBackgroundResource(R.drawable.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Phrase phrase = PhraseManager.getPhraseByName(tv.getText().toString());

                if(phrase != null) {
                    PhraseManager.removePhrase(phrase);
                }
            }
        });

        // Add the delete button into the frame layout container
        ((FrameLayout) inflatedView.findViewById(R.id.fl_container)).addView(delete);

        return inflatedView;
    }

}
