package com.example.gigajava.group;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MyGroupRepository extends JpaRepository<MyGroup, Long> {
    MyGroup findByGroupName(String groupName);

    Optional<Object> findById(int myGroupGroupId);
}
