package com.example.videogamestore.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")

public class UsersController {
    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService userService) {
        this.usersService = userService;
    }

    @GetMapping
    public List<Users> getAllUsers() {
        return usersService.getUser();
    }

    @PostMapping
    public void registerUser(@RequestBody Users user) {
        usersService.addNewUser(user);
    }

    @DeleteMapping(path = "{userId}")
    public void deleteUser(@PathVariable("userId") Integer userId) {
        usersService.deleteUser(userId);
    }
}
