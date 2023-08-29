package com.example.gigajava.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/games")
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping
    public List<GameDTO> getAllGames() {
        return gameService.getAllGames();
    }
    @GetMapping("/{groupId}")
    public ResponseEntity<List<Game>> findByGroupId(@PathVariable int groupId) {
        List<Game> recommendedList = gameService.findByGroupId(groupId);
        return new ResponseEntity<>(recommendedList, HttpStatus.OK);
    }

}

