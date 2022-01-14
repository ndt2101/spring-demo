package com.ndt2101.helloworld.entities;

import javax.persistence.*;

/**
 * Create by Tuan
 * 16:34
 * 13 Jan 2022
 */
@Entity
@Table(name = "post")
public class PostEntity extends BaseEntity{

    @Column
    private String title;

    @Column
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
