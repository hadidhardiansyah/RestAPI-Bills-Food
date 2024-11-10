package com.bills.food_ordering.services.interfaces;

import com.bills.food_ordering.entities.Order;
import com.bills.food_ordering.entities.User;
import com.bills.food_ordering.entities.requests.OrderRequest;

import java.util.List;

public interface IOrderService {

    public Order createOrder(OrderRequest orderRequest, User user) throws Exception;

    public Order updateOrder(Long orderId, String orderStatus) throws Exception;

    public void cancelOrder(Long orderId) throws Exception;

    public List<Order> getUsersOrder(Long userId) throws Exception;

    public List<Order> getRestaurantsOrder(Long restaurantId, String orderStatus) throws Exception;

    public Order findOrderById(Long orderId) throws Exception;

}
