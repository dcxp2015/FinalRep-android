package com.dcxp.finalrep.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 7/20/2015.
 */
public class Playlist {
    private String name, oldName;
    private List<Phrase> phrases;

    public Playlist() {
        phrases = new ArrayList<Phrase>();
    }

    public Playlist(String name, List<Phrase> phrases) {
        this.name = name;
        this.phrases = phrases;
    }

    public String getOldName() {
        return oldName;
    }

    public void setOldName(String oldName) {
        this.oldName = oldName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhrases(List<Phrase> phrases) {
        this.phrases = phrases;
    }

    public void addPhrase(Phrase phrase) {
        phrases.add(phrase);
    }

    public void removePhrase(Phrase phrase) {
        phrases.remove(phrase);
    }

    public String getName() {
        return name;
    }

    public List<Phrase> getPhrases() {
        return phrases;
    }
}
