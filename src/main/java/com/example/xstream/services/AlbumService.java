package com.example.xstream.services;

import com.example.xstream.models.Album;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface AlbumService {
    void addNewAlbum(Album album,long artistId);
    void deleteAlbum(long id);
    List<Album> getAlbums();
    void updateAlbum(long id, String name, String genre, Date date, long artist_id);
    Album getAlbum(long id);
    List<Album> getAlbumsByGenre(String genre);
    Set<Album> getAlbumsByArtist(long id);

}
