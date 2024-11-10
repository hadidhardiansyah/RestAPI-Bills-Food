package com.bills.food_ordering.entities.responses;

import com.bills.food_ordering.constants.UserRole;
import lombok.Data;

@Data
public class AuthResponse {

    private String jwt;

    private String message;

    private UserRole role;

}
