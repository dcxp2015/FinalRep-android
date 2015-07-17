package com.dcxp.tone.uploadexplorer;

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
public class ExplorePhraseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflatedView = inflater.inflate(R.layout.phrase_row, container, false);

        ImageButton download = new ImageButton(getActivity());
        download.setBackgroundResource(R.drawable.download);

        // Now inject the download button into the view's fl container
        ((FrameLayout) inflatedView.findViewById(R.id.fl_container)).addView(download);

        return inflatedView;
    }

}
