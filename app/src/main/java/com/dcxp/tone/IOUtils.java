package com.dcxp.tone;

import android.content.Context;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 * Created by Daniel on 7/16/2015.
 */
public class IOUtils {

    public static String readContents(Context context, String fname) throws IOException {
        InputStream bufferedInputStream = context.getApplicationContext().openFileInput(fname);

        byte[] buffer = new byte[bufferedInputStream.available()];

        bufferedInputStream.read(buffer);

        bufferedInputStream.close();

        return new String(buffer);
    }

    public static void write(Context context, String fname, String data) throws IOException {
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.getApplicationContext().openFileOutput(fname, Context.MODE_PRIVATE));
        outputStreamWriter.write(data);
        outputStreamWriter.close();
    }

}
