package com.example.gigajava.game;

import com.example.gigajava.recommend.AnswerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class GameController {

    private final Map<String, String> gameRecommendations = new HashMap<>();

    @PostMapping("/answers")
    public ResponseEntity<String> receiveAnswers(@RequestBody List<AnswerDTO> answers) {
        // 각 답변을 분석하여 추천된 게임을 저장
        for (AnswerDTO answer : answers) {
            String questionId = answer.getQuestionId();
            String userAnswer = answer.getAnswer();

            // 각 질문에 따라서 추천 게임 설정
            // 이 부분은 실제 비즈니스 로직에 맞게 수정해야 함
            // 예를 들어, 각 답변에 따라 데이터베이스에서 추천 게임을 가져오는 로직 등
            String recommendedGame = getRecommendedGame(questionId, userAnswer);
            gameRecommendations.put(questionId, recommendedGame);
        }

        return ResponseEntity.ok("Answers received successfully");
    }

//    @GetMapping("/recommendation")
//    public ResponseEntity<Map<String, String>> getGameRecommendations() {
//        return ResponseEntity.ok(gameRecommendations);
//    }

    private String getRecommendedGame(String questionId, String userAnswer) {
        // 각 질문에 따라 추천 게임 반환
        // 실제 비즈니스 로직에 맞게 구현
        return "Recommended Game for " + questionId + ": " + userAnswer;
    }

    @Autowired
    private GameService gameService;

    @GetMapping
    public List<GameDTO> getAllGames() {
        return gameService.getAllGames();
    }

    // Other controller methods
}

