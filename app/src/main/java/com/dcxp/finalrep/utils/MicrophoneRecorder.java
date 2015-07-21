package com.dcxp.finalrep.utils;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.util.Log;

import java.io.IOException;

/**
 * Created by Daniel on 7/21/2015.
 */
public class MicrophoneRecorder {
    public static final String TAG = "com.dcxp.finalrep.utils";
    private IAudioPlayListener audioPlayListener;
    private MediaRecorder recorder;

    public void start(String fileName) {
        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        recorder.setOutputFile(fileName);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            recorder.prepare();
        } catch (IOException e) {
            Log.e(TAG, e.toString());
        }

        recorder.start();
    }

    public void stop() {
        recorder.stop();
        recorder.release();
    }

    public void play(String filename) {
        final MediaPlayer player = new MediaPlayer();

        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                player.stop();
                player.release();

                if(audioPlayListener != null) {
                    audioPlayListener.onAudioDonePlaying();
                }
            }
        });

        try {
            player.setDataSource(filename);
            player.prepare();
            player.start();
        } catch(Exception e) {
            Log.e(TAG, e.toString());
        }
    }

    public void setAudioPlayListener(IAudioPlayListener audioPlayListener) {
        this.audioPlayListener = audioPlayListener;
    }

    public static interface IAudioPlayListener {
        void onAudioDonePlaying();
    }
}
