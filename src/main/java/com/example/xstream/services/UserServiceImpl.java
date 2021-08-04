package com.example.xstream.services;

import com.example.xstream.models.User;
import com.example.xstream.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private  UserRepository userRepository;
    //privte final UserServiceImpl userService;
    @Autowired
    public UserServiceImpl (UserRepository userRepository){
        this.userRepository=userRepository;
    }


    public List<User> getUsers(){
      return userRepository.findAll();
    }

    @Override
    public void addNewUser(User user) {
        Optional<User> userOptional =userRepository.findUserByEmail(user.getEmail());
        if(userOptional.isPresent()){
            throw new IllegalStateException("Email already exists");
        }

        userRepository.save(user);
    }

    public void deleteUser(long userId) {
        Boolean userExists = userRepository.existsById(userId);

        if(!userExists){
            throw new IllegalStateException("user id "+userId+"does not exists");
        }

        userRepository.deleteById(userId);
    }

    @Override
    @Transactional
    public void updateUser(long id, String fname, String lname, String email) {

        User user= userRepository.findById(id).orElseThrow(()-> new IllegalStateException( "user with id"+id+"not exists"));



        if(fname!=null && fname.length()>0 && !Objects.equals(user.getFname(),fname)){
                 user.setFname(fname);
        }

        if(lname!=null && lname.length()>0 && !Objects.equals(user.getLname(),lname)){
            user.setLname(lname);
        }

        if(email!=null && email.length()>0 && !Objects.equals(user.getEmail(),email)){
            user.setEmail(email);
        }

    }
}
