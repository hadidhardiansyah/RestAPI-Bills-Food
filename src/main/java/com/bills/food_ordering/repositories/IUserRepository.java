package com.bills.food_ordering.repositories;

import com.bills.food_ordering.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {

    public User findByEmail(String username);

}
