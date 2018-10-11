package com.capgemini.shoppingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.shoppingapp.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
