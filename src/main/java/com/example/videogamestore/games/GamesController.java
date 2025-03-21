package com.example.videogamestore.games;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/games")
public class GamesController {

    private final GamesService gamesService;

    @Autowired
    public GamesController(GamesService gamesService) {
        this.gamesService = gamesService;
    }

    @GetMapping
    public List<Games> getAllGames() {
        return gamesService.getAllGames();
    }

    @PostMapping
    public void addGame(@RequestBody Games game) {
        gamesService.addGame(game);
    }
}
