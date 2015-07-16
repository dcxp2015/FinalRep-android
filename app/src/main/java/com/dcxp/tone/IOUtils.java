package com.dcxp.tone;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 * Created by Daniel on 7/16/2015.
 */
public class IOUtils {

    public static String readContents(Context context, String file) throws IOException {
        InputStream bufferedInputStream = context.getAssets().open(file);

        byte[] buffer = new byte[bufferedInputStream.available()];

        bufferedInputStream.read(buffer);

        return new String(buffer);
    }

    public static void write(Context context, String file, String data) throws IOException {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(file, Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

}
