package com.dcxp.tone.uploadexplorer;

import android.widget.Filter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 7/17/2015.
 */
public class UploadedPhrase {
    private String name;
    private String submitter;

    public UploadedPhrase(String name, String submitter) {
        this.name = name;
        this.submitter = submitter;
    }

    public static Filter filter(final List<UploadedPhrase> original, final List<UploadedPhrase> uploads, final UploadRVAdapter adapter) {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                List<UploadedPhrase> filtered = new ArrayList<UploadedPhrase>();
                List<UploadedPhrase> originalUploads = original;

                if (originalUploads.size() == 0) {
                    for(UploadedPhrase upload : (List<UploadedPhrase>)uploads) {
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
                        UploadedPhrase data = originalUploads.get(i);

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

                for(UploadedPhrase upload : (List<UploadedPhrase>)results.values) {
                    uploads.add(upload);
                }

                adapter.notifyDataSetChanged();
            }
        };
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSubmitter(String submitter) {
        this.submitter = submitter;
    }

    public String getName() {
        return name;
    }

    public String getSubmitter() {
        return submitter;
    }
}
