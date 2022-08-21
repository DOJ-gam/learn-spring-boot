package com.example.demo.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    //SELECT * FROM student WHERE email = ?
    @Query("SELECT s from Student s where s.email = ?1") //JB SQL and not SQL
    Optional<Student> findStudentByEmail(String email);
}
