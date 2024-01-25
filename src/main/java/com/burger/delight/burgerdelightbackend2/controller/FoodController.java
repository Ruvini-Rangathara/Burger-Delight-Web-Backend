package com.burger.delight.burgerdelightbackend2.controller;

import com.burger.delight.burgerdelightbackend2.dto.FoodDTO;
import com.burger.delight.burgerdelightbackend2.dto.ResponseDTO;
import com.burger.delight.burgerdelightbackend2.service.FoodService;
import com.burger.delight.burgerdelightbackend2.util.ResponseList;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * Author: ruu
 * Created: 2024-01-23 12.40
 */

@CrossOrigin(origins = "http://localhost:63342")
@RestController
@RequestMapping(value = "/api/v1/food")
@RequiredArgsConstructor
public class FoodController {

    private final FoodService foodService;

    @Autowired
    private ResponseDTO responseDTO;

    @PostMapping("/save")
    public ResponseEntity<ResponseDTO> saveFood(@RequestBody FoodDTO foodDTO) {
        String result = foodService.addFood(foodDTO);

        if (result.equals("00")) {
            responseDTO.setCode(ResponseList.RSP_SUCCESS);
            responseDTO.setMessage("Food saved successfully");
            responseDTO.setContent(foodDTO);
            System.out.println("Food saved successfully");
            return new ResponseEntity<>(responseDTO, org.springframework.http.HttpStatus.OK);

        } else if (result.equals("03")) {
            responseDTO.setCode(ResponseList.RSP_DUPLICATE);
            responseDTO.setMessage("Food already exists");
            responseDTO.setContent(foodDTO);
            return new ResponseEntity<>(responseDTO, org.springframework.http.HttpStatus.BAD_REQUEST);

        } else {
            responseDTO.setCode(ResponseList.RSP_FAILURE);
            responseDTO.setMessage("Failed to save food");
            responseDTO.setContent(foodDTO);
            return new ResponseEntity<>(responseDTO, org.springframework.http.HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDTO> updateFood(@RequestBody FoodDTO foodDTO) {
        String result = foodService.updateFood(foodDTO);

        if (result.equals("00")) {
            responseDTO.setCode(ResponseList.RSP_SUCCESS);
            responseDTO.setMessage("Food updated successfully");
            responseDTO.setContent(foodDTO);
            return new ResponseEntity<>(responseDTO, org.springframework.http.HttpStatus.OK);

        } else if (result.equals("03")) {
            responseDTO.setCode(ResponseList.RSP_DUPLICATE);
            responseDTO.setMessage("Food already exists");
            responseDTO.setContent(foodDTO);
            return new ResponseEntity<>(responseDTO, org.springframework.http.HttpStatus.BAD_REQUEST);

        } else {
            responseDTO.setCode(ResponseList.RSP_FAILURE);
            responseDTO.setMessage("Failed to update food");
            responseDTO.setContent(foodDTO);
            return new ResponseEntity<>(responseDTO, org.springframework.http.HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO> deleteFood(@PathVariable int id) {
        String result = foodService.deleteFood(id);

        if (result.equals("00")) {
            responseDTO.setCode(ResponseList.RSP_SUCCESS);
            responseDTO.setMessage("Food deleted successfully");
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, org.springframework.http.HttpStatus.OK);

        } else if (result.equals("04")) {
            responseDTO.setCode(ResponseList.RSP_NOT_FOUND);
            responseDTO.setMessage("Food not found");
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, org.springframework.http.HttpStatus.BAD_REQUEST);

        } else {
            responseDTO.setCode(ResponseList.RSP_FAILURE);
            responseDTO.setMessage("Failed to delete food");
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, org.springframework.http.HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ResponseDTO> getFood(@PathVariable int id) {
        FoodDTO foodDTO = foodService.getFood(id);

        if (foodDTO != null) {
            responseDTO.setCode(ResponseList.RSP_SUCCESS);
            responseDTO.setMessage("Food retrieved successfully");
            responseDTO.setContent(foodDTO);
            return new ResponseEntity<>(responseDTO, org.springframework.http.HttpStatus.OK);

        } else {
            responseDTO.setCode(ResponseList.RSP_NOT_FOUND);
            responseDTO.setMessage("Food not found");
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, org.springframework.http.HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<ResponseDTO> getAllFood() {
        FoodDTO[] foodDTOS = foodService.getAllFoods();

        if (foodDTOS.length != 0) {
            responseDTO.setCode(ResponseList.RSP_SUCCESS);
            responseDTO.setMessage("Foods retrieved successfully");
            responseDTO.setContent(foodDTOS);
            return new ResponseEntity<>(responseDTO, org.springframework.http.HttpStatus.OK);

        } else {
            responseDTO.setCode(ResponseList.RSP_NOT_FOUND);
            responseDTO.setMessage("Food not found");
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, org.springframework.http.HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getByCategory/{category}")
    public ResponseEntity<ResponseDTO> getFoodByCategory(@PathVariable String category) {
        responseDTO.setCode(ResponseList.RSP_SUCCESS);
        responseDTO.setMessage("Food retrieved successfully");
        FoodDTO[] foodByCategory = foodService.getFoodByCategory(category);

        responseDTO.setContent(foodByCategory);
        return new ResponseEntity<>(responseDTO, org.springframework.http.HttpStatus.OK);
    }

    @GetMapping("/getNewId")
    public ResponseEntity<ResponseDTO> getNewId() {
        int id = foodService.getNewFoodId();
        responseDTO.setCode(ResponseList.RSP_SUCCESS);
        responseDTO.setMessage("New id retrieved successfully");
        responseDTO.setContent(id);
        return new ResponseEntity<>(responseDTO, org.springframework.http.HttpStatus.OK);
    }


}
