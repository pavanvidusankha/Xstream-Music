package com.example.xstream.services;

import com.example.xstream.models.Playlist;
import com.example.xstream.models.Song;
import com.example.xstream.models.User;
import com.example.xstream.repositories.PlaylistRepository;
import com.example.xstream.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class PlaylistServiceImpl implements PlaylistService{


    PlaylistRepository playlistRepository;
    UserRepository userRepository;
    SongService songService;
    @Autowired
    public PlaylistServiceImpl(PlaylistRepository playlistRepository,UserRepository userRepository){
        this.playlistRepository=playlistRepository;
        this.userRepository=userRepository;
    }
    @Override
    public void addNewPlaylist(Playlist playlist, long userId) {
        User playlistUser=  userRepository.findById(userId).orElseThrow(()-> new IllegalStateException( "User with id "+userId+" not exists"));
        playlist.setUser(playlistUser);

        playlistRepository.save(playlist);

    }

    @Override
    public void deletePlaylist(long id) {
        playlistRepository.deleteById(id);

    }

    @Override
    public List<Playlist> getPlaylists() {
        return playlistRepository.findAll();
    }

    @Override
    @Transactional
    public void updatePlaylist(long id, String name) {
        Playlist testPlaylist= playlistRepository.findById(id).orElseThrow(()-> new IllegalStateException( "Playlist with id "+id+" not exists"));

        if(name!=null && name.length()>0 && !Objects.equals(testPlaylist.getName(),name)){
            testPlaylist.setName(name);
        }
    }

    @Override
    public Playlist getPlaylist(long id) {
        return playlistRepository.findById(id).orElseThrow(()-> new IllegalStateException( "Playlist with id "+id+" not exists"));

    }

    @Override
    public List<Playlist> getPlaylistByUser(long userId) {
        User user=userRepository.findById(userId).orElseThrow(()-> new IllegalStateException( "User with id "+userId+" not exists"));
        return playlistRepository.getPlaylistsByUser(user);
    }

    @Override
    public List<Playlist> getPlaylistByName(String name) {
        return playlistRepository.getPlaylistsByName(name);
    }

    @Override
    public void AddSongToPlaylist(long playlistId, long songId) {
       Playlist playlist=getPlaylist(playlistId);
       Song song=songService.getSong(songId);
       playlist.getPlaylistSongs().add(song);
      // playlistRepository.save(playlist);
    }

    @Override
    public void RemoveSongFromPlaylist(long playlistId, long songId) {
        Playlist playlist=getPlaylist(playlistId);
        Song song=songService.getSong(songId);
        playlist.getPlaylistSongs().remove(song);
       // playlistRepository.save(playlist);
    }
}
