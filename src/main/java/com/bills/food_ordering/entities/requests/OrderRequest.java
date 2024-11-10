package com.bills.food_ordering.entities.requests;

import com.bills.food_ordering.entities.Address;
import lombok.Data;

@Data
public class OrderRequest {

    private Long restaurantId;

    private Address deliveryAddress;

}
