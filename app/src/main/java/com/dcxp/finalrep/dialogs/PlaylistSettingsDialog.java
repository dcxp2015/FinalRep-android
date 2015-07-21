package com.dcxp.finalrep.dialogs;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.dcxp.finalrep.R;
import com.dcxp.finalrep.models.Playlist;
import com.dcxp.finalrep.utils.PhraseManager;

/**
 * Created by Daniel on 7/20/2015.
 */
public class PlaylistSettingsDialog extends DialogFragment {
    private PhraseManager.IPlaylistListener listener;
    private Playlist playlist;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflatedView = inflater.inflate(R.layout.dialog_playlist_settings, container, false);

        getDialog().setTitle("Settings");

        ((ImageButton) inflatedView.findViewById(R.id.ibtn_delete)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onPlaylistDeleted(playlist);
                dismiss();
            }
        });

        ((ImageButton) inflatedView.findViewById(R.id.ibtn_view)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onPlaylistViewed(playlist);
                dismiss();
            }
        });

        ((ImageButton) inflatedView.findViewById(R.id.ibtn_edit)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onPlaylistEdited(playlist);
                dismiss();
            }
        });

        return inflatedView;
    }

    public void setListener(PhraseManager.IPlaylistListener listener) {
        this.listener = listener;
    }

    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }
}
