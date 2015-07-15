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

/**
 * Created by Daniel on 7/15/2015.
 */
public class PlaylistNameDialog extends AlertDialog.Builder {

    public PlaylistNameDialog(Context context, final IPlaylistCreationListener listener) {
        super(context);

        setTitle("Create a playlist");

        final EditText name = new EditText(getContext());
        name.setHint(getContext().getString(R.string.playlistName));

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
                            new PlaylistSelectionDialog(getContext(), listener, playlistName).show();
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
