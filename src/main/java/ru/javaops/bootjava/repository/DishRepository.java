package ru.javaops.bootjava.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.bootjava.model.Dish;
import ru.javaops.bootjava.model.Vote;

import java.util.Optional;

@Transactional(readOnly = true)
public interface DishRepository  extends JpaRepository<Dish, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE FROM Dish d WHERE d.id=:id")
    int delete(@Param("id") int id);

    Page<Dish> findByRestaurantId(Integer restaurantId, Pageable pageable);
    Optional<Dish> findByIdAndRestaurantId(Integer id, Integer restaurantId);
}