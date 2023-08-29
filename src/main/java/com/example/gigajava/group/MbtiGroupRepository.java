package com.example.gigajava.group;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MbtiGroupRepository extends JpaRepository<MbtiGroup, Integer> {

}

