package com.dcxp.tone;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;

/**
 * Created by Daniel on 7/15/2015.
 */
public class PlaylistSelectionDialog extends AlertDialog.Builder {
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

    public PlaylistSelectionDialog(Context context){
        super(context);

        setTitle("Select phrases");
        
        setPositiveButton("Create", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        String[] phrases = {"push harder", "keep going", "you got it", "fix your form"};

        ListView listView = new ListView(context);
        listView.setAdapter(new PhraseAdapter(context, phrases));

        setView(listView);
    }


}
