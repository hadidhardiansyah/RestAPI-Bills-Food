package com.bills.food_ordering.repositories;

import com.bills.food_ordering.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICartItemRepository extends JpaRepository<CartItem, Long> {
}
