package com.example.carSaleShop.service;

import com.example.carSaleShop.document.Sales;
import com.example.carSaleShop.dto.PageResult;
import com.example.carSaleShop.dto.SalesDto;
import com.example.carSaleShop.excception.CustomException;
import com.example.carSaleShop.mapper.SalesMapper;
import com.example.carSaleShop.model.Auitable;
import com.example.carSaleShop.reposatory.CarReposatory;
import com.example.carSaleShop.reposatory.SalesReposatory;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Builder
public class SalesServices extends Auitable {
    @Autowired
    SalesReposatory reposatory;
    @Autowired
    MongoTemplate template;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    SalesMapper mapper;

    public String save(SalesDto dto) {
        Query query =new Query();
        query.addCriteria(Criteria.where("_id").is(dto.getId()));
        if (template.exists(query, Sales.class))
            throw new CustomException("this  order is done", HttpStatus.CREATED);
        return template.save(mapper.toEntityy(dto).getId());
    }


    public void delete(String id) {
        Query query =new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        if (!template.exists(query,Sales.class))
            throw new CustomException("this order not  done",HttpStatus.NOT_FOUND);
        template.remove(query,Sales.class);
    }

    public PageResult search(LocalDateTime dateTime, String carId, Pageable pageable) {
        Query query = new Query();
        if (dateTime != null){
            LocalDateTime from = LocalDateTime.of(LocalDate.from(dateTime), LocalTime.MAX);
            LocalDateTime to = LocalDateTime.of(LocalDate.from(dateTime), LocalTime.MIN);
            query.addCriteria(Criteria.where("dateTime").is("1"));
        }
        if (carId != null)
            query.addCriteria(Criteria.where("carId").is(carId));
        List<SalesDto> dtos = template.find(query.with(pageable),Sales.class).stream().map(sales->{
            return mapper.toDto(sales);
        }).collect(Collectors.toList());
        Long count = template.count(query ,Sales.class);
        List<SalesDto> dtoList = template.find(query ,Sales.class).stream().map(sales -> {
            return mapper.toDto(sales);
        }).collect(Collectors.toList());
        return PageResult.builder().item(dtoList).count(count).build();
    }
}
