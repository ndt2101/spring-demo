package com.ndt2101.helloworld.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student {

    public Integer id;
    public String name;

    public Student(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
