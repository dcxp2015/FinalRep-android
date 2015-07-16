package com.dcxp.tone.playlist;

import org.json.JSONArray;

/**
 * Created by Daniel on 7/16/2015.
 */
public class PlaylistUtils {

    public static synchronized JSONArray phrasesToJSONArray(Playlist playlist) {
        JSONArray phrases = new JSONArray();

        if(playlist.getPhrases() != null) {
            for (String phrase : playlist.getPhrases()) {
                phrases.put(phrase);
            }
        }

        return phrases;
    }
}
