package com.bills.food_ordering.entities.requests;

import lombok.Data;

@Data
public class UpdateCartItemRequest {

    private Long cartItemId;

    private Integer quantity;

}
