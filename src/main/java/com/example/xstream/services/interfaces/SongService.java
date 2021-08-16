package com.example.xstream.services.interfaces;

import com.example.xstream.models.Song;

import java.text.ParseException;
import java.util.List;
import java.util.Set;

public interface SongService {

     void AddSong(Song song, long albumId);
     Song getSong(long songId);
     Song getSongfromAlbumTrackID(long albumId,long trackId);
     List<Song> getSongs();
     void deleteSong(long id);
     void updateSong(long id, String name, String genre, String duration, long artist_id,long album_id);
     Set<Song> getSongsByGenre(String genre);
     //List<Album> getSongsByReleasedDate(Date date);
     List<Song> getSongsByArtist(long id);
     List<Song> getSongsBy(String name, String genre, String duration) throws ParseException;
}
