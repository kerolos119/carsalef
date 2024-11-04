package com.example.carSaleShop.mapper;

import com.example.carSaleShop.document.Car;
import com.example.carSaleShop.document.Sales;
import com.example.carSaleShop.dto.CarDto;
import com.example.carSaleShop.dto.SalesDto;
import org.springframework.stereotype.Component;

@Component
public class SalesMapper extends AbstractMapper<SalesDto, Sales> {
    public SalesMapper() {
        super(SalesDto.class, Sales.class);
    }



    @Override
    public Sales updateToEntity(SalesDto dto, Sales entity) {
        if (dto.getCarId() !=null&& dto.getCarId().isEmpty()){
            entity.setCarId(dto.getCarId());
        }
        return entity;
    }

    @Override
    public Car updaToEntity(CarDto dto, Car entity) {
        return null;
    }
}
