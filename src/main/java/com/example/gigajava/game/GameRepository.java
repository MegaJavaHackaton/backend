package com.example.gigajava.game;

import com.example.gigajava.group.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {
    @Query("SELECT g FROM Game g INNER JOIN g.group gr WHERE gr = :group")
    List<Game> findRecommendedGamesByGroup(Group group);
}


