package com.example.xstream.services;

import com.example.xstream.models.Artist;
import com.example.xstream.repositories.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class ArtistServiceImpl implements ArtistService {

    private final ArtistRepository artistRepository;

    @Autowired
    public ArtistServiceImpl(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    public void addNewArtist(Artist artist) {
        artistRepository.save(artist);
    }

    @Override
    public void deleteArtist(long id) {
        Boolean userExists = artistRepository.existsById(id);

        if (Boolean.FALSE.equals(userExists)) {
            throw new IllegalStateException("user id " + id + "does not exists");
        }

        artistRepository.deleteById(id);
    }

    @Override
    public List<Artist> getArtists() {
        return artistRepository.findAll();
    }

    @Override
    @Transactional
    public void updateArtist(long id, String name, String country, String genre) {
        Artist artist = artistRepository.findById(id).orElseThrow(() -> new IllegalStateException("Artist with id " + id + " not exists"));

        if (name != null && name.length() > 0 && !Objects.equals(artist.getName(), name)) {
            artist.setName(name);
        }

        if (genre != null && genre.length() > 0 && !Objects.equals(artist.getGenre(), genre)) {
            artist.setGenre(genre);
        }

        if (country != null && country.length() > 0 && !Objects.equals(artist.getCountry(), country)) {
            artist.setCountry(country);
        }

    }

    @Override
    public Artist getArtist(long id) {
        return artistRepository.findById(id).orElseThrow(() -> new IllegalStateException("Artist with ID " + id + " is not found"));

    }

    @Override
    public List<Artist> findArtistsByName(String name) {
        return artistRepository.findArtistsByName(name);
    }
}
