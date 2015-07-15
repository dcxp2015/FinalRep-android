package com.dcxp.tone;

import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListView;

/**
 * Created by Daniel on 7/15/2015.
 */
public class PhraseDialogFragment extends DialogFragment {
    public static final String TAG = "com.dcxp.tone";

    private class PhraseAdapter extends ArrayAdapter<String> {
        private String[] phrases;
        private Context context;

        public PhraseAdapter(Context context, String[] phrases) {
            super(context, -1, phrases);
            this.context = context;
            this.phrases = phrases;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View inflatedView = convertView;

            if(inflatedView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                inflatedView = inflater.inflate(R.layout.phrase_row, parent, false);

                CheckBox phraseCheckBox = (CheckBox)inflatedView.findViewById(R.id.cb_include);
                phraseCheckBox.setText(phrases[position]);
                phraseCheckBox.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            }

            return inflatedView;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().setTitle("Select phrases");

        View inflatedView = inflater.inflate(R.layout.phrase_dialog, container, false);

        String[] phrases = {"push harder", "keep going", "you got it", "fix your form"};

        ListView lv = (ListView) inflatedView.findViewById(R.id.lv_phrases);
        lv.setAdapter(new PhraseAdapter(getDialog().getContext(), phrases));


        return inflatedView;
    }
}
