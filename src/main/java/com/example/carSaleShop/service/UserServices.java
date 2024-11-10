package com.example.carSaleShop.service;

import com.example.carSaleShop.reposatory.UserReposatory;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServices {
    @Autowired
    UserReposatory reposatory;
    @PostConstruct
    public void init(){

    }

}
