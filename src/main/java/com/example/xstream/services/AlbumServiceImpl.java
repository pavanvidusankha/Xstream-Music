package com.example.xstream.services;
import com.example.xstream.models.Album;
import com.example.xstream.models.Artist;
import com.example.xstream.repositories.AlbumRepository;
import com.example.xstream.repositories.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class AlbumServiceImpl implements AlbumService {


    private final AlbumRepository albumRepository;
    @Autowired
    private  ArtistRepository artistRepository;


    @Autowired
    public AlbumServiceImpl(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Override
    public void addNewAlbum(Album album,long artistId) {
        albumRepository.save(album);
        Artist artist=artistRepository.findById(artistId).orElseThrow(()-> new IllegalStateException("Artist ID "+artistId +" does not exists"));
        album.setArtist(artist);
    }

    @Override
    public void deleteAlbum(long id) {
        Boolean albumExists = albumRepository.existsById(id);

        if(Boolean.FALSE.equals(albumExists)){
            throw new IllegalStateException("album id "+id+" does not exists");
        }

        albumRepository.deleteById(id);
    }

    @Override
    public List<Album> getAlbums() {
        return albumRepository.findAll();
    }

    @Override
    @Transactional
    public void updateAlbum(long id, String name, String genre, Date releasedDate, long artistId) {

        Album album= albumRepository.findById(id).orElseThrow(()-> new IllegalStateException( "Album with id "+id+" not exists"));



        if(name!=null && name.length()>0 && !Objects.equals(album.getName(),name)){
            album.setName(name);
        }

        if(genre!=null && genre.length()>0 && !Objects.equals(album.getGenre(),genre)){
            album.setGenre(genre);
        }

        if(releasedDate!=null && !Objects.equals(album.getReleasedDate(),releasedDate)){
            album.setReleasedDate(releasedDate);
        }

//        if(artistId!=null  && !Objects.equals(album.getArtist().getId(),artistId)){
//
//            Use
//            album.setArtist(email);
//        }
        

    }

    @Override
    public Album getAlbum(long id) {
        return albumRepository.findById(id).orElseThrow(()-> new IllegalStateException("Album with ID "+ id +" is not found"));
    }

    @Override
    public List<Album> getAlbumsByGenre(String genre) {

        return (List<Album>) albumRepository.findAlbumsByGenre(genre);
    }

    @Override
    public Set<Album>  getAlbumsByArtist(long id) {
        return albumRepository.findAlbumsByArtist_Id(id);
    }
}
