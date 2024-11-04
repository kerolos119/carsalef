package com.example.carSaleShop.document;

import com.example.carSaleShop.model.Energy;
import com.example.carSaleShop.model.Transition;
import com.example.carSaleShop.model.Type;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("/car")
public class Model {
    @Id
    @NotEmpty
    private String id;
    @NotEmpty
    private String name;
    @NotEmpty
    private Type type;
    @NotEmpty
    private Energy energy;
    @NotEmpty
    private Transition transition;
}
