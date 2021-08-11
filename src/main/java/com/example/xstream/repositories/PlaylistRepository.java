package com.example.xstream.repositories;

import com.example.xstream.models.Playlist;
import com.example.xstream.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist,Long> {
    List<Playlist> getPlaylistsByUser(User user);
    List<Playlist> getPlaylistsByName(String name);
}
