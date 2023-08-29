package com.example.gigajava.group;

import com.example.gigajava.game.Game;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "mbti_group")
@Getter
@Setter
public class MbtiGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id")
    private int groupId;

    @Column(name = "group_name")
    private String groupName;

    @OneToMany(mappedBy = "mbtiGroup", cascade = CascadeType.ALL)
    private List<Game> games = new ArrayList<>();

    public MbtiGroup() {
    }

    public MbtiGroup(int groupId, String groupName, List<Game> games) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.games = games;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    @Override
    public String toString() {
        return "MbtiGroup{" +
                "groupId=" + groupId +
                ", groupName='" + groupName + '\'' +
                ", games=" + games +
                '}';
    }
}
