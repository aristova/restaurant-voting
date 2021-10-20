package ru.javaops.bootjava.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.javaops.bootjava.model.Restaurant;
import ru.javaops.bootjava.repository.RestaurantRepository;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;


@RestController
@RequestMapping(value = "/api", produces = { MediaType.APPLICATION_JSON_VALUE })

public class RestaurantController {

    @Autowired
    private RestaurantRepository repository;

    public RestaurantRepository getRepository() {
        return repository;
    }

    @GetMapping(value = "/restaurants")
    public List<Restaurant> getAllRestaurants() {
        return repository.findAll();
    }

    @PostMapping("/restaurants")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    Restaurant createOrSaveRestaurant(@RequestBody Restaurant newRestaurant) {
        return repository.save(newRestaurant);
    }

    @GetMapping("/restaurants/{id}")
    Restaurant getRestaurantById(@PathVariable Integer id) {
        return repository.findById(id).get();
    }

    @PutMapping("/restaurants/{id}")
    Restaurant updateRestaurant(@RequestBody Restaurant newRestaurant, @PathVariable Integer id) {

        return repository.findById(id).map(restaurant -> {
            restaurant.setName(newRestaurant.getName());
            return repository.save(restaurant);
        }).orElseGet(() -> {
            newRestaurant.setId(id);
            return repository.save(newRestaurant);
        });
    }

    @DeleteMapping("/restaurants/{id}")
    void deleteRestaurant(@PathVariable Integer id) {
        repository.deleteById(id);
    }



}
