package com.bills.food_ordering.repositories;

import com.bills.food_ordering.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICategoryRepository extends JpaRepository<Category, Long> {

    public List<Category> findByRestaurantId(Long id);

}
