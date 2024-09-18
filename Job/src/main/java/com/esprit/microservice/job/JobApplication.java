package com.esprit.microservice.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JobApplication {

    public static void main(String[] args) {
        SpringApplication.run(JobApplication.class, args);
    }

    @Autowired
    private JobRepository repository;

    @Bean
    ApplicationRunner init() {
        return (args) -> {
            repository.save(new Job("Developer", true));
            repository.save(new Job("Tester", true));
            repository.save(new Job("Project Manager", false));
            repository.save(new Job("DevOps", true));

            repository.findAll().forEach(System.out::println);
        };
    }

}
