package com.dcxp.tone.playlist;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.dcxp.tone.R;

import java.util.List;

/**
 * Created by Daniel on 7/16/2015.
 */
public class PlaylistRowAdapter extends ArrayAdapter<Playlist> {
    private List<Playlist> playlists;
    private Context context;
    private IPlaylistListener listener;

    public PlaylistRowAdapter(Context context, List<Playlist> playlists, IPlaylistListener listener) {
        super(context, -1, playlists);
        this.context = context;
        this.playlists = playlists;
        this.listener = listener;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View inflatedView = convertView;

        if(inflatedView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            inflatedView = inflater.inflate(R.layout.playlist_row, parent, false);
        }

        // Set the text of the title to that of the corresponding playlist
        TextView name = (TextView) inflatedView.findViewById(R.id.txtv_name);
        name.setText(playlists.get(position).getName());


        // Button to remove this playlist
        ImageButton remove = (ImageButton) inflatedView.findViewById(R.id.btn_remove);

        // Change it's tint to dark red
        remove.setColorFilter(context.getResources().getColor(android.R.color.holo_red_dark), PorterDuff.Mode.SRC_ATOP);

        // Remove a playlist when it's clicked
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onPlaylistRemoved(playlists.get(position));
            }
        });

        return inflatedView;
    }
}
