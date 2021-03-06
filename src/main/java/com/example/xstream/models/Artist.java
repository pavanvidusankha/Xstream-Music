package com.example.xstream.models;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Data
@NoArgsConstructor
@Setter
@Getter
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

    public Artist(String name, String country, String genre) {
        this.name = name;
        this.country = country;
        this.genre = genre;
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
