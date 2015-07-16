package com.dcxp.tone.library;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.SearchView;
import android.widget.TextView;

import com.dcxp.tone.R;

import java.util.List;

/**
 * Created by Daniel on 7/16/2015.
 */
public class PhraseLibaryRowAdapter extends ArrayAdapter<String>  {
    private List<String> phrases;
    private Context context;

    public PhraseLibaryRowAdapter(Context context, List<String> phrases) {
        super(context, -1, phrases);
        this.phrases = phrases;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View inflatedView = convertView;

        if(inflatedView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            inflatedView = inflater.inflate(R.layout.phrase_library_row, parent, false);
        }

        TextView name = (TextView) inflatedView.findViewById(R.id.txtv_name);
        name.setText(phrases.get(position));

        return inflatedView;
    }
}
