package com.example.demo.student;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class StudentService {

    public List<Student> getStudents(){
        return List.of(
                new Student(
                        1L, //because it is Long
                        "Omar Jeng",
                        "doj@gmail.com",
                        LocalDate.of(2020, Month.MARCH, 22),
                        13
                )
        );
    }
}
