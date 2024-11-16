package com.example.carSaleShop.controller;

import com.example.carSaleShop.dto.PageResult;
import com.example.carSaleShop.dto.UserDto;
import com.example.carSaleShop.mapper.UserMapper;
import com.example.carSaleShop.service.UserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    UserServices services;
    @PostMapping
    public  String create ( @RequestBody UserDto dto){
        return services.create(dto);
    }
    @DeleteMapping("/id{}")
    public void delete(@PathVariable String id){
        services.delete(id);
    }
    @PutMapping("/{id}")
    public void update(@PathVariable String id, @RequestBody UserDto dto){
        services.update(id,dto);
    }
    @GetMapping("/{id}")
    public UserDto getById(@PathVariable String id){
        return services.getById(id);
    }
    @GetMapping("/search")
    public PageResult search(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String roles,
            @RequestHeader(required = false,defaultValue = "0") Integer page ,
            @RequestHeader(required = false,defaultValue = "10") Integer size){
        Pageable pageable= PageRequest.of(page,size, Sort.by(Sort.Direction.DESC,"username"));
        return services.search(pageable,roles,username);

    }
}
