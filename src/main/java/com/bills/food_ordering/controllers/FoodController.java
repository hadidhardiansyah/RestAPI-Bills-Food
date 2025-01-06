package com.bills.food_ordering.controllers;

import com.bills.food_ordering.constants.UrlMapping;
import com.bills.food_ordering.entities.Food;
import com.bills.food_ordering.entities.Restaurant;
import com.bills.food_ordering.entities.User;
import com.bills.food_ordering.entities.requests.CreateFoodRequest;
import com.bills.food_ordering.services.interfaces.IFoodService;
import com.bills.food_ordering.services.interfaces.IRestaurantService;
import com.bills.food_ordering.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(UrlMapping.FOOD)
public class FoodController {

    @Autowired
    private IFoodService foodService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IRestaurantService restaurantService;

    @GetMapping(UrlMapping.SEARCH)
    public ResponseEntity<List<Food>> searchFood(@RequestParam String name, @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        List<Food> foods = foodService.searchFood(name);

        return new ResponseEntity<>(foods, HttpStatus.OK);
    }

    @GetMapping(UrlMapping.RESTAURANT_ID)
    public ResponseEntity<List<Food>> getRestaurantFood(@RequestParam(required = false) Boolean vegetarian, @RequestParam(required = false) Boolean non_vegetarian, @RequestParam(required = false) Boolean seasonal, @RequestParam(required = false) String food_category, @PathVariable Long restaurantId, @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        List<Food> foods = foodService.getRestaurantsFood(restaurantId, vegetarian, non_vegetarian, seasonal, food_category);

        return new ResponseEntity<>(foods, HttpStatus.OK);
    }

}
