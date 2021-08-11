package com.example.xstream.services;

import com.example.xstream.models.Playlist;
import java.util.List;

public interface PlaylistService {

    void addNewPlaylist(Playlist playlist, long userId);
    void deletePlaylist(long id);
    List<Playlist> getPlaylists();
    void updatePlaylist(long id, String name);
    Playlist getPlaylist(long id);
    List<Playlist>  getPlaylistByUser(long userId);
    List<Playlist>  getPlaylistByName(String name);

    //Playlist Songs

    void AddSongToPlaylist(long playlistId, long songId);
    void RemoveSongFromPlaylist(long playlistId, long songId);

}
