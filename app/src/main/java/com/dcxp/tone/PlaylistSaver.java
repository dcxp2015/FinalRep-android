package com.dcxp.tone;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.dcxp.tone.playlist.Playlist;
import org.json.*;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Daniel on 7/16/2015.
 */
public class PlaylistSaver extends AsyncTask<Playlist, Void, Void> {
    public static final String TAG = "com.dcxp.tone";
    private Context context;

    public PlaylistSaver(Context context) {
        this.context = context;
    }

    @Override
    protected Void doInBackground(Playlist... params) {
        JSONArray json = null;

        try {
            json = new JSONArray(IOUtils.readContents(context, "phrases/playlists.json"));
        } catch(Exception e) {
            Log.e(TAG, e.toString());
            Toast.makeText(context, "An error has occurred", Toast.LENGTH_SHORT).show();
        }

        for(Playlist playlist : params) {
            JSONObject obj = new JSONObject();

            try {
                obj.put("name", playlist.getName());

                JSONArray phrases = new JSONArray();

                for(String phrase : playlist.getPhrases()) {
                    phrases.put(phrase);
                }

                obj.put("phrases", phrases);
            } catch(JSONException e) {
                Log.e(TAG, e.toString());
            }

            json.put(obj);
        }

        try {
            IOUtils.write(context, "phrases/playlists.json", json.toString());
        } catch(IOException e) {
            Log.e(TAG, e.toString());
            Toast.makeText(context, "Failed to save playlist", Toast.LENGTH_SHORT).show();
        }

        return null;
    }

}
