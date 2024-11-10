package com.bills.food_ordering.services.interfaces;

import com.bills.food_ordering.dto.RestaurantDto;
import com.bills.food_ordering.entities.Restaurant;
import com.bills.food_ordering.entities.User;
import com.bills.food_ordering.entities.requests.CreateRestaurantRequest;

import java.util.List;

public interface IRestaurantService {

    public Restaurant createRestaurant(CreateRestaurantRequest createRestaurantRequest, User user);

    public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updatedRestaurant) throws Exception;

    public void deleteRestaurant(Long restaurantId) throws Exception;

    public List<Restaurant> getAllRestaurant();

    public List<Restaurant> searchRestaurant(String keyword);

    public Restaurant findRestaurantById(Long id) throws Exception;

    public Restaurant getRestaurantByUserId(Long userId) throws Exception;

    public RestaurantDto addToFavorites(Long restaurantId, User user) throws Exception;

    public Restaurant updateRestaurantStatus(Long id) throws Exception;

}
