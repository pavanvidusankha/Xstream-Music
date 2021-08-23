package com.example.xstream.controllers;

import com.example.xstream.models.Playlist;
import com.example.xstream.services.PlaylistServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/")
@RestController
public class PlaylistController {
    private final PlaylistServiceImpl playlistService;

    @Autowired
    public PlaylistController(PlaylistServiceImpl playlistService) {
        this.playlistService = playlistService;
    }

    @GetMapping("/users/playlists")
    public List<Playlist> getPlaylists(@RequestParam(value = "name",required = false) String name){
        if(name!=null){
           return playlistService.getPlaylistByName(name);
        }
        else
           return playlistService.getPlaylists();

    }

    @GetMapping("/users/{userId}/playlists/{playlistId}")
    public Playlist getPlaylistById(@PathVariable ("playlistId") long playlistId,@PathVariable ("userId") long userId){
       return playlistService.getPlaylist(playlistId);
    }


    @PostMapping("/users/{userId}/playlists")
    public void addPlaylist(@RequestBody Playlist playlist,@PathVariable ("userId") long userId){
        playlistService.addNewPlaylist(playlist,userId);

    }

    @DeleteMapping("/users/{userId}/playlists/{playlistId}")
    public void deletePlaylist(@PathVariable ("playlistId") long playlistId,@PathVariable ("userId") long userId){
        playlistService.deletePlaylist(playlistId);
    }

    @PutMapping("/users/{userId}/playlists/{playlistId}")
    public void updatePlaylist(@PathVariable ("playlistId") long playlistId,@PathVariable ("userId") long userId,@RequestParam ("name") String name){
        playlistService.updatePlaylist(playlistId,name);
    }

    @PatchMapping("/users/{userId}/playlists/{playlistId}")
    public void patchPlaylist(@PathVariable ("playlistId") long playlistId,@PathVariable ("userId") long userId,@RequestParam ("name") String name){
        playlistService.updatePlaylist(playlistId,name);
    }

    @GetMapping("/users/{userId}/playlists")
    public List<Playlist> getPlaylistsByUser(@PathVariable("userId") long userId){
      return   playlistService.getPlaylistByUser(userId);
    }
    @PostMapping("/users/{userId}/playlists/{playlistId}/addSong")
    public void addSongToPlaylist(@PathVariable ("playlistId") long playlistId,@PathVariable ("userId") long userId,@RequestParam ("songId") long songId){
        playlistService.AddSongToPlaylist(playlistId,songId);
    }

    @DeleteMapping("/users/{userId}/playlists/{playlistId}/removeSong")
    public void removeSongFromPlaylist(@PathVariable ("playlistId") long playlistId,@PathVariable ("userId") long userId,@RequestParam ("songId") long songId){
        playlistService.AddSongToPlaylist(playlistId,songId);
    }



}
