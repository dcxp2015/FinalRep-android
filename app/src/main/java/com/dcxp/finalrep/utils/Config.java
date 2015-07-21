package com.dcxp.finalrep.utils;

import android.os.Environment;

/**
 * Created by Daniel on 7/21/2015.
 */
public class Config {
    public static class IO {
        public static final String BASE_AUDIO_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/phrase";
        public static final String AUDIO_FILE_TYPE = ".mp3";
        public static final String SHARED_PREFS_PATH = "mypreferences";
    }

    public static class JSON {
        public static final String PHRASE_ID = "phraseID";
        public static final String PLAYLIST_JSON_FILE = "allmyplaylists.json";
        public static final String PHRASES_JSON_FILE = "allmyphrases.json";
        public static final String NAME = "name";
        public static final String SUBMITTER = "submitter";
        public static final String FILE = "file";
        public static final String PHRASES = "phrases";
    }
}
