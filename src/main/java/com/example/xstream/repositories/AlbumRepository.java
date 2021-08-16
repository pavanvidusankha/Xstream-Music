package com.example.xstream.repositories;

import com.example.xstream.models.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {
    List<Album> findAlbumsByArtist_Id(long id);

    List<Album> findAlbumsByArtistNameIgnoreCase(String Name);

    List<Album> findAlbumsByGenreIgnoreCase(String genre);

    List<Album> findAlbumsByReleasedDateIsContaining(Date date);

    List<Album> findAlbumsByReleasedDateBetweenOrGenreOrNameIgnoreCase(Date releasedDate, Date yearAfterReleasedDate, String genre, String name);

    Album findAlbumByName(String name);
}
