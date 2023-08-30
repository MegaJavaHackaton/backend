package com.example.gigajava.group;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MyGroupRepository extends JpaRepository<MyGroup, Long> {
    MyGroup findByGroupName(String groupName);
}
