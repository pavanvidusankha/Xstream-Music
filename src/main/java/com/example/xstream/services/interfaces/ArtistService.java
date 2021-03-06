package com.example.xstream.services.interfaces;

import com.example.xstream.models.Artist;

import java.util.List;

public interface ArtistService {

    void addNewArtist(Artist artist);
    void deleteArtist(long id);
    List<Artist> getArtists();
    void updateArtist(long id, String name, String country, String genre);
    Artist getArtist(long id);
    List<Artist> findArtistsByName(String name);
}
