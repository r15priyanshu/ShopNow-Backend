package com.anshuit.shopnow.productservice.services;

import java.util.List;
import java.util.Optional;

import com.anshuit.shopnow.productservice.entities.Category;

public interface CategoryService {

	Category createCategory(Category category);

	Optional<Category> getCategoryByIdOptional(int categoryId);

	Category getCategoryById(int categoryId);

	List<Category> getAllCategories();

	Category deleteCategoryById(int categoryId);
}
