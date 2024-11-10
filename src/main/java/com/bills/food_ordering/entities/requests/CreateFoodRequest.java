package com.bills.food_ordering.entities.requests;

import com.bills.food_ordering.entities.Category;
import com.bills.food_ordering.entities.IngredientsItem;
import lombok.Data;

import java.util.List;

@Data
public class CreateFoodRequest {

    private String name;

    private String description;

    private Long price;

    private Category foodCategory;

    private List<String> images;

    private Long restaurantId;

    private Boolean isVegetarian;

    private Boolean isSeasonal;

    private List<IngredientsItem> ingredients;

}
