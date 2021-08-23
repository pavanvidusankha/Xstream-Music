package com.example.xstream.config;

import com.example.xstream.models.*;
import com.example.xstream.repositories.*;
import com.example.xstream.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

@Configuration
public class Config {


    @Bean
    @Autowired
    CommandLineRunner commandLineRunner(UserRepository userRepository, UserService userService, ArtistRepository artistRepository, AlbumRepository albumRepository, SongRepository songRepository, PlaylistRepository playlistRepository) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        return args -> {

            //adding the roles
            userService.saveRole(new Role(null, "ADMIN"));
            userService.saveRole(new Role(null, "USER"));

            //Adding the users
            User pavan = new User("psam", "1234", "Pavan", "Samaranayake", "psam@xstream.com");
            User john = new User("jdoe", "123", "John", "Doe", "jdoe@xstream.com");
            User alex = new User("aholder", "12", "Alex", "Holder", "aholder@xstream.com");
            userService.addNewUser(pavan);
            userService.addNewUser(john);
            userService.addNewUser(alex);


            //adding roles to the users
            userService.addRoleToUser("psam", "ADMIN");
            userService.addRoleToUser("psam", "USER");
            userService.addRoleToUser("jdoe", "USER");


            Artist artist = new Artist("The Weeknd", "Canada", "Pop/R&B");
            Artist artist1 = new Artist("Charlie Puth", "USA", "Pop/R&B");
            Artist artist2 = new Artist("Justin Timberlake", "USA", "Pop");
            Artist artist3 = new Artist("Pearl Jam", "USA", "Grunge Rock");

            artistRepository.saveAll(List.of(artist, artist1, artist2, artist3));

            Album weekndAlbum = new Album("Beauty behind the madness", "Pop/R&B", dateFormat.parse("2014-01-21"));
            weekndAlbum.setArtist(artist);
            albumRepository.save(weekndAlbum);

            Album weekndAlbum1 = new Album("After Hours", "Pop", dateFormat.parse("2020-02-21"));
            weekndAlbum1.setArtist(artist);

            Album jtAlbum = new Album("Justified", "Pop/R&B", dateFormat.parse("2003-04-21"));
            jtAlbum.setArtist(artist2);

            Album cpAlbum = new Album("Voice Notes", "Pop/R&B", dateFormat.parse("2016-09-14"));
            cpAlbum.setArtist(artist1);

            Album pAlbum = new Album("Five", "Grunge Rock", dateFormat.parse("1993-03-06"));
            pAlbum.setArtist(artist3);

            albumRepository.saveAll(List.of(weekndAlbum, weekndAlbum1, pAlbum, cpAlbum, jtAlbum));
            SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");

            Song song = new Song("Can't Feel My Face", 240, "Pop");
            song.getAlbums().add(weekndAlbum);
            weekndAlbum.getSongs().add(song);
            song.setArtist(artist);

            Song song1 = new Song("The hills", 270, "Pop");
            song1.getAlbums().add(weekndAlbum);
            weekndAlbum.getSongs().add(song1);
            song1.setArtist(artist);

            Song song3 = new Song("Jeremy", 450, "Grunge");
            song3.getAlbums().add(pAlbum);
            pAlbum.getSongs().add(song3);
            song3.setArtist(artist3);

            songRepository.saveAll(List.of(song1, song, song3));


            //playlists
            Playlist playlist1 = new Playlist("Pop Songs");
            playlist1.setUser(pavan);
            //pavan.getUserPlaylists().add(playlist1);

            playlist1.getPlaylistSongs().add(song);
            playlist1.getPlaylistSongs().add(song1);

            Playlist playlist2 = new Playlist("ROCK");
            playlist2.setUser(john);
            //test.getUserPlaylists().add(playlist2);
            playlist2.getPlaylistSongs().add(song3);

            playlistRepository.saveAll(List.of(playlist1, playlist2));
            //playlistRepository.deleteAllByUser(test);
            //userRepository.delete(test);

            //pavan.setEmail("pavan@xtream.com");
        };
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
