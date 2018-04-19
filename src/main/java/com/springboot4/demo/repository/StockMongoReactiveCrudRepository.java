package com.springboot4.demo.repository;

import com.springboot4.demo.model.Stock;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface StockMongoReactiveCrudRepository extends ReactiveCrudRepository<Stock, String> {

}