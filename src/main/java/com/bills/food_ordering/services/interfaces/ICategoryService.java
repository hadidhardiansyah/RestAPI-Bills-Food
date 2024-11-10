package com.bills.food_ordering.services.interfaces;

import com.bills.food_ordering.entities.Category;

import java.util.List;

public interface ICategoryService {

    public Category createCategory(String name, Long userId) throws Exception;

    public List<Category> findCategoryByRestaurantId(Long id) throws Exception;

    public Category findCategoryById(Long id) throws Exception;

}
