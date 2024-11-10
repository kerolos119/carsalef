package com.example.carSaleShop.service;

import com.example.carSaleShop.document.UserEntity;
import com.example.carSaleShop.reposatory.UserReposatory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUsertDetailServices implements UserDetailsService {
    @Autowired
    UserReposatory reposatory;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       UserEntity user= reposatory.findByUsername(username);
       return User.withUsername(user.getUsername()).password(user.getPassword()).roles(user.getRoles()).build();
    }


}
