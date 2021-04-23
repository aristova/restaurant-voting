package ru.javaops.bootjava;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;

import org.springframework.context.annotation.Bean;
import ru.javaops.bootjava.model.Role;
import ru.javaops.bootjava.model.User;
import ru.javaops.bootjava.repository.UserRepository;

import java.sql.SQLException;
import java.util.Date;

@SpringBootApplication
public class RestaurantVotingApplication {
    private static final Logger log = LoggerFactory.getLogger(RestaurantVotingApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(RestaurantVotingApplication.class, args);
    }
    @Bean
    public CommandLineRunner demo(UserRepository repository) {
        return (args) -> {
            // save a few customers

            // fetch all customers
            log.info("Users found with findAll():");
            log.info("-------------------------------");

            log.info("+++++++++++");

//             fetch an individual customer by ID
            User customer = repository.findById(1);
            log.info("User found with findById(1L):");
            log.info("--------------------------------");
            log.info(customer.toString());
            log.info("====");

            // for (User bauer : repository.findByLastName("Bauer")) {
            //  log.info(bauer.toString());
            // }
            log.info("");
        };
    }

}