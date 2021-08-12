package com.example.xstream.controllers;

import com.example.xstream.models.Artist;
import com.example.xstream.services.ArtistServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/artists")
public class ArtistController {
    private final ArtistServiceImpl artistServiceImpl;

    @Autowired
    public ArtistController(ArtistServiceImpl artistServiceImpl) {
        this.artistServiceImpl = artistServiceImpl;
    }

    @GetMapping
    public List<Artist> getArtists(@RequestParam(required = false) String name) {

        if (name != null) {
            return artistServiceImpl.findArtistsByName(name);
        } else
            return artistServiceImpl.getArtists();
    }

    @GetMapping(path = "/{artistId}")
    public Artist getArtist(@PathVariable("artistId") long id) {
        return artistServiceImpl.getArtist(id);
    }

    @PostMapping
    public void registerNewArtist(@RequestBody Artist artist) {
        artistServiceImpl.addNewArtist(artist);
    }

    @DeleteMapping(path = "{artistId}")
    public void deleteArtist(@PathVariable("artistId") long id) {
        artistServiceImpl.deleteArtist(id);
    }

    @PutMapping(path = "/{artistId}")
    public void updateArtist(@PathVariable("artistId") long id, @RequestParam(required = false) String country, @RequestParam(required = false) String genre, @RequestParam(required = false) String name) {
        artistServiceImpl.updateArtist(id, name, country, genre);
    }

    @PatchMapping(path = "/{artistId}")
    public void putArtist(@PathVariable("artistId") long id, @RequestParam(required = false) String country, @RequestParam(required = false) String genre, @RequestParam(required = false) String name) {
        artistServiceImpl.updateArtist(id, name, country, genre);
    }
}
