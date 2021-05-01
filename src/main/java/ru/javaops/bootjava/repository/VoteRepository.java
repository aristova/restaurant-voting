package ru.javaops.bootjava.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.bootjava.model.Vote;

import java.util.List;
@Repository
@Transactional(readOnly = true)
public class  VoteRepository {
    // null if not found, when updated
    Vote save(Vote vote) {
        return null;
    }

    // false if not found
    boolean delete(int id) {
        return false;
    }

    // null if not found
    Vote get(int id) {
        return null;
    }

    List<Vote> getAll() {
        return null;
    }
}