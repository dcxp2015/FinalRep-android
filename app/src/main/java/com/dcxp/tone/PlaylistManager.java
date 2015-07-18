package com.dcxp.tone;

import android.content.Context;
import android.util.Log;

import com.dcxp.tone.playlist.Playlist;
import com.dcxp.tone.utils.IOSettings;
import com.dcxp.tone.utils.IOUtils;
import com.dcxp.tone.utils.PlaylistUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 7/17/2015.
 */
public class PlaylistManager {
    private static List<Playlist> playlists = new ArrayList<Playlist>();
    public static final String TAG = "com.dcxp.tone";

    public static boolean isPlaylistNameTaken(String name) {
        for(Playlist playlist : playlists) {
            if(playlist.getName().equals(name)) {
                return true;
            }
        }

        return false;
    }

    public static void load(Context context) {
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
    }

    public static void save(Context context) {
        JSONArray json = new JSONArray();

        for(Playlist playlist : PlaylistManager.getPlaylists()) {
            JSONObject obj = new JSONObject();

            try {
                obj.put(Playlist.NAME, playlist.getName());
                obj.put(Playlist.PHRASES, PlaylistUtils.phrasesToJSONArray(playlist));
            } catch(JSONException e) {
                Log.e(TAG, e.toString());
            }

            json.put(obj);
        }

        Log.d(TAG+"D", json.toString()+"");

        try {
            IOUtils.write(context, IOSettings.PLAYLIST_JSON_FILE, json.toString());
        } catch(IOException e) {
            Log.e(TAG, e.toString());
        }
    }

    private static Playlist parsePlaylist(JSONObject obj) throws JSONException{
        Playlist playlist = new Playlist();

        playlist.setName(obj.getString("name"));

        JSONArray phrases = obj.getJSONArray("phrases");

        int length = phrases.length();
        for(int i = 0; i < length; i++) {
            JSONObject pobj = phrases.getJSONObject(i);

            Phrase p = new Phrase(pobj.getString("name"), pobj.getString("submitter"));

            playlist.addPhrase(p);
        }

        return playlist;
    }

    public static int indexOf(Playlist playlist) {
        return playlists.indexOf(playlist);
    }

    public static void addPlaylist(Playlist playlist) {
        playlists.add(playlist);
    }

    public static void removePlaylist(Playlist playlist) {
        playlists.remove(playlist);
    }

    public static Playlist getPlaylist(int pos) {
        return playlists.get(pos);
    }

    public static List<Playlist> getPlaylists() {
        return playlists;
    }
}
