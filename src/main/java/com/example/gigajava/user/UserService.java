package com.example.gigajava.user;

import com.example.gigajava.group.Group;
import com.example.gigajava.group.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final GroupRepository groupRepository;
    private final UserRepository userRepository;

    @Autowired
    public UserService(GroupRepository groupRepository, UserRepository userRepository) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
    }

}
