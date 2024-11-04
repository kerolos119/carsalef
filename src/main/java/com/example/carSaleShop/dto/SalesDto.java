package com.example.carSaleShop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesDto {
    private String id;
    private LocalDate date;
    private String carId;
}
