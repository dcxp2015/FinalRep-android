package com.dcxp.finalrep.dialogs;

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
import android.widget.Toast;

import com.dcxp.finalrep.R;
import com.dcxp.finalrep.activity.MainActivity;
import com.dcxp.finalrep.models.Phrase;
import com.dcxp.finalrep.utils.MicrophoneRecorder;

/**
 * Created by Daniel on 7/19/2015.
 */
public class RecordingDialog extends DialogFragment implements PhraseNameDialog.INameCreationListener {
    private static final String TEMP = "temp";
    private Phrase builtPhrase;
    private IRecordingDialogListener listener;
    private MicrophoneRecorder recorder;

    public RecordingDialog() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        listener = (IRecordingDialogListener) getActivity();

       / recorder = new MicrophoneRecorder();

        View inflatedView = inflater.inflate(R.layout.dialog_recording, container, false);

        getDialog().setTitle("Record a custom phrase");

        final Button recordBtn = (Button) inflatedView.findViewById(R.id.btn_record);
        recordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String record = getString(R.string.record);
                boolean isRecording = !recordBtn.getText().toString().equals(record);

                if (isRecording) {
                    recordBtn.setText(record);
                    showConfirmDialog();
                    stopRecording();
                } else {
                    recordBtn.setText(getString(R.string.stop));
                    startRecording();
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
                // Prompt the user for the name they want
                new PhraseNameDialog((MainActivity) getActivity(), RecordingDialog.this);
                dismiss();
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

    @Override
    public void onNameSubmitted(String name) {
        RecordingDialog.this.dismiss();

        // Build a phrase based on the provided data
        builtPhrase = new Phrase();
        builtPhrase.setName(name);
        builtPhrase.setSubmitter("You");

        listener.onPhraseRecorded(builtPhrase);
    }

    private void startRecording() {
       // recorder.start(TEMP);
    }

    private void stopRecording() {
       // recorder.stop();
    }

    public static interface IRecordingDialogListener {
        void onPhraseRecorded(Phrase phrase);
    }
}