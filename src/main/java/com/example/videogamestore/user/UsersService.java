package com.example.videogamestore.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersService {

    private final UsersRepository userRepository;

    @Autowired
    public UsersService(UsersRepository mUserRepository) {
        this.userRepository = mUserRepository;
    }

    public List<Users> getUser() {
        return userRepository.findAll();

    }

    public void addNewUser(Users user) {
       Optional<Users> userOptional = userRepository.findUsersByEmail(user.getEmail());

       if(userOptional.isPresent()) {
           throw new RuntimeException("User with email " + user.getEmail() + " already exists");
       }
       userRepository.save(user);

    }

    public void deleteUser(Integer userId) {
        boolean exists = userRepository.existsById(userId);
        if(!exists) {
            throw new RuntimeException("User with id " + userId + " does not exist");
        }
        userRepository.deleteById(userId);
    }

    public Optional<Users> findUserByEmail(String email) {
        return userRepository.findUsersByEmail(email);
    }
}
