package com.bills.food_ordering.services.implementations;

import com.bills.food_ordering.dto.RestaurantDto;
import com.bills.food_ordering.entities.Address;
import com.bills.food_ordering.entities.Restaurant;
import com.bills.food_ordering.entities.User;
import com.bills.food_ordering.entities.requests.CreateRestaurantRequest;
import com.bills.food_ordering.repositories.IAddressRepository;
import com.bills.food_ordering.repositories.IRestaurantRepository;
import com.bills.food_ordering.repositories.IUserRepository;
import com.bills.food_ordering.services.interfaces.IRestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService implements IRestaurantService {

    @Autowired
    private IRestaurantRepository restaurantRepository;

    @Autowired
    private IAddressRepository addressRepository;

    @Autowired
    private IUserRepository userRepository;

    @Override
    public Restaurant createRestaurant(CreateRestaurantRequest createRestaurantRequest, User user) {
        Address address = addressRepository.save(createRestaurantRequest.getAddress());

        Restaurant restaurant = new Restaurant();
        restaurant.setAddress(address);
        restaurant.setContactInformation(createRestaurantRequest.getContactInformation());
        restaurant.setCuisineType(createRestaurantRequest.getCuisineType());
        restaurant.setDescription(createRestaurantRequest.getDescription());
        restaurant.setImages(createRestaurantRequest.getImages());
        restaurant.setName(createRestaurantRequest.getName());
        restaurant.setOpeningHours(createRestaurantRequest.getOpeningHours());
        restaurant.setRegistrationDate(LocalDateTime.now());
        restaurant.setOwner(user);

        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updatedRestaurant) throws Exception {
        Restaurant restaurant = findRestaurantById(restaurantId);

        if (restaurant.getCuisineType() != null) {
            restaurant.setCuisineType(updatedRestaurant.getCuisineType());
        }
        if (restaurant.getDescription() != null) {
            restaurant.setDescription(updatedRestaurant.getDescription());
        }
        if (restaurant.getName() != null) {
            restaurant.setName(updatedRestaurant.getName());
        }

        return restaurantRepository.save(restaurant);
    }

    @Override
    public void deleteRestaurant(Long restaurantId) throws Exception {
        Restaurant restaurant = findRestaurantById(restaurantId);

        restaurantRepository.delete(restaurant);
    }

    @Override
    public List<Restaurant> getAllRestaurant() {
        return restaurantRepository.findAll();
    }

    @Override
    public List<Restaurant> searchRestaurant(String keyword) {
        return restaurantRepository.findBySearchQuery(keyword);
    }

    @Override
    public Restaurant findRestaurantById(Long id) throws Exception {
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);

        if (restaurant.isEmpty()) {
            throw new Exception("Restaurant not found with id " + id);
        }

        return restaurant.get();
    }

    @Override
    public Restaurant getRestaurantByUserId(Long userId) throws Exception {
        Restaurant restaurant = restaurantRepository.findByOwnerId(userId);

        if (restaurant == null) {
            throw new Exception("Restaurant not found with owner ID " + userId);
        }
        return restaurant;
    }

    @Override
    public RestaurantDto addToFavorites(Long restaurantId, User user) throws Exception {
        Restaurant restaurant = findRestaurantById(restaurantId);

        RestaurantDto restaurantDto = new RestaurantDto();
        restaurantDto.setDescription(restaurant.getDescription());
        restaurantDto.setImages(restaurant.getImages());
        restaurantDto.setTitle(restaurant.getName());
        restaurantDto.setId(restaurantId);

        if (user.getFavorites().contains(restaurantDto)) {
            user.getFavorites().remove(restaurantDto);
        } else {
            user.getFavorites().add(restaurantDto);
        }

        userRepository.save(user);

        return restaurantDto;
    }

    @Override
    public Restaurant updateRestaurantStatus(Long id) throws Exception {
        Restaurant restaurant = findRestaurantById(id);
        restaurant.setOpen(!restaurant.isOpen());

        return restaurantRepository.save(restaurant);
    }
}
