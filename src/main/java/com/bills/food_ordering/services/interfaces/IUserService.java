package com.bills.food_ordering.services.interfaces;

import com.bills.food_ordering.entities.User;

public interface IUserService {

    public User findUserByJwtToken(String jwt) throws Exception;

    public User findUserByEmail(String email) throws Exception;

}
