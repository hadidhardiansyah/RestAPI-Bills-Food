package com.bills.food_ordering.controllers;

import com.bills.food_ordering.constants.UrlMapping;
import com.bills.food_ordering.dto.RestaurantDto;
import com.bills.food_ordering.entities.Restaurant;
import com.bills.food_ordering.entities.User;
import com.bills.food_ordering.entities.requests.CreateRestaurantRequest;
import com.bills.food_ordering.services.interfaces.IRestaurantService;
import com.bills.food_ordering.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(UrlMapping.RESTAURANTS)
public class RestaurantController {

    @Autowired
    private IRestaurantService restaurantService;

    @Autowired
    private IUserService userService;

    @GetMapping(UrlMapping.SEARCH)
    public ResponseEntity<List<Restaurant>> searchRestaurant(@RequestHeader("Authorization") String jwt, @RequestParam String keyword) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        List<Restaurant> restaurant = restaurantService.searchRestaurant(keyword);

        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Restaurant>> getAllRestaurant(@RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        List<Restaurant> restaurant = restaurantService.getAllRestaurant();

        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @GetMapping(UrlMapping.ID)
    public ResponseEntity<Restaurant> findRestaurantById(@RequestHeader("Authorization") String jwt, @PathVariable Long id) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Restaurant restaurant = restaurantService.findRestaurantById(id);

        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @PutMapping(UrlMapping.ID + UrlMapping.ADD_FAVORITES)
    public ResponseEntity<RestaurantDto> addToFavorites(@RequestHeader("Authorization") String jwt, @PathVariable Long id) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        RestaurantDto restaurantDto = restaurantService.addToFavorites(id, user);

        return new ResponseEntity<>(restaurantDto, HttpStatus.OK);
    }

}
