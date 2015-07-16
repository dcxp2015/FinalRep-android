package com.dcxp.tone.playlist;

import org.json.JSONArray;

/**
 * Created by Daniel on 7/16/2015.
 */
public class PlaylistUtils {

    public static JSONArray phrasesToJSONArray(Playlist playlist) {
        JSONArray phrases = new JSONArray();

        for(String phrase : playlist.getPhrases()) {
            phrases.put(phrase);
        }

        return phrases;
    }
}
