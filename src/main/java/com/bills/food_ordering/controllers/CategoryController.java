package com.bills.food_ordering.controllers;

import com.bills.food_ordering.constants.UrlMapping;
import com.bills.food_ordering.entities.Category;
import com.bills.food_ordering.entities.User;
import com.bills.food_ordering.services.interfaces.ICategoryService;
import com.bills.food_ordering.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(UrlMapping.API)
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private IUserService userService;

    @PostMapping(UrlMapping.ADMIN_CATEGORY)
    public ResponseEntity<Category> createCategory(@RequestBody Category category, @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        Category createCategory = categoryService.createCategory(category.getName(), user.getId());

        return new ResponseEntity<>(createCategory, HttpStatus.CREATED);
    }

    @GetMapping(UrlMapping.CATEGORY_RESTAURANT)
    public ResponseEntity<List<Category>> getRestaurantCategory(@RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        List<Category> categories = categoryService.findCategoryByRestaurantId(user.getId());

        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

}
