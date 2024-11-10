package com.bills.food_ordering.repositories;

import com.bills.food_ordering.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICartRepository extends JpaRepository<Cart, Long> {



}
