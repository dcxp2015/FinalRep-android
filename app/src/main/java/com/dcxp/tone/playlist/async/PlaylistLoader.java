package com.dcxp.tone.playlist.async;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.dcxp.tone.activities.PlaylistActivity;
import com.dcxp.tone.utils.io.IOSettings;
import com.dcxp.tone.utils.io.IOUtils;
import com.dcxp.tone.playlist.Playlist;

import java.util.ArrayList;
import java.util.List;
import org.json.*;

/**
 * Created by Daniel on 7/16/2015.
 */
public class PlaylistLoader extends AsyncTask<Void, Void, List<Playlist>> {
    public static final String TAG = "com.dcxp.tone";
    private Context context;

    public PlaylistLoader(Context context) {
        this.context = context;
    }

    @Override
    protected List<Playlist> doInBackground(Void... params) {
        List<Playlist> playlists = new ArrayList<Playlist>();

        JSONArray root = null;
        try {
            root = new JSONArray(IOUtils.readContents(context, IOSettings.PLAYLIST_JSON_FILE));
        } catch(Exception e) {
            Log.e(TAG, e.toString());
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

    @Override
    protected void onPostExecute(List<Playlist> playlists) {
        super.onPostExecute(playlists);

        for(Playlist playlist : playlists) {
            ((PlaylistActivity) context).onPlaylistImported(playlist);
        }
    }
}
