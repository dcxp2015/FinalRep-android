package com.dcxp.tone.playlist;

/**
 * Created by Daniel on 7/15/2015.
 */
public interface IPlaylistListener {
    void onPlaylistCreated(Playlist playlist);
    void onPlaylistImported(Playlist playlist);
    void onPlaylistEdited(Playlist playlist);
    void onPlaylistRemoved(Playlist playlist);
    boolean isPlaylistNameTaken(String name);
}
