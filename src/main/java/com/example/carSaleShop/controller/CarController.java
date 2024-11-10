package com.example.carSaleShop.controller;

import com.example.carSaleShop.dto.CarDto;
import com.example.carSaleShop.dto.PageResult;
import com.example.carSaleShop.model.Auitable;
import com.example.carSaleShop.model.Transition;
import com.example.carSaleShop.model.Type;
import com.example.carSaleShop.service.CarServices;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/car")
@RequiredArgsConstructor
public class CarController extends Auitable {
    @Autowired
    CarServices services;

    @PostMapping
    public String create (@RequestBody CarDto dto){
        return services.create(dto);
    }
    @PutMapping("/{id}")
    public void update(@PathVariable String id,@RequestBody CarDto dto ){
        services.update(id,dto);
    }
    @GetMapping("/{id}")
    public CarDto getById(@PathVariable String id)
    {
        return services.getById(id);

    }
    @GetMapping("/search")
    public PageResult search(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String model,
            @RequestParam(required = false) Transition transition,
            @RequestParam(required = false) Type type,
            @RequestParam(required = false) Float price,
            @RequestHeader(required = false,defaultValue = "0") Integer page ,
            @RequestHeader(required = false,defaultValue = "10") Integer size
    )
    {
        Pageable pageable= PageRequest.of(page,size, Sort.by(Sort.Direction.DESC,"name"));

        return services.search(name,model,transition,type,price,pageable);

    }
}
