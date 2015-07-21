package com.dcxp.finalrep.utils;


import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.io.File;
import java.util.ArrayList;


/**
 * Created by dannyflax on 7/21/15.
 */

public class ParseUtil {
    public static void getAllPhrases(final ParseUtilCallback cb){
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Phrases");

        query.getInBackground("osvwOZS8NV", new GetCallback<ParseObject>() {
            public void done(ParseObject object, ParseException e) {
                if (e == null) {
                    cb.success(object);
                } else {
                    cb.failure(e);
                }
            }
        });
    }
}

class PhraseObject {
    public String title;
    public String submitter;
    public String objectId;
    public String audioRef;
    public File audioFile;
}