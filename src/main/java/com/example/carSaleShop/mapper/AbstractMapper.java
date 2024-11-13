package com.example.carSaleShop.mapper;

import com.example.carSaleShop.document.Car;
import com.example.carSaleShop.dto.CarDto;
import jakarta.annotation.PostConstruct;
import lombok.Setter;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractMapper<D,E> implements Mapper<D,E> {

    @Autowired
    @Setter
    ModelMapper mapper;
    private Class<D> dClass;
    private Class<E> eClass;
    public AbstractMapper(Class<D> dClass,Class<E> eClass){
        this.dClass=dClass;
        this.eClass=eClass;
    }
    @PostConstruct
    public  void  init(){
        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STANDARD)
                .setAmbiguityIgnored(true)
                .setPropertyCondition(Conditions.isNotNull());
    }
    @Override
    public E toEntityy(D dto){
        return mapper.map(dto ,eClass);

    }
    @Override
    public D toDto(E entity) {
        return mapper.map(entity,dClass);
    }

    @Override
    public E toEntity(D dto) {
        return mapper.map(dto,eClass);
    }

    public abstract Car updaToEntity(CarDto dto, Car entity);
}
