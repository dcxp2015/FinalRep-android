package com.dcxp.tone.playlist;

/**
 * Created by Daniel on 7/15/2015.
 */
public interface IPlaylistListener {
    void onPlaylistCreated(Playlist playlist);
    void onPlaylistImported(Playlist playlist);
}
