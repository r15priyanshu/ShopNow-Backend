package com.anshuit.shopnow.productservice.services;

import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.anshuit.shopnow.productservice.entities.Product;

public interface ProductService {
	Product createProduct(Product product, int categoryId, MultipartFile file);

	Product updateProduct(Product product);

	Optional<Product> getProductByIdOptional(int productId);

	Product getProductById(int productId);

	List<Product> getAllProducts();

	Product deleteProductById(int productId);

	List<Product> getAllProductsByCategoryId(int categoryId);
}
