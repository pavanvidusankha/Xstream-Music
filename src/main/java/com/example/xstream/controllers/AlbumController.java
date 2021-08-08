package com.example.xstream.controllers;

import com.example.xstream.models.Album;
import com.example.xstream.services.AlbumServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping
public class AlbumController {

    private final AlbumServiceImpl albumService;

    @Autowired
    public AlbumController(AlbumServiceImpl albumService) {
        this.albumService = albumService;
    }

    @GetMapping("/artists/albums/{albumId}")
    public Album getAlbum(@PathVariable("albumId") long id) {
        return albumService.getAlbum(id);
    }

    @GetMapping("/artists/{artistId}/albums")
    public List<Album> getAlbumByArtistId(@PathVariable("artistId") long artistId) {
        return albumService.getAlbumsByArtist(artistId);
    }

    @GetMapping("/albums")
    public List<Album> getAlbums(@RequestParam(name = "name", required = false) String name, @RequestParam(name = "genre", required = false) String genre, @RequestParam(name="released_year",required = false)   String releasedYear) throws ParseException {
        if (name != null || releasedYear!=null || genre != null) {
            return albumService.getAlbumsBy(name, genre, releasedYear);
        } else {
            return albumService.getAlbums();
        }
    }

    @PostMapping("/artists/{artistId}/albums")
    public void registerNewAlbum(@RequestBody Album album, @PathVariable("artistId") long artistId) {
        albumService.addNewAlbum(album, artistId);

    }

    @DeleteMapping(path = "/artists/{artistId}/albums/{albumId}")
    public void deleteAlbum(@PathVariable("albumId") long id) {
        albumService.deleteAlbum(id);
    }

    @PutMapping(path = "/artists/{artistId}/albums/{albumId}")
    public void updateAlbum(@PathVariable("albumId") long id, @RequestParam(required = false) String genre, @RequestParam(required = false) String name, @RequestParam(name="released_date",required = false)  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date releasedDate, @PathVariable("artistId") long artistId) {
        albumService.updateAlbum(id, name, genre, releasedDate, artistId);
    }

    @PatchMapping(path = "/artists/{artistId}/albums/{albumId}")
    public void patchAlbum(@PathVariable("albumId") long id,  @RequestParam(required = false) String genre, @RequestParam(required = false) String name, @RequestParam(name="released_date",required = false)  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date releasedDate, @PathVariable("artistId") long artistId)  {

        albumService.updateAlbum(id, name, genre,releasedDate, artistId);
    }
}
