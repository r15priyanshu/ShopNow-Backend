package com.anshuit.shopnow.productservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anshuit.shopnow.productservice.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer>{

}
