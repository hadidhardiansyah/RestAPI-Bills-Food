package com.bills.food_ordering.entities.requests;

import lombok.Data;

@Data
public class IngredientRequest {

    private String name;

    private Long categoryId;

    private Long restaurantId;

}
