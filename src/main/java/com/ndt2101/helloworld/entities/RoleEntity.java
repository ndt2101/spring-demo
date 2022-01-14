package com.ndt2101.helloworld.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Create by Tuan
 * 16:24
 * 13 Jan 2022
 */
@Entity
@Table(name = "role")
public class RoleEntity extends BaseEntity{
    @Column
    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<UserEntity> users;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }
}
