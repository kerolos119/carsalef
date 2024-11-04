package com.example.carSaleShop.document;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("/sales")
public class Sales {
    @Id
    @NotEmpty
    private String id;
    @NotEmpty
    private LocalDateTime date;
    @NotEmpty
    private String carId;
}
