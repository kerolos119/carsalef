package com.example.carSaleShop.reposatory;

import com.example.carSaleShop.document.Sales;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SalesReposatory extends MongoRepository<Sales,String> {
}
