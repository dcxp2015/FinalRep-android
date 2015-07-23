package com.dcxp.finalrep.dialogs;

import android.app.Activity;
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
import com.dcxp.finalrep.utils.Config;
import com.dcxp.finalrep.utils.IOUtils;
import com.dcxp.finalrep.utils.MicrophoneRecorder;

/**
 * Created by Daniel on 7/19/2015.
 */
public class RecordingDialog extends DialogFragment implements PhraseNameDialog.INameCreationListener {
    private static final String PLAY = "Play";
    private static final String PAUSE = "Pause";

    private MainActivity context;
    private Phrase builtPhrase;
    private IRecordingDialogListener listener;
    private MicrophoneRecorder recorder;

    public RecordingDialog() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context = (MainActivity) getActivity();
        listener = (IRecordingDialogListener) context;

        builtPhrase = new Phrase();
        recorder = new MicrophoneRecorder();

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
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Keep this recording?");

        builder.setPositiveButton("Keep", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Prompt the user for the name they want
                new PhraseNameDialog((MainActivity) context, RecordingDialog.this);
                dismiss();
            }
        });

        builder.setNeutralButton(PLAY, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.setNegativeButton("Discard", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dismiss();
            }
        });

        final AlertDialog d = builder.create();

        d.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                // Set the listener for play, we dont want play to close the window
                final Button play = d.getButton(DialogInterface.BUTTON_NEUTRAL);
                play.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String text = play.getText().toString();

                        if (text.equals(PLAY)) {
                            play.setText(PAUSE);

                            // Now play the audio file
                            recorder.play(Config.IO.BASE_AUDIO_PATH + (IOUtils.getNextPhraseID(context) - 1) + Config.IO.AUDIO_FILE_TYPE);

                            // Be notified when it's done playing
                            recorder.setAudioPlayListener(new MicrophoneRecorder.IAudioPlayListener() {
                                @Override
                                public void onAudioDonePlaying() {
                                    // The audio is done playing, just set the text back to play
                                    play.setText(PLAY);
                                }
                            });

                        } else if (text.equals(PAUSE)) {
                            play.setText(PLAY);

                            // Pause the audio
                            recorder.stop();
                        }
                    }
                });
            }
        });

        d.show();
    }

    @Override
    public void onNameSubmitted(String name) {
        RecordingDialog.this.dismiss();

        // Build a phrase based on the provided data
        builtPhrase.setName(name);
        builtPhrase.setSubmitter("You");

        listener.onPhraseRecorded(builtPhrase);
    }

    private void startRecording() {
        // We are recording a new phrase, increment the ID so the new file will have the right id
        IOUtils.incrementPhraseID(context);

        // Build the new path
        String audioFilePath = Config.IO.BASE_AUDIO_PATH + (IOUtils.getNextPhraseID(context) - 1) + Config.IO.AUDIO_FILE_TYPE;

        // Start recording into the file
        recorder.start(audioFilePath);

        // The current phrase's file is now audioFilePath
        builtPhrase.setFile(audioFilePath);

        // Give the current phrase this path
        builtPhrase.setFile(audioFilePath);
    }

    private void stopRecording() {
        recorder.stop();
    }

    public static interface IRecordingDialogListener {
        void onPhraseRecorded(Phrase phrase);
    }
}