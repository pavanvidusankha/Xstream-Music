package com.example.xstream.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table (name = "artists")
public class Artist {

    @Id
    @SequenceGenerator(
            name="artist_sequence",
            sequenceName="artist_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator="artist_sequence"
    )
    private long id;
    private String name;
    private String country;
    private String genre;


    @OneToMany
    @JoinColumn(name = "artist_id")
    private Set<Album> albums= new HashSet<>();

    @OneToMany
    @JoinColumn(name = "artist_id")
    private Set<Song> songs= new HashSet<>();

    public Artist() {
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Artist(String name, String country, String genre) {
        this.name = name;
        this.country = country;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", genre=" + genre +
                '}';
    }
}
