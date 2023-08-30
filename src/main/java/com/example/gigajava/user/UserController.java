package com.example.gigajava.user;

import com.example.gigajava.group.MyGroup;
import com.example.gigajava.group.MyGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MyGroupRepository groupRepository;

    @PostMapping("/users")
    public ResponseEntity<String> createUser(@RequestBody UserDTO userDTO) {
        // 새로운 User 객체 생성
        User newUser = new User();
        newUser.setUserId(userDTO.getUserId());

        // my_group_group_id 값을 넣기 위해 group 엔티티를 조회합니다.
        MyGroup group = (MyGroup) groupRepository.findById(userDTO.getMyGroupGroupId())
                .orElse(null); // group_id에 해당하는 그룹이 없는 경우 처리

        if (group != null) {
            newUser.setMyGroup(group);
        } else {
            // 해당 그룹을 찾지 못한 경우 처리 (기본 그룹을 할당하는 등의 처리)
            // 예: MyGroup defaultGroup = groupRepository.findByGroupName("DEFAULT");
            //     newUser.setMyGroup(defaultGroup);
        }

        // 새로운 사용자 정보 저장
        userRepository.save(newUser);

        return ResponseEntity.ok("User created successfully");
    }



}

