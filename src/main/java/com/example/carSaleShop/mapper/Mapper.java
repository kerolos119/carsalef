package com.example.carSaleShop.mapper;

import com.example.carSaleShop.document.Car;
import com.example.carSaleShop.dto.CarDto;

public interface Mapper <D,E>{
    E toEntityy(D dto);

    public D toDto(E entity);
    public E toEntity(D dto);



    public E updateToEntity(D dto, E entity);

    Car updaToEntity(CarDto dto, Car entity);
}
