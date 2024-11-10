package com.bills.food_ordering.controllers;

import com.bills.food_ordering.constants.UrlMapping;
import com.bills.food_ordering.entities.IngredientCategory;
import com.bills.food_ordering.entities.IngredientsItem;
import com.bills.food_ordering.entities.requests.IngredientCategoryRequest;
import com.bills.food_ordering.entities.requests.IngredientRequest;
import com.bills.food_ordering.services.interfaces.IIngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(UrlMapping.INGREDIENTS)
public class IngredientController {

    @Autowired
    private IIngredientService iIngredientService;

    @PostMapping(UrlMapping.CATEGORY)
    public ResponseEntity<IngredientCategory> createIngredientCategory(@RequestBody IngredientCategoryRequest ingredientCategoryRequest) throws Exception {
        IngredientCategory ingredientCategory = iIngredientService.createIngredientCategory(ingredientCategoryRequest.getName(), ingredientCategoryRequest.getRestaurantId());

        return new ResponseEntity<>(ingredientCategory, HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<IngredientsItem> createIngredientItem(@RequestBody IngredientRequest ingredientRequest) throws Exception {
        IngredientsItem ingredientsItem = iIngredientService.createIngredientItem(ingredientRequest.getRestaurantId(), ingredientRequest.getName(), ingredientRequest.getCategoryId());

        return new ResponseEntity<>(ingredientsItem, HttpStatus.CREATED);
    }

    @PutMapping(UrlMapping.ID + UrlMapping.STOKE)
    public ResponseEntity<IngredientsItem> updateIngredientStock(@PathVariable Long id) throws Exception {
        IngredientsItem ingredientsItem = iIngredientService.updateStock(id);

        return new ResponseEntity<>(ingredientsItem, HttpStatus.OK);
    }

    @GetMapping(UrlMapping.RESTAURANT + UrlMapping.ID)
    public ResponseEntity<List<IngredientsItem>> getRestaurantIngredient(@PathVariable Long id) throws Exception {
        List<IngredientsItem> ingredientsItems = iIngredientService.findRestaurantIngredients(id);

        return new ResponseEntity<>(ingredientsItems, HttpStatus.OK);
    }

    @GetMapping(UrlMapping.RESTAURANT + UrlMapping.ID + UrlMapping.CATEGORY)
    public ResponseEntity<List<IngredientCategory>> getRestaurantIngredientCategory(@PathVariable Long id) throws Exception {
        List<IngredientCategory> ingredientCategories = iIngredientService.findIngredientCategoryByRestaurantId(id);

        return new ResponseEntity<>(ingredientCategories, HttpStatus.OK);
    }

}
