package com.example.gigajava.game;

import com.example.gigajava.group.Group;
import com.example.gigajava.group.GroupRepository;
import com.example.gigajava.recommend.AnswerDTO;
import com.example.gigajava.recommend.GameRecommendationService;
import com.example.gigajava.user.User;
import com.example.gigajava.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class GameController {
    @Autowired
    private GameService gameService;
    private final Map<String, String> gameRecommendations = new HashMap<>();
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GameRecommendationService gameRecommendationService;
    @Autowired
    public GameController(GameRecommendationService gameRecommendationService, GameService gameService) {
        this.gameRecommendationService = gameRecommendationService;
        this.gameService = gameService;
    }

    @PostMapping("/answers")
    public ResponseEntity<String> receiveAnswers(@RequestBody List<AnswerDTO> answers) {
        // 각 답변을 분석하여 추천된 게임을 저장
        for (AnswerDTO answer : answers) {
            String questionId = answer.getQuestionId();
            String userAnswer = answer.getAnswer();

            // 각 질문에 따라서 사용자의 선택을 MBTI로 변환 (가상의 변환 예시)
            String mbti = convertAnswerToMBTI(userAnswer);

            // 사용자의 MBTI를 User 엔티티에 저장
            saveMBTIForUser(answer.getUserId(), mbti);

            // MBTI를 기반으로 게임을 추천받고 결과를 출력 (가상의 예시)
            List<String> recommendedGames = gameService.recommendGamesForMBTI(mbti);
            System.out.println("Recommended games for " + mbti + ": " + recommendedGames);
        }

        return ResponseEntity.ok("Answers received successfully");
    }

    private void saveMBTIForUser(int userId, String mbti) {
        User user = userRepository.findById(userId);
        if (user != null) {
            Group group = groupRepository.findByGroupName(mbti);
            if (group != null) {
                user.setGroup(group); // User 엔티티에 group 설정
                userRepository.save(user); // 변경된 엔티티 저장
            }
        }
    }

    // 가상의 예시: 사용자의 선택을 MBTI로 변환하는 로직
    private String convertAnswerToMBTI(String userAnswer) {
        // 이 부분을 실제로 사용자의 선택을 MBTI로 변환하는 로직으로 수정
        // 예: "A" -> "ISTJ", "B" -> "ENFP" 등
        return "ISTJ"; // 가상의 MBTI로 변환
    }


    private String getRecommendedGame(String questionId, String userAnswer) {
        // 각 질문에 따라 추천 게임 반환
        // 실제 비즈니스 로직에 맞게 구현
        return "Recommended Game for " + questionId + ": " + userAnswer;
    }
}

