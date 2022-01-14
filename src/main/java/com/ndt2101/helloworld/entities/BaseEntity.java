package com.ndt2101.helloworld.entities;

import javax.persistence.*;

/**
 * Create by Tuan
 * 23:39
 * 12 Jan 2022
 */
@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
