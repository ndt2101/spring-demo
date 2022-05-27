package com.ndt2101.helloworld.controller;


import com.ndt2101.helloworld.dto.Student;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
//@Controller // dung cho thymeleaf
@RequestMapping
public class StudentController {

    private final List<Student> students = new ArrayList<>(List.of(
            new Student(1, "student1"),
            new Student(2, "student2"),
            new Student(3, "student3")
    ));

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/api/students")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_STUDENT_ADMIN')")
    public List<Student> getStudent() {
        return students;
    }


    @PostMapping("/api/students")
    @PreAuthorize("hasAuthority('permission:write')")
    public Student addStudent(@RequestBody Student student) {
        System.out.println(student.name);
        return student;
    }

    @PutMapping(path = "/api/students/{studentId}")
    @PreAuthorize("hasAuthority('permission:write')")
    public Student editStudent(@PathVariable("studentId") Integer studentId, @RequestBody Student student) {
        System.out.println(student + "" + studentId);
        return student;
    }

    @DeleteMapping(path = "/api/students/{studentId}")
    @PreAuthorize("hasAuthority('permission:delete')")
    public Integer deleteStudent(@PathVariable("studentId") Integer studentId) {
        System.out.println("delete" + "" + studentId);
        return studentId;
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }
}
