package com.example.gigajava.user;

import com.example.gigajava.group.Group;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    public User() {

    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", group=" + group +
                '}';
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public User orElse(Object o) {
        return null;
    }
}

