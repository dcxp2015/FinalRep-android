package com.dcxp.finalrep.utils;


import com.dcxp.finalrep.activity.MainActivity;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;


/**
 * Created by dannyflax on 7/21/15.
 */

public class ParseUtil {
    private static HashMap<String, File> audioCache = new HashMap<String, File>();

    public static void getAllPhrases(final ParseUtilCallback cb){
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Phrases");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {
                    ArrayList<PhraseObject> phrases = new ArrayList<>();
                    for (ParseObject object : list) {
                        PhraseObject phrase = new PhraseObject();

                        phrase.audioRef = object.getParseFile("Audio");
                        phrase.objectId = object.getObjectId();
                        phrase.submitter = object.getString("Submitter");
                        phrase.title = object.getString("Title");

                        phrases.add(phrase);
                    }
                    cb.success(phrases);
                } else {
                    cb.failure(e);
                }
            }
        });
    }

    public static void getAudioFileForPhrase(final PhraseObject phrase, final File cacheDir, final ParseUtilCallback cb){
        if(audioCache.containsKey(phrase.objectId)){
            cb.success(audioCache.get(phrase.objectId));
        }
        else{
            File tempMp3 = null;
            phrase.audioRef.getDataInBackground(new GetDataCallback() {
                @Override
                public void done(byte[] bytes, ParseException e) {
                    if(e == null){
                        try {
                            File tempMp3 = File.createTempFile("sound", "mp3", cacheDir);
                            tempMp3.deleteOnExit();
                            FileOutputStream fos = new FileOutputStream(tempMp3);
                            fos.write(bytes);
                            fos.close();
                            audioCache.put(phrase.objectId, tempMp3);
                            cb.success(tempMp3);
                        } catch (IOException e2){
                            cb.failure(e2);
                        }
                    }
                    else{
                        cb.failure(e);
                    }
                }
            });
        }
    }
}
