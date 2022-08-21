package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
           Student DOJ = new Student(
                    "Daddy Omar Jeng",
                    "doj@gmail.com",
                   LocalDate.of(1995, Month.MAY, 22),
                    24
            );
            Student malleh = new Student(
                    "Bai Malleh Jeng",
                    "bmj@gmail.com",
                    LocalDate.of(200, Month.MAY, 26),
                    20
            );
            repository.saveAll(
                    List.of(DOJ, malleh)
            );
        };
    }
}
