package com.example.xstream.repositories;

import com.example.xstream.models.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtistRepository extends JpaRepository <Artist,Long> {
        Artist findArtistByName(String name);
        List<Artist> findArtistsByName(String name);
}
