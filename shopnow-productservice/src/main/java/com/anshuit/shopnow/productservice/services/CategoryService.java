package com.anshuit.shopnow.productservice.services;

import java.util.List;

import com.anshuit.shopnow.productservice.entities.Category;

public interface CategoryService {

	List<Category> getAllCategories();
	Category addCategory(Category category);
	Category deleteCategoryById(Integer categoryid);
}
