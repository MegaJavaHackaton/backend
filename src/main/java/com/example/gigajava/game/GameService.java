package com.example.gigajava.game;

import com.example.gigajava.group.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public List<GameDTO> getAllGames() {
        List<Game> games = gameRepository.findAll();

        // Convert Game entities to GameDTOs
        List<GameDTO> gameDTOs = new ArrayList<>();
        for (Game game : games) {
            GameDTO gameDTO = new GameDTO();
            gameDTO.setGameId(game.getGameId());
            gameDTO.setGameName(game.getGameName());
            gameDTO.setGroupId(game.getGroup().getGroupId()); // Assuming there's a Group reference
            gameDTOs.add(gameDTO);
        }

        return gameDTOs;
    }

    public List<String> getRecommendedGamesByGroup(Group group) {
        // 실제 로직에 따라 추천 게임 목록을 조회하고 처리하는 코드 작성
        List<Game> recommendedGames = gameRepository.findRecommendedGamesByGroup(group);

        return recommendedGames.stream()
                .map(Game::getGameName)
                .collect(Collectors.toList());
    }
}

