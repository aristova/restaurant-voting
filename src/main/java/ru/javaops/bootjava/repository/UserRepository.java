package ru.javaops.bootjava.repository;

import java.util.List;

import ru.javaops.bootjava.model.User;


public class UserRepository {
    // null if not found, when updated
    User save(User user) {
        return null;
    }

    // false if not found
    boolean delete(int id) {
        return false;
    }

    // null if not found
    User get(int id){
        return null;
    }

    // null if not found
    User getByEmail(String email){
        return null;
    }

    List<User> getAll() {
        return null;
    }
}