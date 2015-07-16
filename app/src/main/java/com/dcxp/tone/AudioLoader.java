package com.dcxp.tone;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.dcxp.tone.playlist.Playlist;

import java.util.ArrayList;
import java.util.List;
import org.json.*;

/**
 * Created by Daniel on 7/16/2015.
 */
public class AudioLoader extends AsyncTask<Void, Void, List<Playlist>> {
    public static final String TAG = "com.dcxp.tone";
    private Context context;

    public AudioLoader(Context context) {
        this.context = context;
    }

    @Override
    protected List<Playlist> doInBackground(Void... params) {
        List<Playlist> playlists = new ArrayList<Playlist>();

        JSONArray root = null;
        try {
            root = new JSONArray(IOUtils.readContents(context, "playlists.json"));
        } catch(Exception e) {
            Log.e(TAG, e.toString());
            Toast.makeText(context, "An error has occurred", Toast.LENGTH_SHORT);
        }

        int length = root.length();
        for(int i = 0; i < length; i++) {
            try {
                playlists.add(parsePlaylist(root.getJSONObject(i)));
            } catch(JSONException e) {
                Log.e(TAG, e.toString());
            }
        }

        return playlists;
    }

    private Playlist parsePlaylist(JSONObject obj) throws JSONException{
        Playlist playlist = new Playlist();

        playlist.setName(obj.getString("name"));

        JSONArray phrases = obj.getJSONArray("phrases");

        int length = phrases.length();
        for(int i = 0; i < length; i++) {
            playlist.addPhrase(phrases.getString(i));
        }

        return playlist;
    }
}
