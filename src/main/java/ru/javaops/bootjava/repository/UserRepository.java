package ru.javaops.bootjava.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.bootjava.model.User;

@Repository
@Transactional(readOnly = true)
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