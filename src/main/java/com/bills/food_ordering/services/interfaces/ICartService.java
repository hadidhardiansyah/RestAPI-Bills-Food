package com.bills.food_ordering.services.interfaces;

import com.bills.food_ordering.entities.Cart;
import com.bills.food_ordering.entities.CartItem;
import com.bills.food_ordering.entities.requests.AddCartItemRequest;

public interface ICartService {

    public CartItem addItemToCart(AddCartItemRequest cartItemRequest, String jwt) throws Exception;

    public CartItem updateCartItemQuantity(Long cartItemId, Integer quantity) throws Exception;

    public Cart removeItemFromCart(Long cartItemId, String jwt) throws Exception;

    public Long calculateCartTotal(Cart cart) throws Exception;

    public Cart findCartById(Long id) throws Exception;

    public Cart findCartByUserId(String jwt) throws Exception;

    public Cart clearCart(String jwt) throws Exception;

}
