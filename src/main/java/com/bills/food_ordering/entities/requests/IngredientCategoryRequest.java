package com.bills.food_ordering.entities.requests;

import lombok.Data;

@Data
public class IngredientCategoryRequest {

    private String name;

    private Long restaurantId;

}
