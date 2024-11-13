package com.example.carSaleShop.service;

import com.example.carSaleShop.document.UserEntity;
import com.example.carSaleShop.dto.UserDto;
import com.example.carSaleShop.mapper.UserMapper;
import com.example.carSaleShop.reposatory.UserReposatory;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServices {
    @Autowired
    UserReposatory reposatory;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserMapper mapper;
    @PostConstruct
    public void init(){
        if (reposatory.count()==0){
            UserDto user= UserDto.builder().username("admin").password("aadmin").roles("ADMIN").build();
            create( user);
        }
    }
    public  String create (UserDto dto){
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        return reposatory.save(mapper.toEntity(dto)).getId();
    }
}
