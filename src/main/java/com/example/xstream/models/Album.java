package com.example.xstream.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
@Data
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "albums")
public class Album {

    @Id
    @SequenceGenerator(
            name="album_sequence",
            sequenceName="album_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator="album_sequence"
    )
    @Column(name = "album_id")
    private long id;
    private String name;
    private String genre;

    @Temporal(TemporalType.TIMESTAMP)
    @Column( nullable = false)
    private Date releasedDate;

//    public Album() {
//    }

    public Album(String name, String genre, Date releasedDate) {
        this.name = name;
        this.genre = genre;
        this.releasedDate = releasedDate;

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

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", genre='" + genre + '\'' +
                ", releasedDate=" + releasedDate +
                '}';
    }

//    public String getGenre() {
//        return genre;
//    }
//
//    public void setGenre(String genre) {
//        this.genre = genre;
//    }
//
//    public Date getReleasedDate() {
//        return releasedDate;
//    }
//
//    public void setReleasedDate(Date releasedDate) {
//        this.releasedDate = releasedDate;
//    }
//
//    public Artist getArtist() {
//        return artist;
//    }
//
//    public void setArtist(Artist artist) {
//        this.artist = artist;
//    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "artist_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Artist artist;
//fetch=FetchType.LAZY
//    @ManyToMany(
//            cascade = {
//                    CascadeType.ALL
//            })
//
//    @JoinTable(name = "album_songs",
//            joinColumns = { @JoinColumn(name = "song_id") },
//            inverseJoinColumns = { @JoinColumn(name = "album_id") }) , cascade = { CascadeType.MERGE }
    @ManyToMany
    @JsonIgnore
    private Set<Song> songs=new HashSet<>();

//    public Set<Song> getSongs() {
//        return songs;
//    }
//
//    public void setSongs(Set<Song> songs) {
//        this.songs = songs;
//    }
}
