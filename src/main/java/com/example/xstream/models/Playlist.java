package com.example.xstream.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Data
@NoArgsConstructor
@Setter
@Getter
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
    @Column(name = "playlist_id")
    private long id;
    private String name;

    @JsonIgnore
    @ManyToMany
    private Set<Song> playlistSongs= new HashSet<Song>();

   @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    @ManyToOne
   
    private User user;

    public Playlist(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Playlist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public Set<Song> getPlaylistSongs() {
//        return playlistSongs;
//    }
//
//    public void setPlaylistSongs(Set<Song> playlistSongs) {
//        this.playlistSongs = playlistSongs;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
}
