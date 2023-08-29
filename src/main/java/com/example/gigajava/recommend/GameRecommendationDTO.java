package com.example.gigajava.recommend;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GameRecommendationDTO {
    private int groupId;
    private List<String> recommendedGames;
}

