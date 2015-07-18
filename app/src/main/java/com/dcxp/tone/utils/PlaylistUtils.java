package com.dcxp.tone.utils;

import com.dcxp.tone.Phrase;
import com.dcxp.tone.playlist.Playlist;

import org.json.JSONArray;
import org.json.*;

/**
 * Created by Daniel on 7/16/2015.
 */
public class PlaylistUtils {

    public static synchronized JSONArray phrasesToJSONArray(Playlist playlist) throws JSONException {
        JSONArray phrases = new JSONArray();

        if(playlist.getPhrases() != null) {
            for (Phrase phrase : playlist.getPhrases()) {
                JSONObject object = new JSONObject();
                object.put(Phrase.NAME, phrase.getName());
                object.put(Phrase.SUBMITTER, phrase.getSubmitter());
                phrases.put(object);
            }
        }

        return phrases;
    }
}
