package com.example.carSaleShop.controller;


import com.example.carSaleShop.dto.PageResult;
import com.example.carSaleShop.dto.SalesDto;
import com.example.carSaleShop.model.Auitable;
import com.example.carSaleShop.service.SalesServices;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/sale")
@RequiredArgsConstructor
public class SalesController extends Auitable {
    @Autowired
    SalesServices services;
    @PostMapping
    public String create(@RequestBody SalesDto dto){
        return  services.save(dto);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id){
        services.delete(id);
    }
    @GetMapping
    public PageResult search(
            @RequestParam(required = false)LocalDateTime dateTime,
            @RequestParam(required = false)String CarId,
            @RequestHeader(required = false,defaultValue="0")Integer page,
            @RequestHeader(required = false,defaultValue = "10") Integer size
            ){
        Pageable pageable= PageRequest.of(page,size, Sort.by(Sort.Direction.DESC,"name"));
        return services.search(dateTime,CarId,pageable);
    }
}
