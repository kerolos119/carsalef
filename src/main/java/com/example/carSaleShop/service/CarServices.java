package com.example.carSaleShop.service;

import com.example.carSaleShop.document.Car;
import com.example.carSaleShop.dto.CarDto;
import com.example.carSaleShop.dto.PageResult;
import com.example.carSaleShop.excception.CustomException;
import com.example.carSaleShop.mapper.CarMapper;
import com.example.carSaleShop.model.Auitable;
import com.example.carSaleShop.model.Transition;
import com.example.carSaleShop.model.Type;
import com.example.carSaleShop.reposatory.CarReposatory;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Builder
public class CarServices extends Auitable {
    @Autowired
    CarReposatory reposatory;
    @Autowired
    MongoTemplate template;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    CarMapper mapper;

    public String create(CarDto dto) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(dto.getId()));
        if (template.exists(query, Car.class))
            throw new CustomException("car is order exist", HttpStatus.CREATED);
//        dto.setCreatedAt();
        return template.save(mapper.toEntity(dto)).getId();
    }

    public void update(String id, CarDto dto) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").regex(dto.getName()));
        query.addCriteria(Criteria.where("_id").is(dto.getId()));
        if (template.exists(query, Car.class)) {
            throw new RuntimeException("car is alreeady exist");
        }
        query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        Car car = template.findOne(query, Car.class);
        template.save(mapper.updateToEntity(dto, car));

    }

    public CarDto getById(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        return mapper.toDto(template.findOne(query, Car.class));
    }

    public PageResult search(String name,
                             String model,
                             Transition transition,
                             Type type,
                             Float price,
                             Pageable pageable) {
        Query query = new Query();
        if (name != null)
            query.addCriteria(Criteria.where("name").regex("1"));
        if (model != null)
            query.addCriteria(Criteria.where("model").regex("1"));
        if (transition != null)
            query.addCriteria(Criteria.where("transitionn").is("1"));
        if (type != null)
            query.addCriteria(Criteria.where("type").is("1"));
        if (price != null)
            query.addCriteria(Criteria.where("price").in("1"));
        List<CarDto> dtos = template.find(query.with(pageable), Car.class).stream().map(car -> {
            return mapper.toDto(car);
        }).collect(Collectors.toList());
        Long count = template.count(query, Car.class);
        List<CarDto> dtoList = template.find(query, Car.class).stream().map(car -> {
            return mapper.toDto(car);
        }).collect(Collectors.toList());
       return PageResult.builder().item(dtoList).count(count).build();

    }
}


