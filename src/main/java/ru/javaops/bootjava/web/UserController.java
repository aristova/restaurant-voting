package ru.javaops.bootjava.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.javaops.bootjava.model.User;
import ru.javaops.bootjava.repository.UserRepository;

import java.util.List;


@RestController
@RequestMapping(value = "/api", produces = { MediaType.APPLICATION_JSON_VALUE })

public class UserController {

    @Autowired
    private UserRepository repository;

    public UserRepository getRepository() {
        return repository;
    }

    @GetMapping(value = "/users")
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    @PostMapping("/users")
    User createOrSaveUser(@RequestBody User newUser) {
        return repository.save(newUser);
    }

    @GetMapping("/users/{id}")
    User getUserById(@PathVariable Integer id) {
        return repository.findById(id).get();
    }

    @PutMapping("/users/{id}")
    User updateUser(@RequestBody User newUser, @PathVariable Integer id) {

        return repository.findById(id).map(user -> {
            user.setName(newUser.getName());
            user.setEmail(newUser.getEmail());
            return repository.save(user);
        }).orElseGet(() -> {
            newUser.setId(id);
            return repository.save(newUser);
        });
    }

    @DeleteMapping("/users/{id}")
    void deleteUser(@PathVariable Integer id) {
        repository.deleteById(id);
    }



}
