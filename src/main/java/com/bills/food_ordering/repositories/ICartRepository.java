package com.bills.food_ordering.repositories;

import com.bills.food_ordering.entities.Cart;
import com.bills.food_ordering.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICartRepository extends JpaRepository<Cart, Long> {

    public Cart findByCustomerId(Long userId);

}
