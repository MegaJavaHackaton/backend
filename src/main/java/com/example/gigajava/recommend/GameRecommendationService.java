package com.example.gigajava.recommend;

import com.example.gigajava.game.Game;
import com.example.gigajava.game.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameRecommendationService {

    private final GameRepository gameRepository;

    @Autowired
    public GameRecommendationService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public String recommendGameForMBTI(String mbti) {
        // 여기에서 MBTI 결과에 따라 게임을 추천하는 로직을 구현하세요.
        // gameRepository를 사용하여 MBTI에 따른 게임을 가져와서 반환하는 방식으로 구현합니다.

        // 아래는 가상의 예시 코드입니다.
        Game recommendedGame = gameRepository.findByGroupGroupName(mbti);
        if (recommendedGame != null) {
            return "Recommended Game for " + mbti + ": " + recommendedGame.getGameName();
        } else {
            return "No recommendation available";
        }
    }
}

