package com.example.videogamestore.games;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GamesService {

    private final GamesRepository gamesRepository;

    @Autowired
    public GamesService(GamesRepository gamesRepository) {
        this.gamesRepository = gamesRepository;
    }

    public List<Games> getAllGames() {
        return gamesRepository.findAll();
    }

    public void addGame(Games game) {
        gamesRepository.save(game);
    }
}
