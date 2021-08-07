package com.example.xstream.controllers;
import com.example.xstream.models.Album;
import com.example.xstream.services.AlbumServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping
public class AlbumController {
    private final AlbumServiceImpl albumService;

    @Autowired
    public AlbumController(AlbumServiceImpl albumService){
        this.albumService=albumService;
    }

    @GetMapping("/artists/albums/{albumId}" )
    public Album getAlbum(@PathVariable("albumId") long id) {
        return albumService.getAlbum(id);
    }

    @GetMapping("/artists/{artistId}/albums/" )
    public Set<Album> getAlbumByArtistId(@PathVariable("artistId") long artistId) {
        return albumService.getAlbumsByArtist(artistId);
    }

    @GetMapping("/albums")
    public List<Album> getAlbumsByGenre(@RequestParam(name="genre",required = false) String genre){

        if(genre!=null){
            return albumService.getAlbumsByGenre(genre);
        }
        else
            return albumService.getAlbums();
    }

    @PostMapping("/artists/{artistId}/albums")
    public void registerNewAlbum(@RequestBody Album album,@PathVariable("artistId") long artistId) {
        Album testAlbum=album;
        albumService.addNewAlbum(testAlbum,artistId);

    }

    @DeleteMapping(path = "/artists/{artistId}/albums/{albumId}")
    public void deleteAlbum(@PathVariable("albumId") long id) {
        albumService.deleteAlbum(id);
    }

    @PutMapping(path = "/artists/{artistId}/albums/{albumId}")
    public void updateAlbum(@PathVariable("albumId") long id, @RequestParam(required = false) String country, @RequestParam(required = false) String genre, @RequestParam(required = false) String name,@RequestParam(required = false) Date releasedDate,@PathVariable("artistId") long artistId) {
        albumService.updateAlbum(id,name,genre,releasedDate,artistId);
    }

    @PatchMapping(path = "/artists/{artistId}/albums/{albumId}")
    public void patchAlbum(@PathVariable("albumId") long id, @RequestParam(required = false) String country, @RequestParam(required = false) String genre, @RequestParam(required = false) String name,@RequestParam(required = false) Date releasedDate,@PathVariable("artistId") long artistId) {
        albumService.updateAlbum(id,name,genre,releasedDate,artistId);
    }
}
