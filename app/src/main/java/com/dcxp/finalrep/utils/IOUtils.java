package com.dcxp.finalrep.utils;

import android.content.Context;
import android.util.Log;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;

/**
 * Created by Daniel on 7/16/2015.
 */
public class IOUtils {

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

    public static String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

}
