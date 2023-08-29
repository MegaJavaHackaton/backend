package com.example.gigajava.group;

import com.example.gigajava.game.GameDTO;

import java.util.List;

public class GroupDTO {
    private int groupId;
    private String groupName;
    private List<GameDTO> games;

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

    public List<GameDTO> getGames() {
        return games;
    }

    public void setGames(List<GameDTO> games) {
        this.games = games;
    }
}
