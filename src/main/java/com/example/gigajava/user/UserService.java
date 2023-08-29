package com.example.gigajava.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();

        // Convert User entities to UserDTOs
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User user : users) {
            UserDTO userDTO = new UserDTO();
            userDTO.setUserId(user.getUserId());
            userDTO.setGroupId(user.getGroup().getGroupId()); // Assuming there's a Group reference
            userDTOs.add(userDTO);
        }

        return userDTOs;
    }

    // Other service methods
}
