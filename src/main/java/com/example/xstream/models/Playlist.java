package com.example.xstream.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "playlists")
public class Playlist {
    @SequenceGenerator(
            name="playlist_sequence",
            sequenceName="playlist_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator="playlist_sequence"
    )
    @Id
    private long id;
    private String name;
    @ManyToMany
    private Set<Song> playlistSongs= new HashSet<Song>();
    @ManyToOne
    private User user;

    public Playlist() {
    }

    public Playlist(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Song> getPlaylistSongs() {
        return playlistSongs;
    }

    public void setPlaylistSongs(Set<Song> playlistSongs) {
        this.playlistSongs = playlistSongs;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
