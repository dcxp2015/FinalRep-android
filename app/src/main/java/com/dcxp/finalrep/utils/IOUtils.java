package com.dcxp.finalrep.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;

/**
 * Created by Daniel on 7/16/2015.
 */
public class IOUtils {

    public static void incrementPhraseID(Context context) {
        change(context, 1);
    }

    public static void decrementPhraseID(Context context) {
        change(context, -1);
    }

    private static void change(Context context, int am) {
        SharedPreferences preferences = context.getSharedPreferences(Config.IO.SHARED_PREFS_PATH, Context.MODE_PRIVATE);
        SharedPreferences.Editor e = preferences.edit();
        e.putLong(Config.JSON.PHRASE_ID, preferences.getLong(Config.JSON.PHRASE_ID, 0) + am);
        e.apply();
    }

    public static long getNextPhraseID(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(Config.IO.SHARED_PREFS_PATH, Context.MODE_PRIVATE);

        return preferences.getLong(Config.JSON.PHRASE_ID, 0);
    }

    public static String readContents(Context context, String fname) throws IOException {
        try {
            InputStream bufferedInputStream = context.getApplicationContext().openFileInput(fname);

            byte[] buffer = new byte[bufferedInputStream.available()];

            bufferedInputStream.read(buffer);

            bufferedInputStream.close();

            return new String(buffer);

        } catch(FileNotFoundException e) {
            Log.e("com.dcxp.tone", e.toString());

            // File not found, create it
            write(context, fname, "[]");

            // Now read
            readContents(context, fname);
        }

        return null;
    }

    public static void write(Context context, String fname, String data) throws IOException {
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.getApplicationContext().openFileOutput(fname, Context.MODE_PRIVATE));
        outputStreamWriter.write(data);
        outputStreamWriter.close();
    }

}
