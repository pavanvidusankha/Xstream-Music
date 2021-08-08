package com.example.xstream.services;

import com.example.xstream.models.Album;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface AlbumService {
    void addNewAlbum(Album album,long artistId);
    void deleteAlbum(long id);
    List<Album> getAlbums();
    void updateAlbum(long id, String name, String genre, Date date, long artist_id);
    Album getAlbum(long id);
    List<Album> getAlbumsByGenre(String genre);
    List<Album> getAlbumsByReleasedDate(Date date);
    List<Album> getAlbumsByArtist(long id);
    List<Album> getAlbumsBy(String albumName, String genre, String releasedDate) throws ParseException;

}
