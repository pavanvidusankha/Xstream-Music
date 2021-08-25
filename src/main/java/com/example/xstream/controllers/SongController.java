package com.example.xstream.controllers;

import com.example.xstream.models.Song;
import com.example.xstream.services.SongServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

import java.util.List;


@RequestMapping("/")
@SecurityRequirement(name = "bearerAuth")
@RestController
public class SongController {
    private final SongServiceImpl songService;

    @Autowired
    public SongController(SongServiceImpl songService) {
        this.songService = songService;
    }

    @GetMapping("/artists/{artistId}/albums/{albumId}/songs/{songId}")
    public Song getSong(@PathVariable("songId") long songId,@PathVariable("albumId") long albumId) {
        return songService.getSongfromAlbumTrackID(albumId,songId);
    }

    @GetMapping("/artists/{artistId}/songs")
    public List<Song> getSongByArtistId(@PathVariable("artistId") long artistId) {
        return songService.getSongsByArtist(artistId);
    }

    @GetMapping("/songs")
    public List<Song> getSongs(@RequestParam(name = "name", required = false) String name, @RequestParam(name = "genre", required = false) String genre, @RequestParam(name="duration",required = false)   String duration) throws ParseException {
        if (name != null || duration !=null || genre != null) {
            return songService.getSongsBy(name, genre, duration);
        } else {
            return  songService.getSongs();
        }
    }

    @PostMapping("/artists/{artistId}/albums/{albumId}/songs")
    public void registerNewSong(@RequestBody Song song, @PathVariable("artistId") long artistId,long albumId) {
        songService.AddSong(song,albumId);

    }

    @DeleteMapping(path = "/artists/{artistId}/albums/{albumId}/songs/{songId}")
    public void deleteSong(@PathVariable("songId") long id) {
        songService.deleteSong(id);
    }

    @PutMapping(path = "/artists/{artistId}/songs/{albumId}/songs/{songId}")
    public void updateSong(@PathVariable("songId") long id, @RequestParam(required = false) String genre, @RequestParam(required = false) String name, @RequestParam(name="duration",required = false)  String duration, @PathVariable("artistId") long artistId,@PathVariable("albumId") long albumId) {
        songService.updateSong(id, name, genre, duration, artistId,albumId);
    }

    @PatchMapping(path = "/artists/{artistId}/albums/{albumId}/songs/{songId}")
    public void putSong(@PathVariable("songId") long id, @RequestParam(required = false) String genre, @RequestParam(required = false) String name, @RequestParam(name="duration",required = false)  String duration, @PathVariable("artistId") long artistId,@PathVariable("albumId") long albumId) {
        songService.updateSong(id, name, genre, duration, artistId,albumId);
    }
}
