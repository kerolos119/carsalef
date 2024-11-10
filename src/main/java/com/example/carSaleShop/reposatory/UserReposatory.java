package com.example.carSaleShop.reposatory;

import com.example.carSaleShop.document.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserReposatory extends MongoRepository<UserEntity,String> {
    UserEntity findByUsername(String username);
}
