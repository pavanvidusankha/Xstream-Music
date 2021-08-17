package com.example.xstream.services;

import com.example.xstream.exceptions.CustomException;
import com.example.xstream.models.Artist;
import com.example.xstream.repositories.ArtistRepository;
import com.example.xstream.services.interfaces.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
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
        if (artist.getName().isEmpty() || artist.getCountry().isEmpty() || artist.getGenre().isEmpty()) {
            throw new CustomException("001", "Some fields are empty");
        }
      try{
          artistRepository.save(artist);
      } catch (IllegalStateException e) {
          throw new CustomException("002", "Artist is null" + e.getMessage());

      } catch (Exception e) {
          throw new CustomException("015", "There is an error in the artist service" + e.getMessage());
      }
    }

    @Override
    public void deleteArtist(long id) {
       try{
           Boolean artistExists = artistRepository.existsById(id);
           artistRepository.deleteById(id);

//           if (Boolean.FALSE.equals(artistExists)) {
//               throw new IllegalStateException("artist id " + id + "does not exists");
//           }
       }catch (NoSuchElementException e){
           throw new CustomException("","artist id " + id + "does not exists");
       }




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
