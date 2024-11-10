package com.bills.food_ordering.services.implementations;

import com.bills.food_ordering.entities.Cart;
import com.bills.food_ordering.entities.CartItem;
import com.bills.food_ordering.entities.Food;
import com.bills.food_ordering.entities.User;
import com.bills.food_ordering.entities.requests.AddCartItemRequest;
import com.bills.food_ordering.repositories.ICartItemRepository;
import com.bills.food_ordering.repositories.ICartRepository;
import com.bills.food_ordering.services.interfaces.ICartService;
import com.bills.food_ordering.services.interfaces.IFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService implements ICartService {

    @Autowired
    private ICartRepository cartRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ICartItemRepository cartItemRepository;

    @Autowired
    private IFoodService foodService;

    @Override
    public CartItem addItemToCart(AddCartItemRequest cartItemRequest, String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Food food = foodService.findFoodById(cartItemRequest.getFoodId());
        Cart cart = cartRepository.findByCustomerId(user.getId());

        for (CartItem cartItem : cart.getItems()) {
            if (cartItem.getFood().equals(food)) {
                Integer newQuantity = cartItem.getQuantity() + cartItemRequest.getQuantity();
                return updateCartItemQuantity(cartItem.getId(), newQuantity);
            }
        }

        CartItem cartItem = new CartItem();
        cartItem.setFood(food);
        cartItem.setCart(cart);
        cartItem.setQuantity(cartItemRequest.getQuantity());
        cartItem.setIngredients(cartItemRequest.getIngredients());
        cartItem.setTotalPrice(cartItemRequest.getQuantity() * food.getPrice());

        CartItem savedCartItem = cartItemRepository.save(cartItem);

        cart.getItems().add(savedCartItem);

        return savedCartItem;
    }

    @Override
    public CartItem updateCartItemQuantity(Long cartItemId, Integer quantity) throws Exception {
        Optional<CartItem> cartItem = cartItemRepository.findById(cartItemId);

        if (cartItem.isEmpty()) {
            throw new Exception("Cart item not found");
        }

        CartItem item = cartItem.get();
        item.setQuantity(quantity);
        item.setTotalPrice(item.getFood().getPrice() * quantity);

        return cartItemRepository.save(item);
    }

    @Override
    public Cart removeItemFromCart(Long cartItemId, String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Cart cart = cartRepository.findByCustomerId(user.getId());

        Optional<CartItem> cartItem = cartItemRepository.findById(cartItemId);

        if (cartItem.isEmpty()) {
            throw new Exception("Cart item not found");
        }

        CartItem item = cartItem.get();

        cart.getItems().remove(item);

        return cartRepository.save(cart);
    }

    @Override
    public Long calculateCartTotal(Cart cart) throws Exception {
        Long total = 0L;

        for (CartItem cartItem : cart.getItems()) {
            total += cartItem.getFood().getPrice() * cartItem.getQuantity();
        }

        return total;
    }

    @Override
    public Cart findCartById(Long id) throws Exception {
        Optional<Cart> cart = cartRepository.findById(id);

        if (cart.isEmpty()) {
            throw new Exception("Cart not found with id " + id);
        }

        return cart.get();
    }

    @Override
    public Cart findCartByUserId(Long userId) throws Exception {
        Cart cart = cartRepository.findByCustomerId(userId);
        cart.setTotal(calculateCartTotal(cart));

        return cart;
    }

    @Override
    public Cart clearCart(Long userId) throws Exception {
        Cart cart = findCartByUserId(userId);
        cart.getItems().clear();

        return cartRepository.save(cart);
    }
}
