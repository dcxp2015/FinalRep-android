package com.dcxp.finalrep.dialogs;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.dcxp.finalrep.R;
import com.dcxp.finalrep.activity.MainActivity;

/**
 * Created by Daniel on 7/21/2015.
 */
public class PhraseNameDialog extends AlertDialog.Builder {

    public PhraseNameDialog(final MainActivity context, final INameCreationListener listener) {
        super(context);

        setTitle("Name of your phrase");

        final EditText name = new EditText(context);
        name.setHint("Name");

        setView(name);

        setPositiveButton("Next", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        final AlertDialog d = create();
        d.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                d.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String n = name.getText().toString();

                        if (n.trim().length() == 0){
                            Toast.makeText(getContext(), "Please enter a name", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        listener.onNameSubmitted(n);
                        d.dismiss();
                    }
                });
            }
        });

        d.show();
    }

    public static interface INameCreationListener {
        void onNameSubmitted(String name);
    }

}
