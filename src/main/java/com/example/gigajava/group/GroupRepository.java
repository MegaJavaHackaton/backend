package com.example.gigajava.group;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<MyGroup, Integer> {

    MyGroup findByGroupName(String mbti);
}

