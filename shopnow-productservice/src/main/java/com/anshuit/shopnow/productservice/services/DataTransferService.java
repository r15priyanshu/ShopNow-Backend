package com.anshuit.shopnow.productservice.services;

import com.anshuit.shopnow.productservice.dtos.CategoryDto;
import com.anshuit.shopnow.productservice.dtos.ProductDto;
import com.anshuit.shopnow.productservice.entities.Category;
import com.anshuit.shopnow.productservice.entities.Product;

public interface DataTransferService {

	ProductDto mapProductToProductDto(Product product);

	Product mapProductDToToProduct(ProductDto productDto);

	CategoryDto mapCategoryToCategoryDto(Category category);

	Category mapCategoryDToToCategory(CategoryDto categoryDto);

}