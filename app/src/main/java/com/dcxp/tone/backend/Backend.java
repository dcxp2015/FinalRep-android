package com.parse.starter;

import android.util.Log;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rush on 7/16/15.
 */
public class Backend {

    public static List<MetaData> getMetadata() {
        // Get objectId - String
        final List<MetaData> list = new ArrayList<MetaData>();

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Phrases");
        query.getInBackground("HDzLa8aQNE", new GetCallback<ParseObject>() {
            public void done(ParseObject object, ParseException e) {
                if (e == null) {
                    String title = object.getString("Title");
                    String submitter = object.getString("Submitter");
                }
                else {
                    Log.v("Error", "An error has occured");
                }
            }
        });

        return list;
    }

    public static void downloadAudio(String objectId) {

    }

    public static class MetaData {
        String title;
        String submitter;
        byte audio;


        public MetaData(String title, String submitter, byte audio) {
            this.title = title;
            this.submitter = submitter;
            this.audio = audio;
        }
    }
}
