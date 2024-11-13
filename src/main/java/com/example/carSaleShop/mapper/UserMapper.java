package com.example.carSaleShop.mapper;
import com.example.carSaleShop.document.Car;
import com.example.carSaleShop.document.UserEntity;
import com.example.carSaleShop.dto.CarDto;
import com.example.carSaleShop.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper extends AbstractMapper<UserDto, UserEntity> {
    public UserMapper() {
        super(UserDto.class,UserEntity.class);
    }

    @Override
    public UserEntity updateToEntity(UserDto dto, UserEntity entity) {
        if (dto.getPassword() != null && !dto.getPassword().isEmpty())
            entity.setPassword(dto.getPassword());
        if (dto.getRoles() != null && !dto.getRoles().isEmpty())
            entity.setRoles(dto.getRoles());
        return entity;
    }


    @Override
    public Car updaToEntity(CarDto dto, Car entity) {
        return null;
    }
}
