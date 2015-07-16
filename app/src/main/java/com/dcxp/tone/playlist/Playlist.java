package com.dcxp.tone.playlist;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 7/15/2015.
 */
public class Playlist {
    private String name;
    private List<String> phrases;

    public Playlist() {

    }

    public Playlist(String name, List<String> phrases) {
        this.name = name;
        this.phrases = phrases;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhrases(List<String> phrases) {
        this.phrases = phrases;
    }

    public void addPhrase(String phrase) {
        if(phrases == null) {
            phrases = new ArrayList<String>();
        }

        phrases.add(phrase);
    }

    public List<String> getPhrases() {
        return phrases;
    }

    public String getName() {
        return name;
    }
}
