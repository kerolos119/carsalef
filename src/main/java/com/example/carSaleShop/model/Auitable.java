package com.example.carSaleShop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Auitable {
    private String createdAt;
    private LocalDateTime modifiedAt;
    private String createdBy;
    private LocalDateTime modifiedBy;
}
