package com.dcxp.finalrep.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.dcxp.finalrep.R;
import com.dcxp.finalrep.activity.MainActivity;
import com.dcxp.finalrep.dialogs.PlaylistNameDialog;
import com.dcxp.finalrep.dialogs.PlaylistSettingsDialog;
import com.dcxp.finalrep.models.Playlist;
import com.dcxp.finalrep.utils.PhraseManager;

import java.util.List;

/**
 * Created by Daniel on 7/20/2015.
 */
public class PlaylistAdapter extends ArrayAdapter<Playlist> implements PhraseManager.IPlaylistListener {
    private List<Playlist> playlists;
    private MainActivity context;

    public PlaylistAdapter(MainActivity context, List<Playlist> playlists) {
        super(context, -1, playlists);
        this.playlists = playlists;
        this.context = context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View inflatedView = convertView;

        if(inflatedView == null) {
            inflatedView =  LayoutInflater.from(getContext()).inflate(R.layout.row_playlist, parent, false);
        }

        // Set the title of the row to that of the playlist
        ((TextView) inflatedView.findViewById(R.id.txtv_title)).setText(playlists.get(position).getName());

        // When settings is clicked is clicked
        ((ImageButton) inflatedView.findViewById(R.id.ibtn_settings)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlaylistSettingsDialog d = new PlaylistSettingsDialog();
                d.show(context.getFragmentManager(), "");
                d.setPlaylist(playlists.get(position));
                d.setListener(PlaylistAdapter.this);
            }
        });

        return inflatedView;

    }

    @Override
    public void onPlaylistEdited(Playlist playlist) {
        new PlaylistNameDialog(context, playlist);
    }

    @Override
    public void onPlaylistDeleted(Playlist playlist) {
        context.getPhraseManager().removePlaylist(playlist);
        notifyDataSetChanged();
    }

    @Override
    public void onPlaylistCreated(Playlist playlist) {
    }

    @Override
    public void onPlaylistViewed(Playlist playlist) {

    }
}
