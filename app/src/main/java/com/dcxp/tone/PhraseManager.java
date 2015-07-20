package com.dcxp.tone;

import android.util.Log;

import java.util.List;

/**
 * Created by Daniel on 7/19/2015.
 */
public class PhraseManager {
    private static List<Phrase> phrases;

    public static void setPhrases(List<Phrase> phrases) {
        PhraseManager.phrases = phrases;
    }

    public static void addPhrase(Phrase phrase) {
        phrases.add(phrase);
    }

    public static void removePhrase(Phrase phrase) {
        phrases.remove(phrase);
    }

    public static Phrase getPhraseByName(String name) {
        for(Phrase phrase : phrases) {
            if(phrase.getName().equals(name)) {
                return phrase;
            }
        }

        return null;
    }

    public static List<Phrase> getPhrases() {
        return phrases;
    }
}
