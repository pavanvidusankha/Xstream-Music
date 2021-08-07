package com.example.xstream.config;

import com.example.xstream.models.Album;
import com.example.xstream.models.Artist;
import com.example.xstream.models.User;
import com.example.xstream.repositories.AlbumRepository;
import com.example.xstream.repositories.ArtistRepository;
import com.example.xstream.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.List;

@Configuration
public class studentConfig {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository, ArtistRepository artistRepository,AlbumRepository albumRepository){
        return args -> {
            User pavan =new User("psam","Pavan","Samaranayake","psam@xstream.com");
            User test =new User("test","John","Doe","jdoe@xstream.com");
            Artist artist=new Artist("The Weeknd","Canada","Pop/R&B");
            Artist artist1=new Artist("Charlie Puth","USA","Pop/R&B");
            Artist artist2=new Artist("Justin Timberlake","USA","Pop");
            Artist artist3=new Artist("Pearl Jam","USA","Grunge Rock");
            userRepository.saveAll(
                    List.of(pavan,test)
            );
            artistRepository.saveAll(List.of(artist,artist1,artist2,artist3));

            Album weekndAlbum=new Album("Beauty behind the madness","Pop/R&B",new Date(2014-01-21));
            weekndAlbum.setArtist(artist);
albumRepository.save(weekndAlbum);

            Album weekndAlbum1=new Album("After Hours","Pop",new Date(2020-02-21));
            weekndAlbum1.setArtist(artist);

            Album jtAlbum=new Album("Justified","Pop/R&B",new Date(2003-04-21));
            jtAlbum.setArtist(artist1);

            Album cpAlbum=new Album("Voice Notes","Pop/R&B",new Date("2016/09/14"));
            cpAlbum.setArtist(artist2);

            Album pAlbum=new Album("Five","Grunge Rock",new Date(1993-03-06));
            pAlbum.setArtist(artist3);

            //albumRepository.saveAll(List.of(weekndAlbum,weekndAlbum1,pAlbum,cpAlbum,jtAlbum));
        };
    }
}
