package ru.javaops.bootjava.web;


import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javaops.bootjava.model.User;
import ru.javaops.bootjava.model.Vote;
import ru.javaops.bootjava.repository.RestaurantRepository;
import ru.javaops.bootjava.repository.UserRepository;
import ru.javaops.bootjava.repository.VoteRepository;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(value = "/api", produces = { MediaType.APPLICATION_JSON_VALUE })

public class VoteController {

    @Autowired
    private VoteRepository repository;
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private UserRepository userRepository;


    public VoteRepository getRepository() {
        return repository;
    }
    public RestaurantRepository getRestaurantRepository() {
        return restaurantRepository;
    }
    public UserRepository getUserRepository() {
        return userRepository;
    }

    @GetMapping(value = "/votes")
    public List<Vote> getAllVotes() {
        return repository.findAll();
    }


    // TODO https://www.callicoder.com/hibernate-spring-boot-jpa-one-to-many-mapping-example/



    @GetMapping("/restaurants/{restaurantId}/votes")
    public Page<Vote> getAllVotesByRestaurantId(@PathVariable (value = "restaurantId") Integer restaurantId,
                                             Pageable pageable) {
        return repository.findByRestaurantId(restaurantId, pageable);
    }


    @PostMapping("/restaurants/{restaurantId}/votes")
    public Vote createVote(@PathVariable (value = "restaurantId") Integer restaurantId,
                                 @RequestBody Vote newVote) {
        return restaurantRepository.findById(restaurantId).map(restaurant -> {
            newVote.setRestaurant(restaurant);
            Optional<User> user = userRepository.findById(1);

            User currentUser = null;
            try {
                currentUser = user.orElseThrow(
                        () -> new NotFoundException("User not found with userId "));
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
            newVote.setUser(currentUser);
            return repository.save(newVote);
        }).orElseThrow(() -> new IllegalArgumentException("PostId " + restaurantId + " not found"));
    }


   // @PostMapping("/votes")
   // Vote createOrSaveVote(@RequestBody Vote newVote) {
   //     newVote.setRestaurant(newVote.getRestaurant());

     //   return repository.save(newVote);
    //}

    @GetMapping("/votes/{id}")
    Vote getVoteById(@PathVariable Integer id) {
        return repository.findById(id).get();
    }

    @PutMapping("/votes/{id}")
    Vote updateVote(@RequestBody Vote newVote, @PathVariable Integer id) {

        return repository.findById(id).map(vote -> {
            vote.setDate(newVote.getDate());
            vote.setRestaurant(newVote.getRestaurant());
            vote.setUser(newVote.getUser());
            return repository.save(vote);
        }).orElseGet(() -> {
            newVote.setId(id);
            return repository.save(newVote);
        });
    }

    @DeleteMapping("/votes/{id}")
    void deleteVote(@PathVariable Integer id) {
        repository.deleteById(id);
    }



}
