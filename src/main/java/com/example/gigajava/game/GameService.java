package com.example.gigajava.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class GameService {

    private final GameRepository gameRepository;

    @Autowired
    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<String> recommendGamesForMBTI(String mbti) {
        List<Game> recommendedGames = gameRepository.findByGroupGroupName(mbti);
        List<String> gameNames = recommendedGames.stream()
                .map(Game::getGameName)
                .collect(Collectors.toList());

        return gameNames;
    }

}

