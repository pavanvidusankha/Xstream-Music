package com.example.xstream.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Setter
@Getter
@Table(name = "users" )
public class User {
    @Id
    @SequenceGenerator(
            name="user_sequence",
            sequenceName="user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator="user_sequence"
    )
    @Column(name="user_id")
    private long id;
    private String uname;
    private String fname;
    private String lname;
    private String email;

    @Override
    public String toString() {
        return "User{" +
                "uname='" + uname + '\'' +
                ", id=" + id +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public User(String uname, long id, String fname, String lname, String email) {
        this.uname = uname;
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
    }

//    public User() {
//    }

//    public String getUname() {
//        return uname;
//    }
//
//    public void setUname(String uname) {
//        this.uname = uname;
//    }
//
//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public String getFname() {
//        return fname;
//    }
//
//    public void setFname(String fname) {
//        this.fname = fname;
//    }
//
//    public String getLname() {
//        return lname;
//    }
//
//    public void setLname(String lname) {
//        this.lname = lname;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }

    public User(String uname, String fname, String lname, String email) {
        this.uname = uname;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
    }

//    public List<Playlist> getUserPlaylists() {
//        return userPlaylists;
//    }
//
//    public void setUserPlaylists(List<Playlist> userPlaylists) {
//        this.userPlaylists = userPlaylists;
//    }
    @JsonIgnore
    @OneToMany
    private List<Playlist> userPlaylists;
}
