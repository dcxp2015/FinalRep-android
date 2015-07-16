package com.dcxp.tone.dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import com.dcxp.tone.playlist.IPlaylistCreationListener;
import com.dcxp.tone.R;
import com.dcxp.tone.playlist.Playlist;

/**
 * Created by Daniel on 7/15/2015.
 */
public class PlaylistNameDialog extends AlertDialog.Builder {

    public PlaylistNameDialog(Context context, final IPlaylistCreationListener listener, final Playlist playlistToEdit) {
        super(context);

        final EditText name = new EditText(getContext());
        name.setHint(getContext().getString(R.string.playlistName));

        if(playlistToEdit != null) {
            // The playlist is being edited
            name.setText(playlistToEdit.getName());
            setTitle("Edit playlist");
        }
        else {
            setTitle("Create a playlist");
        }

        setView(name);

        setPositiveButton("Next", null);

        final AlertDialog d =  create();
        d.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(final DialogInterface dialog) {
                d.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String playlistName = name.getText().toString();

                        if (playlistName.trim().length() > 0) {
                            d.dismiss();

                            Playlist playlist = playlistToEdit;

                            if(playlist == null) {
                                playlist = new Playlist();
                                playlist.setName(playlistName);
                            }

                            new PlaylistSelectionDialog(getContext(), listener, playlist).show();
                        } else {
                            Toast.makeText(getContext(), "Please enter a name", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        d.show();
    }
}
