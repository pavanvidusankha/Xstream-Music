package com.example.xstream.repositories;

import com.example.xstream.models.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;
@Repository
public interface SongRepository extends JpaRepository<Song,Long> {

    Set<Song> getSongsByAlbums(long album_id);
    Set<Song> getSongsByGenre(String genre);
    Set<Song> getSongsByArtist(String genre);


}
