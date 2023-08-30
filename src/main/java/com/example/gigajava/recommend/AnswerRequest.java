package com.example.gigajava.recommend;

import java.util.List;

public class AnswerRequest {
    private int userId;
    private List<AnswerDTO> answers;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<AnswerDTO> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerDTO> answers) {
        this.answers = answers;
    }
}

