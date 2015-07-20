package com.dcxp.tone.fragments;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.dcxp.tone.R;

/**
 * Created by Daniel on 7/19/2015.
 */
public class RecordingFragment extends Fragment {
    private static final int MAX_RECORDING_LENGTH = 5;
    private int progress;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View inflatedView = inflater.inflate(R.layout.fragment_record, container, false);

        final ProgressBar progressBar = (ProgressBar) inflatedView.findViewById(R.id.pb_recording_time_remaining);
        progressBar.setMax(MAX_RECORDING_LENGTH);
        progressBar.setProgress(0);
        progressBar.setIndeterminate(false);

        final Button recordBtn = (Button) inflatedView.findViewById(R.id.btn_record);
        recordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String record = getString(R.string.record);
                boolean isRecording = !recordBtn.getText().toString().equals(record);

                if(isRecording) {
                    recordBtn.setText(record);
                }
                else {
                    recordBtn.setText(getString(R.string.stop));
                }
            }
        });

        // Notify the user that the recording can only be so long
        showRecordingLengthDialog();

        return inflatedView;
    }

    private void showRecordingLengthDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("All ready to record!");
        builder.setMessage("Your recording may not be more than " + MAX_RECORDING_LENGTH + " seconds");
        builder.setPositiveButton("Got it", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.show();
    }
}
