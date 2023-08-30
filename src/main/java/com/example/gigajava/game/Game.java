package com.example.gigajava.game;

import com.example.gigajava.group.MyGroup;
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
    @JoinColumn(name = "my_group_group_id", referencedColumnName = "group_id")
    private MyGroup group;

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

    public MyGroup getGroup() {
        return group;
    }

    public void setGroup(MyGroup group) {
        this.group = group;
    }
}

