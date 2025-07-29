package com.anshuit.shopnow.productservice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.anshuit.shopnow.productservice.dtos.CategoryDto;
import com.anshuit.shopnow.productservice.entities.Category;
import com.anshuit.shopnow.productservice.services.CategoryService;
import com.anshuit.shopnow.productservice.services.DataTransferService;

@RestController
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private DataTransferService dataTransferService;

	@PostMapping("/categories")
	public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) {
		Category category = dataTransferService.mapCategoryDToToCategory(categoryDto);
		Category createdCategory = categoryService.createCategory(category);
		CategoryDto createdCategoryDto = dataTransferService.mapCategoryToCategoryDto(createdCategory);
		return new ResponseEntity<>(createdCategoryDto, HttpStatus.CREATED);
	}

	@GetMapping("/categories")
	public ResponseEntity<List<CategoryDto>> getAllCategories() {
		List<Category> allCategories = categoryService.getAllCategories();
		List<CategoryDto> allCategoriesDto = allCategories.stream()
				.map(category -> dataTransferService.mapCategoryToCategoryDto(category)).toList();
		return new ResponseEntity<>(allCategoriesDto, HttpStatus.OK);
	}

	@DeleteMapping("/categories/{categoryId}")
	public ResponseEntity<CategoryDto> deleteCategoryById(@PathVariable("categoryId") int categoryId) {
		Category deletedCategory = categoryService.deleteCategoryById(categoryId);
		CategoryDto deletedCategoryDto = dataTransferService.mapCategoryToCategoryDto(deletedCategory);
		return new ResponseEntity<>(deletedCategoryDto, HttpStatus.OK);
	}
}
