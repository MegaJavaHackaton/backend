package com.example.gigajava.game;

import com.example.gigajava.group.MbtiGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public List<GameDTO> getAllGames() {
        List<Game> games = gameRepository.findAll();

        // Convert Game entities to GameDTOs
        return games.stream()
                .map(game -> {
                    GameDTO gameDTO = new GameDTO();
                    gameDTO.setGameId(game.getGameId());
                    gameDTO.setGameName(game.getGameName());
                    MbtiGroup mbtiGroup = game.getMbtiGroup();
                    if (mbtiGroup != null) {
                        gameDTO.setGroupId(mbtiGroup.getGroupId());
                    }
                    return gameDTO;
                })
                .collect(Collectors.toList());
    }

    public List<Game> findByGroupId(int groupId) {
        return gameRepository.findByGroupId(groupId);
    }
}

