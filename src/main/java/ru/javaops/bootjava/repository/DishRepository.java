package ru.javaops.bootjava.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.bootjava.model.Dish;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public class DishRepository {
    // null if not found, when updated
    Dish save(Dish dish) {
        return null;
    }

    // false if not found
    boolean delete(int id) {
        return false;
    }

    // null if not found
    Dish get(int id) {
        return null;
    }

    // null if not found
    Dish getByRestaurant(int restaurant) {
        return null;
    }

    List<Dish> getAll() {
        return null;
    }
}