package com.anshuit.shopnow.productservice.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anshuit.shopnow.productservice.entities.Category;
import com.anshuit.shopnow.productservice.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	List<Product> findProductByCategory(Category category);
}
