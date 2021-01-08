package com.restapi.voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.restapi.voting.model.Dish;

import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface DishRepository extends JpaRepository<Dish, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Dish d WHERE d.id=:id")
    int delete(@Param("id") int id);

    @Query("SELECT d FROM Dish d WHERE d.restaurantId=:restaurant_id ORDER BY d.name")
    List<Dish> findAllByRestaurantId(@Param("restaurant_id") int restaurant_id);

    @Query("SELECT d FROM Dish d WHERE d.restaurantId=:restaurant_id AND d.date=:date ORDER BY d.name")
    List<Dish> findAllByRestaurantIdToday(@Param("restaurant_id") int restaurant_id, @Param("date") LocalDate date);
}
