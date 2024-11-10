package com.bills.food_ordering.controllers;

import com.bills.food_ordering.constants.UrlMapping;
import com.bills.food_ordering.entities.Cart;
import com.bills.food_ordering.entities.CartItem;
import com.bills.food_ordering.entities.requests.AddCartItemRequest;
import com.bills.food_ordering.entities.requests.UpdateCartItemRequest;
import com.bills.food_ordering.services.interfaces.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(UrlMapping.API)
public class CartController {

    @Autowired
    private ICartService cartService;

    @PutMapping(UrlMapping.CART_ADD)
    public ResponseEntity<CartItem> addItemToCart(@RequestBody AddCartItemRequest addCartItemRequest, @RequestHeader("Authorization") String jwt) throws Exception {
        CartItem cartItem = cartService.addItemToCart(addCartItemRequest, jwt);

        return new  ResponseEntity<>(cartItem, HttpStatus.OK);
    }

    @PutMapping(UrlMapping.CART_ITEM_UPDATE)
    public ResponseEntity<CartItem> updateCartItemQuantity(@RequestBody UpdateCartItemRequest updateCartItemRequest, @RequestHeader("Authorization") String jwt) throws Exception {
        CartItem cartItem = cartService.updateCartItemQuantity(updateCartItemRequest.getCartItemId(), updateCartItemRequest.getQuantity());

        return new  ResponseEntity<>(cartItem, HttpStatus.OK);
    }

    @DeleteMapping(UrlMapping.CART_ITEM_REMOVE)
    public ResponseEntity<Cart> removeCartItem(@PathVariable Long id, @RequestHeader("Authorization") String jwt) throws Exception {
        Cart cart = cartService.removeItemFromCart(id, jwt);

        return new  ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PutMapping(UrlMapping.CART_CLEAR)
    public ResponseEntity<Cart> clearCart(@RequestHeader("Authorization") String jwt) throws Exception {
        Cart cart = cartService.clearCart(jwt);

        return new  ResponseEntity<>(cart, HttpStatus.OK);
    }

    @GetMapping(UrlMapping.CART)
    public ResponseEntity<Cart> findUserCart(@RequestHeader("Authorization") String jwt) throws Exception {
        Cart cart = cartService.findCartByUserId(jwt);

        return new  ResponseEntity<>(cart, HttpStatus.OK);
    }

}
