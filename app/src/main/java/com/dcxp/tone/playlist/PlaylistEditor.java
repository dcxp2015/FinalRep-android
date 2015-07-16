package com.dcxp.tone.playlist;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.dcxp.tone.io.IOSettings;
import com.dcxp.tone.io.IOUtils;

import org.json.*;

import java.io.IOException;

/**
 * Created by Daniel on 7/16/2015.
 */
public class PlaylistEditor extends AsyncTask<Playlist, Void, Void> {
    public static final String TAG = "com.dcxp.tone";
    private Context context;

    public PlaylistEditor(Context context) {
        this.context = context;
    }

    @Override
    protected Void doInBackground(Playlist... params) {
        try {
            String json = IOUtils.readContents(context, IOSettings.PLAYLIST_JSON_FILE);
            JSONArray obj = new JSONArray(json);

            // New name of the playlist
            String newName = params[0].getName();

            int length = obj.length();
            for(int i = 0; i < length; i++) {
                JSONObject playlist = obj.getJSONObject(i);

                Log.d(TAG + "C", "New Name: " + newName);
                Log.d(TAG + "C", "Current Name: " + playlist.getString("name"));

                // If this playlist has the same name as the updated one, update it. There is only one playlist in the param array
                if(playlist.getString("name").equals(params[0].getOldName())) {
                    // Update the playlist json object
                    playlist.put("name", newName);
                    playlist.put("phrases", PlaylistUtils.phrasesToJSONArray(params[0]));
                }
            }

            // Now rewrite to the json file overwriting data
            IOUtils.write(context, IOSettings.PLAYLIST_JSON_FILE, obj.toString());
        } catch(Exception e) {
            Log.e(TAG, e.toString());
        }

        return null;
    }
}
