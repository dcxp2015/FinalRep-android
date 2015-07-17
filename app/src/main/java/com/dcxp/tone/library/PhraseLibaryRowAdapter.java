package com.dcxp.tone.library;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.SearchView;
import android.widget.TextView;

import com.dcxp.tone.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 7/16/2015.
 */
public class PhraseLibaryRowAdapter extends ArrayAdapter<String> {
    private List<String> originalPhrases;
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

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                List<String> filtered = new ArrayList<String>();

                if (originalPhrases == null) {
                    originalPhrases = new ArrayList<String>(phrases);
                }

                if (constraint == null || constraint.length() == 0) {
                    results.count = originalPhrases.size();
                    results.values = originalPhrases;
                }
                else {
                    constraint = constraint.toString().toLowerCase();

                    for (int i = 0; i < originalPhrases.size(); i++) {
                        String data = originalPhrases.get(i);

                        if (data.toLowerCase().startsWith(constraint.toString())) {
                            filtered.add(data);
                        }
                    }

                    results.count = filtered.size();
                    results.values = filtered;
                }

                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                phrases = (List<String>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    @Override
    public int getCount() {
        return phrases != null ? phrases.size() : 0;
    }
}
