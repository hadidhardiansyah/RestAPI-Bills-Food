package com.bills.food_ordering.dto;

import com.bills.food_ordering.constants.NumberConstant;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.util.List;

@Data
@Embeddable
public class RestaurantDto {

    private Long id;

    private String title;

    @Column(length = NumberConstant.ONE_THOUSAND_NUMBER)
    private List<String> images;

    private String description;

}
