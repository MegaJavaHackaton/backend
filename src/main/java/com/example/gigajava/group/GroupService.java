package com.example.gigajava.group;

import com.example.gigajava.game.GameDTO;
import com.example.gigajava.game.GameService;
import com.example.gigajava.recommend.GameRecommendationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private GameService gameService;

    public List<GroupDTO> getAllGroups() {
        List<Group> groups = groupRepository.findAll();

        // Convert Group entities to GroupDTOs
        List<GroupDTO> groupDTOs = new ArrayList<>();
        for (Group group : groups) {
            GroupDTO groupDTO = new GroupDTO();
            groupDTO.setGroupId(group.getGroupId());
            groupDTO.setGroupName(group.getGroupName());

            List<GameDTO> gameDTOs = gameService.getAllGames();
            groupDTO.setGames(gameDTOs);

            groupDTOs.add(groupDTO);
        }

        return groupDTOs;
    }

}


