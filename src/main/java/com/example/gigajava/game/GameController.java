package com.example.gigajava.game;

import com.example.gigajava.group.GroupRepository;
import com.example.gigajava.group.MyGroup;
import com.example.gigajava.recommend.AnswerDTO;
import com.example.gigajava.recommend.AnswerRequest;
import com.example.gigajava.recommend.GameRecommendationService;
import com.example.gigajava.user.User;
import com.example.gigajava.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class GameController {
    @Autowired
    private GameService gameService;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GameRecommendationService gameRecommendationService;
    @Autowired
    private GameRepository gameRepository;

    @PostMapping("/answers")
    public ResponseEntity<String> receiveAnswers(@RequestBody AnswerRequest answerRequest) {
        int userId = answerRequest.getUserId();
        List<AnswerDTO> answers = answerRequest.getAnswers();

        // 사용자의 MBTI를 계산하고 저장
        String mbti = calculateAndSaveMBTI(userId, answers);

        // MBTI를 기반으로 게임 추천
        List<Game> recommendedGames = gameRepository.findByGroupGroupName(mbti);
        List<String> recommendedGameNames = recommendedGames.stream()
                .map(Game::getGameName)
                .collect(Collectors.toList());

        saveUserMBTI(userId, mbti);
        return ResponseEntity.ok("Answers received successfully");
    }

    private void saveUserMBTI(int userId, String mbti) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            MyGroup group = groupRepository.findByGroupName(mbti);

            if (group != null) {
                user.setGroup(group);
                userRepository.save(user);
            } else {
                // 그룹을 찾지 못한 경우 처리
                String defaultGroupName = "DEFAULT"; // 기본 그룹 이름 설정
                MyGroup defaultGroup = groupRepository.findByGroupName(defaultGroupName);

                if (defaultGroup != null) {
                    user.setGroup(defaultGroup);
                    userRepository.save(user);
                    System.out.println("No matching group found for MBTI: " + mbti + ". Assigned to default group: " + defaultGroupName);
                } else {
                    System.out.println("No matching group found for MBTI: " + mbti + " and no default group available.");
                }
            }
        } else {
            // 사용자를 찾지 못한 경우 처리
            System.out.println("User not found for userId: " + userId);
        }
    }


    private String calculateAndSaveMBTI(int userId, List<AnswerDTO> answers) {
        // MBTI 계산 로직 구현

        int eCount = 0, iCount = 0, sCount = 0, nCount = 0, tCount = 0, fCount = 0, jCount = 0, pCount = 0;

        for (int i = 1; i <= 12; i++) {
            String questionId = "q" + i;
            AnswerDTO answer = answers.get(i - 1);  // 인덱스는 0부터 시작하므로 (i - 1)을 사용

            if (answer != null) {
                switch (questionId) {
                    case "q1":
                    case "q6":
                    case "q8":
                        eCount += answer.getAnswer().equals("E") ? 1 : 0;
                        iCount += answer.getAnswer().equals("I") ? 1 : 0;
                        break;
                    case "q2":
                    case "q3":
                    case "q7":
                        sCount += answer.getAnswer().equals("S") ? 1 : 0;
                        nCount += answer.getAnswer().equals("N") ? 1 : 0;
                        break;
                    case "q4":
                    case "q10":
                    case "q11":
                        tCount += answer.getAnswer().equals("T") ? 1 : 0;
                        fCount += answer.getAnswer().equals("F") ? 1 : 0;
                        break;
                    case "q5":
                    case "q9":
                    case "q12":
                        jCount += answer.getAnswer().equals("J") ? 1 : 0;
                        pCount += answer.getAnswer().equals("P") ? 1 : 0;
                        break;
                }
            }
        }

        String mbti = "";
        mbti += eCount > iCount ? "E" : "I";
        mbti += sCount > nCount ? "S" : "N";
        mbti += tCount > fCount ? "T" : "F";
        mbti += jCount > pCount ? "J" : "P";

        // 사용자의 MBTI를 업데이트
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // 이후 로직 계속 진행
            MyGroup group = groupRepository.findByGroupName(mbti);

            if (group != null) {
                user.setGroup(group);
                userRepository.save(user);
            } else {
                // 해당 MBTI에 맞는 그룹을 찾지 못한 경우 처리
                // 예: 로그를 출력하거나 기본 그룹을 할당하는 등의 처리
                String defaultGroupName = "DEFAULT"; // 기본 그룹 이름 설정
                MyGroup defaultGroup = groupRepository.findByGroupName(defaultGroupName);

                if (defaultGroup != null) {
                    user.setGroup(defaultGroup);
                    userRepository.save(user);
                    System.out.println("No matching group found for MBTI: " + mbti + ". Assigned to default group: " + defaultGroupName);
                } else {
                    System.out.println("No matching group found for MBTI: " + mbti + " and no default group available.");
                }
            }
        } else {
            // 해당 userId에 맞는 사용자를 찾지 못한 경우 처리
            // 예: 로그를 출력하거나 에러 메시지를 리턴하는 등의 처리
            System.out.println("User not found for userId: " + userId);
        }


