package com.dcxp.tone.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.EditText;

import com.dcxp.tone.R;
import com.dcxp.tone.activities.MainActivity;
import com.dcxp.tone.fragments.RecordingFragment;

/**
 * Created by Daniel on 7/19/2015.
 */
public class PhraseNameDialog extends AlertDialog.Builder {

    public PhraseNameDialog(final Context context) {
        super(context);

        setTitle("Name of this Phrase");

        final EditText name = new EditText(context);
        name.setHint("Name");

        setView(name);

        setPositiveButton(context.getString(R.string.next), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Arguments to give to the recording dialog
                Bundle args = new Bundle();

                // Give the recording dialog the name
                args.putString("name", name.getText().toString());

                new RecordingDialog().show(((MainActivity) context).getFragmentManager(), "com");

                // Show the recording dialog
                //((MainActivity) context).setContent(RecordingFragment.class, args);
            }
        });

        show();
    }
}
