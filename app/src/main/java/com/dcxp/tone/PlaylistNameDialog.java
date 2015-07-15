package com.dcxp.tone;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.EditText;
import android.widget.ListView;

import com.dcxp.tone.activities.PlaylistActivity;

/**
 * Created by Daniel on 7/15/2015.
 */
public class PlaylistNameDialog extends AlertDialog.Builder {

    public PlaylistNameDialog(Context context) {
        super(context);

        setTitle("Select phrases");

        EditText name = new EditText(getContext());
        name.setHint(getContext().getString(R.string.playlistName));

        setView(name);

        setPositiveButton("Create", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                new PlaylistSelectionDialog(getContext()).show();
            }
        });
    }
}
