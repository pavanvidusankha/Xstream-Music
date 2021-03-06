package com.example.xstream.services;

import com.example.xstream.exceptions.*;
import com.example.xstream.models.Role;
import com.example.xstream.models.User;
import com.example.xstream.repositories.PlaylistRepository;
import com.example.xstream.repositories.RoleRepository;
import com.example.xstream.repositories.UserRepository;
import com.example.xstream.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.InvalidParameterException;
import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final PlaylistRepository playlistRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

//    @Autowired
//    public UserServiceImpl(UserRepository userRepository, PlaylistRepository playlistRepository) {
//        this.userRepository = userRepository;
//        this.playlistRepository = playlistRepository;
//    }


    public List<User> getUsers() {
        List<User> userList = null;
        try {
            userList = userRepository.findAll();

        } catch (NoSuchElementException e) {
            //throw new CustomException("006", "There is some error while fetching the users");
            throw new NoSuchElementException(e.getMessage());
        }
        if (userList.isEmpty()) {
            throw new EmptyListException("Users");
        }
        return userList;
    }

    @Override
    public void addAllUsers(List<User> usersList) {
        if (usersList == null) {
            throw new CustomException("013", "List is empty");
        }
        try {
            userRepository.saveAll(usersList);
        } catch (Exception e) {
            throw new CustomException("014", "There is an error saving all users in the service layer " + e.getMessage());

        }
    }

    @Override
    public Role saveRole(Role role) {
        try {
            roleRepository.save(role);
        } catch (IllegalStateException e) {
            log.info("Error occurred when saving role");
            throw new RoleServiceException();
        }
        log.info("Saving role " + role.getName());
        return role;
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        try {
            User user = userRepository.findUserByUname(username).orElseThrow();
            Role role = roleRepository.findRoleByName(roleName);
            user.getRoles().add(role);
            log.info("adding Role"+role.getName()+"Saving user "+user.getFname());

        } catch (Exception e) {
            throw new UserServiceException();
        }

    }

    @Override
    public void addNewUser(User user) {

        if (user.getUname().isEmpty() || user.getFname().isEmpty() || user.getEmail().isEmpty()) {
            throw new CustomException("001", "Some fields are empty");
        }
        try {
            Optional<User> userOptional = userRepository.findUserByEmail(user.getEmail());
            if (userOptional.isPresent()) {
                throw new CustomException("004", "Email already exists");
            }
            userRepository.save(user);
            user.setPassword(passwordEncoder.encode(user.getPassword()));


        } catch (IllegalStateException e) {
            log.error("Error occurred when saving user");
            throw new CustomException("002", "User is null" + e.getMessage());

        } catch (Exception e) {
            throw new CustomException("003", "There is an error in the user service" + e.getMessage());
        }

        log.info("Saving user "+user.getUname());

//            try {
//                userRepository.save(user);
//                //save encrypted password
//                user.setPassword(passwordEncoder.encode(user.getPassword()));
//            }catch (IllegalStateException e){
//                log.info("Error occurred when saving user");
//                throw new UserServiceException();
//            }
//            log.info("Saving user "+user.getName());
//
//            return user;


    }

    public void deleteUser(long userId) {
        try {
            Boolean userExists = userRepository.existsById(userId);

        } catch (IllegalArgumentException e) {
            throw new CustomException("007", "Id is empty,please send an id " + e.getMessage());
        } catch (NoSuchElementException e) {

            throw new CustomException("008", "User with ID " + userId + " is not found " + e.getMessage());

        } catch (IllegalStateException e) {
            throw new CustomException("009", "There is an error deleting a user in the service layer " + e.getMessage());

        }

        try {
            playlistRepository.deleteAllByUser(getUser(userId));
            userRepository.deleteById(userId);

        } catch (Exception e) {
            throw new CustomException("011", "There is an error deleting a user and playlists in the service layer " + e.getMessage());

        }


    }

    @Override
    @Transactional
    public void updateUser(long id, String fname, String lname, String email) {
        try {
            User user = userRepository.findById(id).orElseThrow(() -> new IllegalStateException("user with id" + id + "not exists"));


            if (fname != null && fname.length() > 0 && !Objects.equals(user.getFname(), fname)) {
                user.setFname(fname);
            }

            if (lname != null && lname.length() > 0 && !Objects.equals(user.getLname(), lname)) {
                user.setLname(lname);
            }

            if (email != null && email.length() > 0 && !Objects.equals(user.getEmail(), email)) {
                user.setEmail(email);
            }
        } catch (IllegalArgumentException e) {
            throw new CustomException("007", "Id is empty,please send an id " + e.getMessage());
        } catch (Exception e) {
            throw new CustomException("010", "There is an error updating a user in the service layer " + e.getMessage());

        }


    }

    @Override
    public User getUser(long id) {
        User user = new User();
        try {
            user = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No such user exists in the DB"));
        } catch (IllegalArgumentException e) {
            throw new CustomException("007", "Id is empty,please send an id " + e.getMessage());
        } catch (IllegalStateException e) {
            throw new CustomException("008", "User with ID " + id + " is not found");
        } catch (NullPointerException e) {
            throw new CustomException("008", "User with ID " + id + " is not found " + e.getMessage());

        }
        return user;

    }

    @Override
    public User getUserbyUserName(String username) {
        if(username.isEmpty()){
            throw  new InvalidParameterException();
        }
        User user;
        try{
            user=userRepository.findUserByUname(username).orElseThrow();
        }catch (IllegalStateException e) {
            throw new UserServiceException();
        }catch (NullPointerException e){
            throw new NoItemFoundException();
        }
        log.info("retrieving the user"+user.getUname());

        return user;    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user;
        try{
            user=userRepository.findUserByUname(username).orElseThrow();

        }catch (UsernameNotFoundException e){
            log.error("User with name "+username+"is not found");
            throw new UsernameNotFoundException("User with name "+username+"is not found");
        }catch (Exception e){
            throw new UserServiceException();
        }
        Collection<SimpleGrantedAuthority> authorities=new ArrayList<>();
        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
        return new org.springframework.security.core.userdetails.User(user.getUname(),user.getPassword(),authorities);
    }
}
