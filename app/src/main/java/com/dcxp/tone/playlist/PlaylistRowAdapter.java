package com.dcxp.tone.playlist;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dcxp.tone.PlaylistManager;
import com.dcxp.tone.R;

import java.util.List;

/**
 * Created by Daniel on 7/16/2015.
 */
public class PlaylistRowAdapter extends ArrayAdapter<Playlist> {
    private List<Playlist> playlists;
    private Context context;

    public PlaylistRowAdapter(Context context, List<Playlist> playlists) {
        super(context, -1, playlists);
        this.context = context;
        this.playlists = playlists;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View inflatedView = convertView;

        if(inflatedView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            inflatedView = inflater.inflate(R.layout.playlist_row, parent, false);
        }

        // Set the text of the title to that of the corresponding playlist
        TextView name = (TextView) inflatedView.findViewById(R.id.txtv_title);
        name.setText(playlists.get(position).getName());


        // Button to remove this playlist
        ImageButton remove = (ImageButton) inflatedView.findViewById(R.id.ibtn_delete);

        // Remove a playlist when it's clicked
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlaylistManager.removePlaylist(playlists.get(position));
                PlaylistManager.save(getContext());
                notifyDataSetChanged();
            }
        });

        return inflatedView;
    }

}
