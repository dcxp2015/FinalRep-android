package com.dcxp.tone.dialogs;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.dcxp.tone.R;
import com.dcxp.tone.activities.MainActivity;
import com.dcxp.tone.fragments.ExploreFragment;

/**
 * Created by Daniel on 7/19/2015.
 */
public class PhraseCreationDialog extends AlertDialog.Builder {

    public PhraseCreationDialog(final Activity context) {
        super(context);

        setTitle("Add phrase");

        View view = LayoutInflater.from(context).inflate(R.layout.phrase_creation_dialog, null, false);

        final RadioButton record = (RadioButton) view.findViewById(R.id.rb_record);
        final RadioButton download = (RadioButton) view.findViewById(R.id.rb_download);

        setView(view);

        setPositiveButton(context.getString(R.string.next), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(!record.isChecked()) {
                    // User wants to record
                    new RecordingDialog().show(context.getFragmentManager(), null);
                }
                else {
                    // Update the action bar title
                    ((MainActivity) context).getSupportActionBar().setTitle("Explore");
                    // User wants to download, send them to the content page
                    ((MainActivity) context).setContent(ExploreFragment.class, null);
                }
            }
        });

        show();
    }
}
