package com.dcxp.tone.playlist;

import java.util.List;

/**
 * Created by Daniel on 7/15/2015.
 */
public class Playlist {
    private String name;
    private List<String> phrases;

    public Playlist(String name, List<String> phrases) {
        this.name = name;
        this.phrases = phrases;
    }

    public List<String> getPhrases() {
        return phrases;
    }

    public String getName() {
        return name;
    }
}
