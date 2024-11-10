package com.bills.food_ordering.controllers;

import com.bills.food_ordering.constants.UrlMapping;
import com.bills.food_ordering.entities.Food;
import com.bills.food_ordering.entities.Restaurant;
import com.bills.food_ordering.entities.User;
import com.bills.food_ordering.entities.requests.CreateFoodRequest;
import com.bills.food_ordering.entities.responses.MessageResponse;
import com.bills.food_ordering.services.interfaces.IFoodService;
import com.bills.food_ordering.services.interfaces.IRestaurantService;
import com.bills.food_ordering.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(UrlMapping.ADMIN_FOOD)
public class AdminFoodController {

    @Autowired
    private IFoodService foodService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IRestaurantService restaurantService;

    @PostMapping
    public ResponseEntity<Food> createFood(@RequestBody CreateFoodRequest createFoodRequest, @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Restaurant restaurant = restaurantService.findRestaurantById(createFoodRequest.getRestaurantId());
        Food food = foodService.createFood(createFoodRequest, createFoodRequest.getFoodCategory(), restaurant);

        return new ResponseEntity<>(food, HttpStatus.CREATED);
    }

    @DeleteMapping(UrlMapping.ID)
    public ResponseEntity<MessageResponse> deleteFood(@PathVariable Long id, @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        foodService.deleteFood(id);

        MessageResponse response = new MessageResponse();
        response.setMessage("Food deleted successfully");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(UrlMapping.ID)
    public ResponseEntity<Food> updateFoodAvailability(@PathVariable Long id, @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Food food = foodService.updateAvailabilityStatus(id);

        return new ResponseEntity<>(food, HttpStatus.OK);
    }

}
