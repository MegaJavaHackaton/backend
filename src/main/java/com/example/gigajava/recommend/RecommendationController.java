package com.example.gigajava.recommend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class RecommendationController {

    private final GameRecommendationService gameRecommendationService;

    @Autowired
    public RecommendationController(GameRecommendationService gameRecommendationService) {
        this.gameRecommendationService = gameRecommendationService;
    }

    @GetMapping("/recommendation")
    public ResponseEntity<Map<String, String>> getRecommendation(@RequestParam Map<String, String> userAnswers) {
        // 사용자 답변을 기반으로 MBTI를 추출합니다.
        String mbti = determineMBTI(userAnswers);

        // MBTI에 따른 게임 추천을 받아옵니다.
        String recommendedGame = gameRecommendationService.recommendGameForMBTI(mbti);

        // 결과를 맵으로 포장하여 반환합니다.
        Map<String, String> recommendationMap = Map.of(mbti, recommendedGame);
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
