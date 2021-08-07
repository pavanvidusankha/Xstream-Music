package com.example.xstream.repositories;

import com.example.xstream.models.Album;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Set;

public interface AlbumRepository extends JpaRepository<Album,Long> {
    Set<Album> findAlbumsByArtist_Id(long id);
    Set<Album> findAlbumsByArtistName(String Name);
    Set<Album> findAlbumsByGenre(String genre);
    Set<Album> findAlbumsByReleasedDate(Date date);


}
