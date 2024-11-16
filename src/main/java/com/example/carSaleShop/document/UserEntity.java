package com.example.carSaleShop.document;

import com.example.carSaleShop.model.Auitable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("user")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity extends Auitable {
    @Id
    private String id;
    private String username;
    private String password;
    private String roles;
}