// 계산된 MBTI 반환
        return mbti;
    }


//    @PostMapping("/answers")
//    public ResponseEntity<String> receiveAnswers(@RequestBody List<AnswerDTO> answers) {
//        // 각 답변을 분석하여 추천된 게임을 저장
//        for (AnswerDTO answer : answers) {
//            String questionId = answer.getQuestionId();
//            String userAnswer = answer.getAnswer();
//            int userId = answer.getUserId();
//
//            // 각 질문에 따라서 사용자의 선택을 MBTI로 변환 (가상의 변환 예시)
//            String mbti = convertAnswerToMBTI(userAnswer);
//
//            // 사용자의 MBTI를 User 엔티티에 저장
//            saveMBTIForUser(userId, mbti);
//
//            // MBTI를 기반으로 게임을 추천받고 결과를 출력 (가상의 예시)
//            List<String> recommendedGames = gameService.recommendGamesForMBTI(mbti);
//            System.out.println("Recommended games for " + mbti + ": " + recommendedGames);
//        }
//
//        return ResponseEntity.ok("Answers received successfully");
//    }
//
//    private void saveMBTIForUser(int userId, String mbti) {
//        User user = userRepository.findById(userId);
//        if (user != null) {
//            Group group = groupRepository.findByGroupName(mbti);
//            if (group != null) {
//                user.setGroup(group); // User 엔티티에 group 설정
//                userRepository.save(user); // 변경된 엔티티 저장
//            }
//        }
//    }
//
//    // 가상의 예시: 사용자의 선택을 MBTI로 변환하는 로직
//    private String convertAnswerToMBTI(String userAnswer) {
//        int eCount = 0, iCount = 0, sCount = 0, nCount = 0, tCount = 0, fCount = 0, jCount = 0, pCount = 0;
//
//        for (int i = 1; i <= 12; i++) {
//            String questionId = "q" + i;
//            String answer = userAnswer;
//
//            if (answer != null) {
//                switch (questionId) {
//                    case "q1":
//                    case "q6":
//                    case "q8":
//                        eCount += answer.equals("E") ? 1 : 0;
//                        iCount += answer.equals("I") ? 1 : 0;
//                        break;
//                    case "q2":
//                    case "q3":
//                    case "q7":
//                        sCount += answer.equals("S") ? 1 : 0;
//                        nCount += answer.equals("N") ? 1 : 0;
//                        break;
//                    case "q4":
//                    case "q10":
//                    case "q11":
//                        tCount += answer.equals("T") ? 1 : 0;
//                        fCount += answer.equals("F") ? 1 : 0;
//                        break;
//                    case "q5":
//                    case "q9":
//                    case "q12":
//                        jCount += answer.equals("J") ? 1 : 0;
//                        pCount += answer.equals("P") ? 1 : 0;
//                        break;
//                }
//            }
//        }
//
//        String mbti = "";
//        mbti += eCount > iCount ? "E" : "I";
//        mbti += sCount > nCount ? "S" : "N";
//        mbti += tCount > fCount ? "T" : "F";
//        mbti += jCount > pCount ? "J" : "P";
//
//        return mbti;
//    }


    private String getRecommendedGame(String questionId, String userAnswer) {
        // 각 질문에 따라 추천 게임 반환
        // 실제 비즈니스 로직에 맞게 구현
        return "Recommended Game for " + questionId + ": " + userAnswer;
    }
}

