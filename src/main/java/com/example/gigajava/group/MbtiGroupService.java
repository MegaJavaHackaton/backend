package com.example.gigajava.group;

import com.example.gigajava.game.GameDTO;
import com.example.gigajava.game.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MbtiGroupService {

    @Autowired
    private MbtiGroupRepository mbtigroupRepository;

    @Autowired
    private GameService gameService;

    public List<MbtiGroupDTO> getAllGroups() {
        List<MbtiGroup> groups = mbtigroupRepository.findAll();

        // Convert Group entities to GroupDTOs
        List<MbtiGroupDTO> groupDTOs = new ArrayList<>();
        for (MbtiGroup group : groups) {
            MbtiGroupDTO groupDTO = new MbtiGroupDTO();
            groupDTO.setGroupId(group.getGroupId());
            groupDTO.setGroupName(group.getGroupName());

            List<GameDTO> gameDTOs = gameService.getAllGames();
            groupDTO.setGames(gameDTOs);

            groupDTOs.add(groupDTO);
        }

        return groupDTOs;
    }

}


