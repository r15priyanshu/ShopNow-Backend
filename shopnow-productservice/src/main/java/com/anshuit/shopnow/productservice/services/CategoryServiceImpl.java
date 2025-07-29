package com.anshuit.shopnow.productservice.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.anshuit.shopnow.productservice.entities.Category;
import com.anshuit.shopnow.productservice.exceptions.CustomException;
import com.anshuit.shopnow.productservice.repositories.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	private Category saveOrUpdateCategory(Category category) {
		return this.categoryRepository.save(category);
	}

	@Override
	public Category createCategory(Category category) {
		return saveOrUpdateCategory(category);
	}

	@Override
	public Optional<Category> getCategoryByIdOptional(int categoryId) {
		return categoryRepository.findById(categoryId);
	}

	@Override
	public Category getCategoryById(int categoryId) {
		Category foundCategory = getCategoryByIdOptional(categoryId).orElseThrow(
				() -> new CustomException("Category Not Found With Id : " + categoryId, HttpStatus.NOT_FOUND));
		return foundCategory;
	}

	@Override
	public List<Category> getAllCategories() {
		return categoryRepository.findAll();
	}

	@Override
	public Category deleteCategoryById(int categoryId) {
		Category foundCategory = getCategoryById(categoryId);
		categoryRepository.delete(foundCategory);
		return foundCategory;
	}

}
