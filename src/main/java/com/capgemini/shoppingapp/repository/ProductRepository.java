package com.capgemini.shoppingapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.capgemini.shoppingapp.entities.Product;

public interface ProductRepository extends MongoRepository<Product, Integer>{

}
