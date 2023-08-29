package com.example.gigajava.recommend;

import com.example.gigajava.game.Game;
import com.example.gigajava.game.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameRecommendationService {

    private final GameRepository gameRepository;

    @Autowired
    public GameRecommendationService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }
}

