package com.example.xstream.services;

import com.example.xstream.models.Artist;

import java.util.List;

public interface ArtistService {

    void addNewArtist(Artist artist);
    void deleteArtist(long id);
    List<Artist> getArtists();
    void updateArtist(long id, String name, String country, String genre);
    Artist getArtist(long id);
}
