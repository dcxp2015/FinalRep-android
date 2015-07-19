package com.dcxp.tone.dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.dcxp.tone.playlist.PlaylistManager;
import com.dcxp.tone.R;
import com.dcxp.tone.playlist.Playlist;

/**
 * Created by Daniel on 7/15/2015.
 */
public class PlaylistNameDialog extends AlertDialog.Builder {
    private boolean editingMode;

    public PlaylistNameDialog(final Context context, final Playlist playlistToEdit) {
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

//        name.requestFocus();
//        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);

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

                        if(PlaylistManager.isPlaylistNameTaken(playlistName)) {
                            if(playlistToEdit != null && !playlistName.equals(playlistToEdit.getName())) {
                                Toast.makeText(getContext(), "You already have a playlist with that name", Toast.LENGTH_SHORT).show();
                                return;
                            }

                        }

                        // If the name hasn't been used and it is not all spaces then continue
                        if (playlistName.trim().length() > 0){
                            d.dismiss();

                            Playlist playlist = playlistToEdit;

                            if (playlist == null) {
                                playlist = new Playlist();
                            }

                            playlist.setOldName(playlist.getName());
                            playlist.setName(playlistName);

                            // Add the playlist and save it off
                            if(!PlaylistManager.getPlaylists().contains(playlist)) {
                                PlaylistManager.addPlaylist(playlist);
                                PlaylistManager.save(getContext());
                            }


                            //Intent intent = new Intent(getContext(), PlaylistSelectionActivity.class);

                            // Pass over the position of this playlist rather than doing it by serialization
                           // intent.putExtra("playlist", PlaylistManager.indexOf(playlist));
                            
                           // context.startActivity(intent);
                        }
                        else {
                            Toast.makeText(getContext(), "Please enter a valid playlist name", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });


        d.show();
    }
}
