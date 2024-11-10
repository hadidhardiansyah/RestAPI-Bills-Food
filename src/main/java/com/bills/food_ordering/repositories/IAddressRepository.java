package com.bills.food_ordering.repositories;

import com.bills.food_ordering.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAddressRepository extends JpaRepository<Address, Long> {
}
