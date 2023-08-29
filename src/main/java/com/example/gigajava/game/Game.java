package com.example.gigajava.game;

import com.example.gigajava.group.MbtiGroup;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "game")
@Getter
@Setter
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_id")
    private int gameId;

    @Column(name = "game_name")
    private String gameName;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "group_id", referencedColumnName = "group_id")
    private MbtiGroup mbtiGroup;


    public Game() {
    }

    public Game(int gameId, String gameName, MbtiGroup mbtiGroup) {
        this.gameId = gameId;
        this.gameName = gameName;
        this.mbtiGroup = mbtiGroup;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public MbtiGroup getMbtiGroup() {
        return mbtiGroup;
    }

    public void setMbtiGroup(MbtiGroup mbtiGroup) {
        this.mbtiGroup = mbtiGroup;
    }
}

