package com.dcxp.tone.playlist.async;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.dcxp.tone.io.IOSettings;
import com.dcxp.tone.io.IOUtils;
import com.dcxp.tone.playlist.Playlist;
import com.dcxp.tone.playlist.PlaylistUtils;

import org.json.*;

import java.io.IOException;

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
        JSONArray json = new JSONArray();

        for(Playlist playlist : params) {
            JSONObject obj = new JSONObject();

            try {
                obj.put("name", playlist.getName());
                obj.put("phrases", PlaylistUtils.phrasesToJSONArray(playlist));
            } catch(JSONException e) {
                Log.e(TAG, e.toString());
            }

            json.put(obj);
        }

        try {
            IOUtils.write(context, IOSettings.PLAYLIST_JSON_FILE, json.toString());
        } catch(IOException e) {
            Log.e(TAG, e.toString());
            Toast.makeText(context, "Failed to save playlist", Toast.LENGTH_SHORT).show();
        }

        return null;
    }

}
