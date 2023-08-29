package com.example.gigajava.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/groups")
public class MbtiGroupController {

    @Autowired
    private MbtiGroupService mbtiGroupService;

    @GetMapping
    public List<MbtiGroupDTO> getAllGroups() {
        return mbtiGroupService.getAllGroups();
    }

    // Other controller methods
}


