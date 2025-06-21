package com.anshuit.shopnow.productservice.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.anshuit.shopnow.productservice.entities.Product;

public interface ProductService {
	Product addProduct(Product product,Integer categoryid,MultipartFile file);

	Product updateProduct(Product product);

	Product getProductById(Integer id);

	List<Product> getAllProducts();

	Product deleteProductById(Integer id);
	
	List<Product> getAllProductsByCategoryId(Integer categoryid);
}
