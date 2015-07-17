package com.dcxp.tone.uploadexplorer;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.internal.view.menu.MenuView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dcxp.tone.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 7/17/2015.
 */
public class UploadRVAdapter extends RecyclerView.Adapter<UploadRVAdapter.ViewHolder> implements Filterable {
    private List<UploadedPhrase> uploads;
    private List<UploadedPhrase> originalUploads;

    public UploadRVAdapter(List<UploadedPhrase> uploads) {
        this.uploads = uploads;
        originalUploads = new ArrayList<UploadedPhrase>();
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final UploadedPhrase upload = uploads.get(position);
        holder.title.setText(upload.getName());
        holder.submitter.setText(upload.getSubmitter());

        // Have to set the listener here because download will delete from the recycler view and needs a position to remove from
        holder.downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Dynamically insert the progress bar in place of the download button
                holder.downloadContainer.removeAllViews();
                holder.downloadContainer.addView(holder.progressBar);
            }
        });
    }

    @Override
    public int getItemCount() {
        return uploads.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.explore_phrase_row, parent, false);
        return new ViewHolder(view);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        private ImageButton tuneButton;
        private ImageButton playButton;
        private ImageButton downloadButton;
        private TextView title;
        private TextView submitter;
        private FrameLayout downloadContainer;
        private ProgressBar progressBar;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view.findViewById(R.id.card);
            tuneButton = (ImageButton) view.findViewById(R.id.ibtn_tune);
            playButton = (ImageButton) view.findViewById(R.id.ibtn_play);
            downloadButton = (ImageButton) view.findViewById(R.id.ibtn_download);
            title = (TextView) view.findViewById(R.id.txtv_title);
            submitter = (TextView) view.findViewById(R.id.txtv_submitter);
            downloadContainer = (FrameLayout) view.findViewById(R.id.fl_container);
            progressBar = new ProgressBar(view.getContext());

            playButton.setTag(R.drawable.play);

            final Resources resources = view.getResources();

            progressBar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Cancel the download

                    // Replace the progress spinner with the download button
                    downloadContainer.removeAllViews();
                    downloadContainer.addView(downloadButton);
                }
            });

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
        return UploadedPhrase.filter(originalUploads, uploads, this);
    }
}
