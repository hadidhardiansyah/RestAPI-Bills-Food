package com.bills.food_ordering.entities.requests;

import lombok.Data;

@Data
public class LoginRequest {

    private String email;

    private String password;

}