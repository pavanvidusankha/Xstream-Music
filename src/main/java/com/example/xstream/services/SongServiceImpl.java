package com.example.xstream.services;

import com.example.xstream.models.Album;
import com.example.xstream.models.Artist;
import com.example.xstream.models.Song;
import com.example.xstream.repositories.AlbumRepository;
import com.example.xstream.repositories.ArtistRepository;
import com.example.xstream.repositories.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.*;

@Service
public class SongServiceImpl implements SongService{

    private final SongRepository songRepository;
    @Autowired
    private AlbumRepository albumRepository;
    private ArtistRepository artistRepository;

    @Autowired
    public SongServiceImpl(SongRepository songRepository){
        this.songRepository=songRepository;
    }

    public void AddSong(Song song,long albumId){
        Album optionalAlbum=albumRepository.findById(albumId).orElseThrow(()-> new IllegalStateException("Album ID "+albumId+" doesnt exists"));

        song.getAlbums().add(optionalAlbum);
        song.setArtist(optionalAlbum.getArtist());

        songRepository.save(song);
    }

    @Override
    public Song getSong(long songId) {
        return songRepository.getById(songId);
    }

    @Override
    public List<Song> getSongs() {
        return songRepository.findAll();
    }

    @Override
    public void deleteSong(long id) {
         songRepository.getById(id);
    }

    @Override
    @Transactional
    public void updateSong(long id, String name, String genre, String duration, long artist_id, long album_id) {
        Song song= songRepository.findById(id).orElseThrow(()-> new IllegalStateException( "Song with id "+id+" not exists"));



        if(name!=null && name.length()>0 && !Objects.equals(song.getName(),name)){
            song.setName(name);
        }

        if(genre!=null && genre.length()>0 && !Objects.equals(song.getGenre(),genre)){
            song.setGenre(genre);
        }

        if(duration!=null && duration.length()>0 && !Objects.equals(song.getDuration(),Integer.parseInt(duration))){
            song.setDuration(Integer.parseInt(duration));
        }

    }

    @Override
    public Set<Song> getSongsByGenre(String genre) {
        return songRepository.getSongsByGenre(genre);
    }

    @Override
    public List<Song> getSongsByArtist(long id) {
        //return null;
        return songRepository.getSongsByArtist_Id(id)  ;
    }

    @Override
    public List<Song> getSongsBy(String albumName, String genre, String artistName) throws ParseException {
        Album album = albumRepository.findAlbumByName(albumName);
        Artist artist = artistRepository.findArtistByName(artistName);
        //return songRepository.getSongsByArtistsOrAlbumsOrGenreIgnoreCase(artist,album,genre);
        return null;
    }
}
