package com.dcxp.finalrep.dialogs;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.dcxp.finalrep.R;
import com.dcxp.finalrep.activity.MainActivity;
import com.dcxp.finalrep.fragments.PlaylistSelection;
import com.dcxp.finalrep.models.Phrase;
import com.dcxp.finalrep.models.Playlist;
import com.dcxp.finalrep.utils.ContentLoader;
import com.dcxp.finalrep.utils.PhraseManager;


/**
 * Created by Daniel on 7/15/2015.
 */
public class PlaylistNameDialog extends AlertDialog.Builder {
    private boolean editingMode;

    public PlaylistNameDialog(final MainActivity context, final Playlist playlistToEdit) {
        super(context);

        final EditText name = new EditText(getContext());
        name.setHint(getContext().getString(R.string.playlistName));

        if(playlistToEdit != null) {
            // The playlist is being edited
            editingMode = true;
            name.setText(playlistToEdit.getName());
            setTitle("Edit playlist");
        }
        else {
            setTitle("Create a playlist");
        }

        name.setSelection(name.getText().length());

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

                        PhraseManager phraseManager = context.getPhraseManager();

                        if (phraseManager.isPlaylistNameTaken(playlistName)) {
                            if (playlistToEdit != null && !playlistName.equals(playlistToEdit.getName())) {
                                Toast.makeText(getContext(), "You already have a playlist with that name", Toast.LENGTH_SHORT).show();
                                return;
                            }

                        }

                        // If the name hasn't been used and it is not all spaces then continue
                        if (playlistName.trim().length() > 0) {
                            d.dismiss();

                            Playlist playlist = playlistToEdit;

                            if (playlist == null) {
                                playlist = new Playlist();
                            }

                            playlist.setOldName(playlist.getName());
                            playlist.setName(playlistName);

                            // Add the playlist and save it off
                            if (!phraseManager.isPlaylistNameTaken(playlist.getName())) {
                                // Add the playlist, listeners will be notified
                                phraseManager.addPlaylist(playlist);

                                ContentLoader.savePlaylists(context, phraseManager.getPlaylists());
                            }


                            //Intent intent = new Intent(getContext(), PlaylistSelectionActivity.class);

                            // Pass over the position of this playlist rather than doing it by serialization
                            // intent.putExtra("playlist", PlaylistManager.indexOf(playlist));

                            // context.startActivity(intent);

                            Bundle args = new Bundle();
                            args.putInt("playlist", phraseManager.indexOfPlaylist(playlist));

                            PlaylistSelection ps = new PlaylistSelection();
                            ps.setArguments(args);

                            context.setContent(ps);
                        } else {
                            Toast.makeText(getContext(), "Please enter a valid playlist name", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        d.show();
    }
}
