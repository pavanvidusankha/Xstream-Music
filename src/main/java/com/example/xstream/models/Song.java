package com.example.xstream.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "songs")
public class Song {

    @Id
    @SequenceGenerator(
            name="song_sequence",
            sequenceName="song_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator="song_sequence"
    )
    @Column(name = "song_id")
    private long id;
    private String name;
    private Integer duration;
    private String genre;

    @ManyToMany(
            cascade = {

                    CascadeType.MERGE
            })
    @JoinTable(
            name = "album_songs",
            joinColumns = {
                    @JoinColumn(name = "song_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "album_id")
            }
    )
    private Set<Album> albums =new HashSet<>();
    @ManyToOne
    private Artist artist;


    public Song() {
    }

    public Song(String name, Integer duration, String genre) {
        this.name = name;
        this.duration = duration;
        this.genre = genre;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Set<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(Set<Album> albums) {
        this.albums = albums;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }
}