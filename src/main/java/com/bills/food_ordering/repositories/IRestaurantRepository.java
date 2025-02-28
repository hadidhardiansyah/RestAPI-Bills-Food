package com.bills.food_ordering.repositories;

import com.bills.food_ordering.entities.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IRestaurantRepository extends JpaRepository<Restaurant, Long> {

    @Query("SELECT r FROM Restaurant r WHERE lower(r.name) LIKE lower(concat('%', :query, '%')) " +
            "OR lower(r.cuisineType) LIKE lower(concat('%', :query, '%')) ")
    List<Restaurant> findBySearchQuery(String query);
    Restaurant findByOwnerId(Long userId);

}
