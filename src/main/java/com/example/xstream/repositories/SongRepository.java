package com.example.xstream.repositories;

import com.example.xstream.models.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface SongRepository extends JpaRepository<Song,Long> {

    Set<Song> getSongsByAlbums(long album_id);
    Set<Song> getSongsByGenre(String genre);
    Set<Song> getSongsByArtist(String genre);


}
