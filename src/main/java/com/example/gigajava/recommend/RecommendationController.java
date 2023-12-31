package com.example.gigajava.recommend;

import com.example.gigajava.game.Game;
import com.example.gigajava.game.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class RecommendationController {

    private final GameRecommendationService gameRecommendationService;
    private final GameService gameService;

    @Autowired
    public RecommendationController(GameRecommendationService gameRecommendationService, GameService gameService) {
        this.gameRecommendationService = gameRecommendationService;
        this.gameService = gameService;
    }

    @GetMapping("/recommendation")
    public ResponseEntity<Map<String, List<String>>> getRecommendation(@RequestParam Map<String, String> userAnswers) {
        // 사용자 답변을 기반으로 MBTI를 추출합니다.

        String mbti = determineMBTI(userAnswers);
        List<String> recommendedGameNames = gameService.recommendGamesForMBTI(mbti);
        Map<String, List<String>> recommendationMap = new HashMap<>();
        recommendationMap.put(mbti, recommendedGameNames);

        return ResponseEntity.ok(recommendationMap);
    }

    private String determineMBTI(Map<String, String> userAnswers) {
        int eCount = 0, iCount = 0, sCount = 0, nCount = 0, tCount = 0, fCount = 0, jCount = 0, pCount = 0;

        for (int i = 1; i <= 12; i++) {
            String questionId = "q" + i;
            String answer = userAnswers.get(questionId);

            if (answer != null) {
                switch (questionId) {
                    case "q1": case "q6": case "q8":
                        eCount += answer.equals("E") ? 1 : 0;
                        iCount += answer.equals("I") ? 1 : 0;
                        break;
                    case "q2": case "q3": case "q7":
                        sCount += answer.equals("S") ? 1 : 0;
                        nCount += answer.equals("N") ? 1 : 0;
                        break;
                    case "q4": case "q10": case "q11":
                        tCount += answer.equals("T") ? 1 : 0;
                        fCount += answer.equals("F") ? 1 : 0;
                        break;
                    case "q5": case "q9": case "q12":
                        jCount += answer.equals("J") ? 1 : 0;
                        pCount += answer.equals("P") ? 1 : 0;
                        break;
                }
            }
        }

        String mbti = "";
        mbti += eCount > iCount ? "E" : "I";
        mbti += sCount > nCount ? "S" : "N";
        mbti += tCount > fCount ? "T" : "F";
        mbti += jCount > pCount ? "J" : "P";

        return mbti;
    }
}
