package com.dcxp.finalrep.utils;

import android.content.Context;
import android.content.ContextWrapper;

import com.dcxp.finalrep.models.Phrase;
import com.dcxp.finalrep.models.Playlist;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 7/20/2015.
 */
public class PhraseManager extends ContextWrapper {
    private List<Phrase> phrases;
    private List<Playlist> playlists;
    private List<IPlaylistListener> listeners;

    public static interface IPlaylistListener {
        void onPlaylistCreated(Playlist playlist);
        void onPlaylistEdited(Playlist playlist);
        void onPlaylistViewed(Playlist playlist);
        void onPlaylistDeleted(Playlist playlist);
    }

    public PhraseManager(Context context) {
        super(context);
        phrases = new ArrayList<Phrase>();
        playlists = new ArrayList<Playlist>();
        listeners = new ArrayList<IPlaylistListener>();

        // TODO: ASYNC LOADING
        phrases = ContentManager.loadPhrases(this);
        playlists = ContentManager.loadPlaylists(this);
    }

    public void register(IPlaylistListener listener) {
        listeners.add(listener);
    }

    public void unregister(IPlaylistListener listener) {
        listeners.remove(listener);
    }

    public void addPlaylist(Playlist playlist) {
        playlists.add(playlist);

        for(IPlaylistListener playlistListener : listeners) {
            playlistListener.onPlaylistCreated(playlist);
        }
    }

    public void removePlaylist(Playlist playlist) {
        playlists.remove(playlist);

        for(IPlaylistListener playlistListener : listeners) {
            playlistListener.onPlaylistDeleted(playlist);
        }
    }

    public boolean isPhraseNameTaken(String phrase) {
        for(Phrase ph : phrases) {
            if(ph.getName().equals(phrase)) {
                return true;
            }
        }

        return false;
    }

    public boolean isPlaylistNameTaken(String playlist) {
        for(Playlist pl : playlists) {
            if(pl.getName().equals(playlist)) {
                return true;
            }
        }

        return false;
    }

    public Phrase getPhraseByName(String name) {
        for(Phrase phrase : phrases) {
            if(phrase.getName().equals(name)) {
                return phrase;
            }
        }

        return null;
    }

    public void savePhrases() {
        ContentManager.savePhrases(this, phrases);
    }

    public void addPhrase(Phrase phrase) {
        phrases.add(phrase);
    }

    public int indexOfPlaylist(Playlist playlist) {
        return playlists.indexOf(playlist);
    }

    public List<Phrase> getPhrases() {
        return phrases;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }
}
