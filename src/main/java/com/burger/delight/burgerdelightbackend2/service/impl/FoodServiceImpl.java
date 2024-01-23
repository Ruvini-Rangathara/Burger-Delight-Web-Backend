package com.burger.delight.burgerdelightbackend2.service.impl;
import com.burger.delight.burgerdelightbackend2.dto.FoodDTO;
import com.burger.delight.burgerdelightbackend2.model.Food;
import com.burger.delight.burgerdelightbackend2.repo.FoodRepo;
import com.burger.delight.burgerdelightbackend2.service.FoodService;
import com.burger.delight.burgerdelightbackend2.util.ResponseList;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Author: ruu
 * Created: 2024-01-23 11.28
 */

@Service
@Transactional
@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService{

    private final FoodRepo foodRepo;
    private final ModelMapper modelMapper;

    @Override
    public String addFood(FoodDTO food) {
          try {
              if(foodRepo.existsById(food.getId())) {
                  return ResponseList.RSP_DUPLICATE;
              }else {
                    foodRepo.save(modelMapper.map(food, Food.class));
                    return ResponseList.RSP_SUCCESS;
              }
          }catch (Exception e){
              return ResponseList.RSP_FAILURE;
          }
    }

    @Override
    public String updateFood(FoodDTO food) {
        try {
            if(!foodRepo.existsById(food.getId())) {
                return ResponseList.RSP_NOT_FOUND;
            }
            foodRepo.save(modelMapper.map(food, Food.class));
            return ResponseList.RSP_SUCCESS;
        }catch (Exception e) {
            return ResponseList.RSP_FAILURE;
        }
    }

    @Override
    public String deleteFood(int id) {
        try {
            if(!foodRepo.existsById(id)) {
                return ResponseList.RSP_NOT_FOUND;
            }
            foodRepo.deleteById(id);
            return ResponseList.RSP_SUCCESS;
        }catch (Exception e) {
            return ResponseList.RSP_FAILURE;
        }
    }

    @Override
    public FoodDTO getFood(int id) {
        try{
            if(!foodRepo.existsById(id)) {
                return null;
            }
            Food food = foodRepo.findById(id).get();
            return modelMapper.map(food, FoodDTO.class);
        }catch (Exception e) {
            return null;
        }
    }

    @Override
    public FoodDTO[] getAllFoods() {
        try {
            return modelMapper.map(foodRepo.findAll(), FoodDTO[].class);
        }catch (Exception e) {
            return null;
        }
    }

    @Override
    public int getNewFoodId() {
        try {
            return foodRepo.findAll().size() + 1;
        }catch (Exception e) {
            return 0;
        }
    }
}
