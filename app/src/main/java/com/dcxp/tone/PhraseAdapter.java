package com.dcxp.tone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 7/15/2015.
 */
public class PhraseAdapter extends ArrayAdapter<String> {
    private String[] phrases;
    private List<String> checkedPhrases;
    private Context context;

    public PhraseAdapter(Context context, String[] phrases) {
        super(context, -1, phrases);
        this.context = context;
        this.phrases = phrases;
        checkedPhrases = new ArrayList<String>();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View inflatedView = convertView;

        if(inflatedView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            inflatedView = inflater.inflate(R.layout.phrase_row, parent, false);
        }

        Button play = (Button) inflatedView.findViewById(R.id.btn_play);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PhraseAdapter.this.getContext(), "Playing...", Toast.LENGTH_SHORT).show();
            }
        });

        final CheckBox phraseCheckBox = (CheckBox) inflatedView.findViewById(R.id.cb_include);
        phraseCheckBox.setText(phrases[position]);
        phraseCheckBox.setChecked(checkedPhrases.contains(phrases[position]));
        phraseCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (phraseCheckBox.isChecked()) {
                    if (!checkedPhrases.contains(phrases[position])) {
                        checkedPhrases.add(phrases[position]);
                    }
                } else {
                    if (checkedPhrases.contains(phrases[position])) {
                        checkedPhrases.remove(phrases[position]);
                    }
                }
            }
        });

        return inflatedView;
    }

    public List<String> getCheckedPhrases() {
        return checkedPhrases;
    }
}