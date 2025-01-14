package com.bills.food_ordering.services.implementations;

import com.bills.food_ordering.entities.Category;
import com.bills.food_ordering.entities.Restaurant;
import com.bills.food_ordering.repositories.ICategoryRepository;
import com.bills.food_ordering.services.interfaces.ICategoryService;
import com.bills.food_ordering.services.interfaces.IRestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private IRestaurantService restaurantService;

    @Autowired
    private ICategoryRepository categoryRepository;

    @Override
    public Category createCategory(String name, Long userId) throws Exception {
        Restaurant restaurant = restaurantService.getRestaurantByUserId(userId);
        Category category = new Category();
        category.setName(name);
        category.setRestaurant(restaurant);

        return categoryRepository.save(category);
    }

    @Override
    public List<Category> findCategoryByRestaurantId(Long id) throws Exception {
        Restaurant restaurant = restaurantService.getRestaurantByUserId(id);
        return categoryRepository.findByRestaurantId(restaurant.getId());
    }

    @Override
    public Category findCategoryById(Long id) throws Exception {
        Optional<Category> category = categoryRepository.findById(id);

        if (category.isEmpty()) {
            throw new Exception("Category not found");
        }

        return category.get();
    }

}
