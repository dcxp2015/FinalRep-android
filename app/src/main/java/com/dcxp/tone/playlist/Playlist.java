package com.dcxp.tone.playlist;

import com.dcxp.tone.Phrase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 7/15/2015.
 */
public class Playlist {
    public static final String NAME = "name";
    public static final String PHRASES = "phrases";

    private String name;
    private String oldName;
    private List<Phrase> phrases;

    public Playlist() {

    }

    public Playlist(String name, List<Phrase> phrases) {
        this.name = name;
        this.phrases = phrases;
    }

    public void setOldName(String oldName) {
        this.oldName = oldName;
    }

    public String getOldName() {
        return oldName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhrases(List<Phrase> phrases) {
        this.phrases = phrases;
    }

    public void addPhrase(Phrase phrase) {
        if(phrases == null) {
            phrases = new ArrayList<Phrase>();
        }

        phrases.add(phrase);
    }

    public List<Phrase> getPhrases() {
        return phrases;
    }

    public String getName() {
        return name;
    }
}
