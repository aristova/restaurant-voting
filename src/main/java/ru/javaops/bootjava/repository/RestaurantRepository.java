package ru.javaops.bootjava.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.bootjava.model.Restaurant;

import java.util.List;
@Repository
@Transactional(readOnly = true)
public class RestaurantRepository {
    // null if not found, when updated
    Restaurant save(Restaurant restaurant) {
        return null;
    }

    // false if not found
    boolean delete(int id) {
        return false;
    }

    // null if not found
    Restaurant get(int id) {
        return null;
    }


    List<Restaurant> getAll() {
        return null;
    }
}