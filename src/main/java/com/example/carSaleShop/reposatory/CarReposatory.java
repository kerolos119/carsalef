package com.example.carSaleShop.reposatory;

import com.example.carSaleShop.document.Car;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CarReposatory extends MongoRepository<Car,String> {
}
