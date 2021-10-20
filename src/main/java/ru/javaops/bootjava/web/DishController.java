package ru.javaops.bootjava.web;


import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.javaops.bootjava.model.Dish;
import ru.javaops.bootjava.model.User;
import ru.javaops.bootjava.model.Vote;
import ru.javaops.bootjava.repository.DishRepository;
import ru.javaops.bootjava.repository.RestaurantRepository;
import ru.javaops.bootjava.repository.UserRepository;
import ru.javaops.bootjava.repository.VoteRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api", produces = { MediaType.APPLICATION_JSON_VALUE })
public class DishController {


    @Autowired
    private DishRepository repository;
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private UserRepository userRepository;


    public DishRepository getRepository() {
        return repository;
    }
    public RestaurantRepository getRestaurantRepository() {
        return restaurantRepository;
    }
    public UserRepository getUserRepository() {
        return userRepository;
    }


    @GetMapping(value = "/dishes")
    public List<Dish> getAllDishes() {
        return repository.findAll();
    }



    @GetMapping("/restaurants/{restaurantId}/dishes")
    public Page<Dish> getAllDishesByRestaurantId(@PathVariable (value = "restaurantId") Integer restaurantId,
                                                Pageable pageable) {
        return repository.findByRestaurantId(restaurantId, pageable);
    }

    @PostMapping("/restaurants/{restaurantId}/dishes")
    public Dish createDish(@PathVariable (value = "restaurantId") Integer restaurantId,
                           @RequestBody Dish newDish) {
        return restaurantRepository.findById(restaurantId).map(restaurant -> {
            newDish.setRestaurant(restaurant);
            return repository.save(newDish);
        }).orElseThrow(() -> new IllegalArgumentException("PostId " + restaurantId + " not found"));
    }


    @GetMapping("/dishes/{id}")
    Dish getDishById(@PathVariable Integer id) {
        return repository.findById(id).get();
    }

    @PutMapping("/dishes/{id}")
    Dish updateDish(@RequestBody Dish newDish, @PathVariable Integer id) {

        return repository.findById(id).map(dish -> {
            dish.setName(newDish.getName());
            return repository.save(dish);
        }).orElseGet(() -> {
            newDish.setId(id);
            return repository.save(newDish);
        });
    }

    @DeleteMapping("/dishes/{id}")
    void deleteDish(@PathVariable Integer id) {
        repository.deleteById(id);
    }



}
