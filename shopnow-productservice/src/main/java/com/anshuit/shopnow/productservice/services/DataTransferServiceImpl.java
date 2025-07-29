package com.anshuit.shopnow.productservice.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anshuit.shopnow.productservice.dtos.CategoryDto;
import com.anshuit.shopnow.productservice.dtos.ProductDto;
import com.anshuit.shopnow.productservice.entities.Category;
import com.anshuit.shopnow.productservice.entities.Product;

@Service
public class DataTransferServiceImpl implements DataTransferService {

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ProductDto mapProductToProductDto(Product product) {
		return modelMapper.map(product, ProductDto.class);
	}

	@Override
	public Product mapProductDToToProduct(ProductDto productDto) {
		return modelMapper.map(productDto, Product.class);
	}

	@Override
	public CategoryDto mapCategoryToCategoryDto(Category category) {
		return modelMapper.map(category, CategoryDto.class);
	}

	@Override
	public Category mapCategoryDToToCategory(CategoryDto categoryDto) {
		return modelMapper.map(categoryDto, Category.class);
	}
}
