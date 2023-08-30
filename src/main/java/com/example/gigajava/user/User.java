package com.example.gigajava.user;

import com.example.gigajava.group.MyGroup;
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
    @JoinColumn(name = "my_group_group_id", referencedColumnName = "group_id")
    private MyGroup group;

    public User() {

    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", group=" + group +
                '}';
    }

    public User orElse(Object o) {
        return null;
    }

    public void setGroup(MyGroup group) {
    }

    public void setMyGroup(MyGroup group) {
    }
}

