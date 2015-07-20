package com.dcxp.tone.dialogs;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.preference.DialogPreference;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.dcxp.tone.R;

/**
 * Created by Daniel on 7/19/2015.
 */
public class RecordingDialog extends DialogFragment {

    public RecordingDialog() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflatedView = inflater.inflate(R.layout.record_dialog, container, false);

     //   final ProgressBar progressBar = (ProgressBar) inflatedView.findViewById(R.id.pb_recording_time_remaining);

     //   progressBar.setVisibility(View.INVISIBLE);

        getDialog().setTitle("Record a Phrase");

        final Button recordBtn = (Button) inflatedView.findViewById(R.id.btn_record);
        recordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String record = getString(R.string.record);
                boolean isRecording = !recordBtn.getText().toString().equals(record);

                if (isRecording) {
                    recordBtn.setText(record);
                    showConfirmDialog();
                    //   progressBar.setVisibility(View.INVISIBLE);
                } else {
                    recordBtn.setText(getString(R.string.stop));
                    // progressBar.setVisibility(View.VISIBLE);
                }
            }
        });

        return inflatedView;
    }

    private void showConfirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Keep this recording?");

        builder.setPositiveButton("Keep", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                new PhraseNameDialog(getActivity());
                dismiss();
                RecordingDialog.this.dismiss();
            }
        });

        builder.setNegativeButton("Discard", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dismiss();
            }
        });

        builder.show();
    }
}
