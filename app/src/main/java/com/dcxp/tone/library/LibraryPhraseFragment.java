package com.dcxp.tone.library;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.dcxp.tone.R;

/**
 * Created by Daniel on 7/17/2015.
 */
public class LibraryPhraseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflatedView = inflater.inflate(R.layout.phrase_row, container, false);

        ImageButton delete = new ImageButton(getActivity());
        delete.setBackgroundResource(R.drawable.delete);

        // Add the delete button into the frame layout container
        ((FrameLayout) inflatedView.findViewById(R.id.fl_container)).addView(delete);

        return inflatedView;
    }

}
