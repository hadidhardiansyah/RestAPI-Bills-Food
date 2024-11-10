package com.bills.food_ordering.constants;

public abstract class UrlMapping {

    public static final String API = "/api";

    public static final String API_ADMIN = "/api/admin";

    public static final String AUTH = "/auth";

    public static final String SIGN_UP = "/signup";

    public static final String SIGN_IN = "/signin";

    public static final String USERS = "/api/users";

    public static final String PROFILE = "/profile";

    public static final String ADMIN_RESTAURANTS = "/api/admin/restaurants";

    public static final String RESTAURANTS = "/api/restaurants";

    public static final String RESTAURANT = "/restaurant";

    public static final String RESTAURANT_ID = "restaurant/{restaurantId}";

    public static final String ID = "/{id}";

    public static final String STATUS = "/status";

    public static final String USER = "/user";

    public static final String SEARCH = "/search";

    public static final String ADD_FAVORITES = "/add-favorites";

    public static final String ADMIN_FOOD = "/api/admin/food";

    public static final String FOOD = "/api/food";

    public static final String ADMIN_CATEGORY = "/admin/category";

    public static final String CATEGORY_RESTAURANT = "/category/restaurant";

    public static final String CATEGORY = "/category";

    public static final String INGREDIENTS = "api/admin/ingredients";

    public static final String STOKE = "/stoke";

    public static final String CART = "/cart";

    public static final String CART_ADD = "/cart/add";

    public static final String CART_CLEAR = "/cart/clear";

    public static final String CART_ITEM_UPDATE = "/cart-item/update";

    public static final String CART_ITEM_REMOVE = "/cart-item/{id}/remove";

    public static final String ORDER = "/order";

    public static final String ORDER_USER = "/order/user";

    public static final String ORDER_RESTAURANT = "/order/restaurant/{id}";

    public static final String ORDER_STATUS = "/order/{Id}/{orderStatus}";

}
