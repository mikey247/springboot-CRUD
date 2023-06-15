package com.mikey.springbootdebut.student;

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
            Student mikeyUme = new Student(
                    "Mikey Ume",
                    "mikey@gmail.com",
                    LocalDate.of(1946, Month.JUNE, 15)
            );
            Student melissaMcarthy = new Student(
                    "Melissa Mcarthy",
                    "melissa@gmail.com",
                    LocalDate.of(1946, Month.JUNE, 15)
            );
        repository.saveAll(List.of(mikeyUme, melissaMcarthy));
        };
    }
}
