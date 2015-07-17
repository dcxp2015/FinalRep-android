package com.dcxp.tone;

import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 7/17/2015.
 */
public class RVPhraseAdapter extends RecyclerView.Adapter<RVPhraseAdapter.ViewHolder> implements Filterable {
    private List<Phrase> uploads;
    private List<Phrase> originalUploads;
    private View.OnClickListener listener;
    private int layoutToInflate;

    public RVPhraseAdapter(int layoutToInflate, List<Phrase> uploads, View.OnClickListener listener) {
        this(layoutToInflate, uploads);
        this.listener = listener;
    }

    public RVPhraseAdapter(int layoutToInflate, List<Phrase> uploads) {
        this.uploads = uploads;
        this.layoutToInflate = layoutToInflate;
        originalUploads = new ArrayList<Phrase>();
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Phrase upload = uploads.get(position);
        holder.title.setText(upload.getName());
        holder.submitter.setText(upload.getSubmitter());
    }

    @Override
    public int getItemCount() {
        return uploads.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutToInflate, parent, false);
        view.setOnClickListener(listener);
        return new ViewHolder(view);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageButton playButton;
        private TextView title;
        private TextView submitter;
        private FrameLayout injectableContainer;
        private ProgressBar progressBar;

        public ViewHolder(View view) {
            super(view);
            playButton = (ImageButton) view.findViewById(R.id.ibtn_play);
            title = (TextView) view.findViewById(R.id.txtv_title);
            submitter = (TextView) view.findViewById(R.id.txtv_submitter);
            injectableContainer = (FrameLayout) view.findViewById(R.id.fl_container);
            progressBar = new ProgressBar(view.getContext());

            playButton.setTag(R.drawable.play);

            final Resources resources = view.getResources();

            playButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(playButton.getTag().equals(R.drawable.play)) {
                        playButton.setTag(R.drawable.pause);
                        playButton.setBackgroundResource(R.drawable.pause);
                    }
                    else {
                        playButton.setTag(R.drawable.play);
                        playButton.setBackgroundResource(R.drawable.play);
                    }
                }
            });
        }
    }

    @Override
    public Filter getFilter() {
        return Phrase.filter(originalUploads, uploads, this);
    }
}
