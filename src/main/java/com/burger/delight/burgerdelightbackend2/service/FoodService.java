package com.burger.delight.burgerdelightbackend2.service;
import com.burger.delight.burgerdelightbackend2.dto.FoodDTO;

import java.util.List;

/**
 * Author: ruu
 * Created: 2024-01-23 11.27
 */

public interface FoodService {
    String addFood(FoodDTO food);
    String updateFood(FoodDTO food);
    String deleteFood(int id);
    FoodDTO getFood(int id);
    FoodDTO[] getAllFoods();
    int getNewFoodId();
    FoodDTO[] getFoodByCategory(String category);
}
