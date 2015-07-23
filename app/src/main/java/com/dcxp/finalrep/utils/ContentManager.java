package com.dcxp.finalrep.utils;

import android.content.Context;
import android.util.Log;

import com.dcxp.finalrep.models.Phrase;
import com.dcxp.finalrep.models.Playlist;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 7/20/2015.
 */
public class ContentManager {
    public static final String TAG = "com.dcxp.finalrep.utils";

    private static JSONArray phrasesToJSONArray(List<Phrase> phrases) throws JSONException {
        JSONArray phraseObject = new JSONArray();

        for(Phrase phrase : phrases) {
            JSONObject object = new JSONObject();
            object.put(Config.JSON.NAME, phrase.getName());
            object.put(Config.JSON.SUBMITTER, phrase.getSubmitter());
            object.put(Config.JSON.FILE, phrase.getFile());
            phraseObject.put(object);
        }


        return phraseObject;
    }

    public static List<Playlist> loadPlaylists(Context context) {
        JSONArray root = null;
        List<Playlist> playlists = new ArrayList<Playlist>();

        try {
            root = new JSONArray(IOUtils.readContents(context, Config.JSON.PLAYLIST_JSON_FILE));
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

    public static List<Phrase> loadPhrases(Context context) {
        JSONArray root = null;

        try {
            root = new JSONArray(IOUtils.readContents(context, Config.JSON.PHRASES_JSON_FILE));
        } catch(Exception e) {
            Log.e(TAG, e.toString());
        }

        return parsePhrases(root);
    }

    public static void savePhrases(Context context, List<Phrase> phrases) {
        try {
            IOUtils.write(context, Config.JSON.PHRASES_JSON_FILE, phrasesToJSONArray(phrases).toString());
        } catch(Exception e) {
            Log.e(TAG, e.toString());
        }
    }

    public static void savePlaylists(Context context, List<Playlist> playlists) {
        JSONArray json = new JSONArray();

        for(Playlist playlist : playlists) {
            JSONObject obj = new JSONObject();

            try {
                obj.put(Config.JSON.NAME, playlist.getName());
                obj.put(Config.JSON.PHRASES, phrasesToJSONArray(playlist.getPhrases()));
            } catch(JSONException e) {
                Log.e(TAG, e.toString());
            }

            json.put(obj);
        }

        try {
            IOUtils.write(context, Config.JSON.PLAYLIST_JSON_FILE, json.toString());
        } catch(IOException e) {
            Log.e(TAG, e.toString());
        }
    }

    private static List<Phrase> parsePhrases(JSONArray o) {
        List<Phrase> phraseList = null;

        try {
            phraseList = new ArrayList<Phrase>();

            int length = o.length();
            for (int i = 0; i < length; i++) {
                JSONObject pobj = o.getJSONObject(i);
                phraseList.add(new Phrase(pobj.getString(Config.JSON.NAME), pobj.getString(Config.JSON.SUBMITTER), pobj.getString(Config.JSON.FILE)));
            }

            return phraseList;
        } catch(JSONException e) {
            Log.e(TAG, e.toString());
        }

        return phraseList;
    }

    private static Playlist parsePlaylist(JSONObject obj) throws JSONException{
        Playlist playlist = new Playlist();

        playlist.setName(obj.getString(Config.JSON.NAME));

        List<Phrase> parsedPhrases = parsePhrases(obj.getJSONArray(Config.JSON.PHRASES));

        for(Phrase p : parsedPhrases) {
            playlist.addPhrase(p);
        }

        return playlist;
    }
}
