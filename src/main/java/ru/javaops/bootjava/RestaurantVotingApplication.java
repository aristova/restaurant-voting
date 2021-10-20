package ru.javaops.bootjava;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;

import org.springframework.context.annotation.Bean;
import ru.javaops.bootjava.repository.UserRepository;
import ru.javaops.bootjava.repository.VoteRepository;

@SpringBootApplication
public class RestaurantVotingApplication {
    private static final Logger log = LoggerFactory.getLogger(RestaurantVotingApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(RestaurantVotingApplication.class, args);
    }
    @Bean
    public CommandLineRunner demo(UserRepository repository, VoteRepository voteRepository) {
        return (args) -> {

        };
    }

}