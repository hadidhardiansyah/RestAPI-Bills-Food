package com.bills.food_ordering.services.interfaces;

import com.bills.food_ordering.entities.Category;
import com.bills.food_ordering.entities.Food;
import com.bills.food_ordering.entities.Restaurant;
import com.bills.food_ordering.entities.requests.CreateFoodRequest;

import java.util.List;

public interface IFoodService {

    public Food createFood(CreateFoodRequest createFoodRequest, Category category, Restaurant restaurant);

    void deleteFood(Long foodId) throws Exception;

    public List<Food> getRestaurantsFood(Long restaurantId, Boolean isVegetarian, Boolean isNonVegetarian, Boolean isSeasonal, String foodCategory);

    public List<Food> searchFood(String keyword);

    public Food findFoodById(Long foodId) throws Exception;

    public Food updateAvailabilityStatus(Long foodId) throws Exception;

}
