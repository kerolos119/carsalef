package com.example.carSaleShop.dto;

import com.example.carSaleShop.model.Transition;
import com.example.carSaleShop.model.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {
    private String id;
    private String Name;
    private String quantity;
    private String model;
    private Transition transition;
    private String color;
    private Float price;
    private Type type;
}
