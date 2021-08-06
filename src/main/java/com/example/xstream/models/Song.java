package com.example.xstream.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Song {

    @Id
    private long id;
    private String name;
    private Integer duration;
    private String genre;


}