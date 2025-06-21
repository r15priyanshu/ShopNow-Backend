package com.anshuit.shopnow.customerservice.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anshuit.shopnow.customerservice.entities.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
	Optional<Customer> findCustomerByEmail(String email);
	Optional<Customer> findCustomerByEmailAndPassword(String email,String password);
}
