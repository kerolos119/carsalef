package com.example.carSaleShop.dto;

import com.example.carSaleShop.model.Auitable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto extends Auitable {
    private String id;
    private String username;
    private String password;
    private String roles;
}
