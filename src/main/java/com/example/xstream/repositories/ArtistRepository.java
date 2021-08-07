package com.example.xstream.repositories;

import com.example.xstream.models.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends JpaRepository <Artist,Long> {

}
