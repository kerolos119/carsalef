package com.example.carSaleShop.mapper;

import com.example.carSaleShop.document.Car;
import com.example.carSaleShop.dto.CarDto;
import org.springframework.stereotype.Component;

@Component
public  class CarMapper extends AbstractMapper<CarDto, Car> {

    public CarMapper() {
        super(CarDto.class,Car.class);
    }



    @Override
    public Car updateToEntity(CarDto dto, Car entity) {
        if (dto.getQuantity() !=null && dto.getQuantity().isEmpty()){
            entity.setQuantity(dto.getQuantity());
        }
        if (dto.getColor() != null && dto.getColor().isEmpty()){
            entity.setColor(dto.getColor());
        }
        return entity;
    }

    @Override
    public Car updaToEntity(CarDto dto, Car entity) {
        return null;
    }
}
