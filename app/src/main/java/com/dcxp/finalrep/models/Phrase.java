package com.dcxp.finalrep.models;

import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Filter;

import com.dcxp.finalrep.RVPhraseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 7/20/2015.
 */
public class Phrase {
    private String name;
    private String submitter;

    public Phrase() {

    }

    public Phrase(String name, String submitter) {
        this.name = name;
        this.submitter = submitter;
    }

    public String getSubmitter() {
        return submitter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSubmitter(String submitter) {
        this.submitter = submitter;
    }

    public static Filter filter(final List<Phrase> original, final List<Phrase> uploads, final RVPhraseAdapter adapter) {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                List<Phrase> filtered = new ArrayList<Phrase>();
                List<Phrase> originalUploads = original;

                if (originalUploads.size() == 0) {
                    for(Phrase upload : (List<Phrase>)uploads) {
                        originalUploads.add(upload);
                    }
                }

                if (constraint == null || constraint.length() == 0) {
                    results.count = originalUploads.size();
                    results.values = originalUploads;
                }
                else {
                    constraint = constraint.toString().toLowerCase();

                    for (int i = 0; i < originalUploads.size(); i++) {
                        Phrase data = originalUploads.get(i);

                        if (data.getName().toLowerCase().startsWith(constraint.toString())) {
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
                uploads.clear();

                for(Phrase upload : (List<Phrase>)results.values) {
                    uploads.add(upload);
                }

                adapter.notifyDataSetChanged();
            }
        };
    }
}
