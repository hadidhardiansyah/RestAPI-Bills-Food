package com.bills.food_ordering.controllers;

import com.bills.food_ordering.constants.UrlMapping;
import com.bills.food_ordering.entities.Restaurant;
import com.bills.food_ordering.entities.User;
import com.bills.food_ordering.entities.requests.CreateRestaurantRequest;
import com.bills.food_ordering.entities.responses.MessageResponse;
import com.bills.food_ordering.services.interfaces.IRestaurantService;
import com.bills.food_ordering.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(UrlMapping.ADMIN_RESTAURANTS)
public class AdminRestaurantController {

    @Autowired
    private IRestaurantService restaurantService;

    @Autowired
    private IUserService userService;

    @PostMapping
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody CreateRestaurantRequest createRestaurantRequest, @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Restaurant restaurant = restaurantService.createRestaurant(createRestaurantRequest, user);

        return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
    }

    @PutMapping(UrlMapping.ID)
    public ResponseEntity<Restaurant> updateRestaurant(@RequestBody CreateRestaurantRequest createRestaurantRequest, @RequestHeader("Authorization") String jwt, @PathVariable Long id) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Restaurant restaurant = restaurantService.updateRestaurant(id, createRestaurantRequest);

        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @DeleteMapping(UrlMapping.ID)
    public ResponseEntity<MessageResponse> deleteRestaurant(@RequestHeader("Authorization") String jwt, @PathVariable Long id) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        restaurantService.deleteRestaurant(id);

        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setMessage("Restaurant deleted successfullt");
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }

    @PutMapping(UrlMapping.ID + UrlMapping.STATUS)
    public ResponseEntity<Restaurant> updateRestaurantStatus(@RequestHeader("Authorization") String jwt, @PathVariable Long id) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Restaurant restaurant = restaurantService.updateRestaurantStatus(id);

        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @GetMapping(UrlMapping.USER)
    public ResponseEntity<Restaurant> findRestaurantByUserId(@RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Restaurant restaurant = restaurantService.getRestaurantByUserId(user.getId());

        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

}
