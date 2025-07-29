package com.anshuit.shopnow.productservice.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.anshuit.shopnow.productservice.entities.Category;
import com.anshuit.shopnow.productservice.entities.Product;
import com.anshuit.shopnow.productservice.exceptions.CustomException;
import com.anshuit.shopnow.productservice.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Value("${shopnow.product-service.images.product-images.path}")
	private String productImagesFolderPath;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private FileService fileService;

	@Override
	public Product createProduct(Product product, int categoryId, MultipartFile file) {

		Category category = categoryService.getCategoryById(categoryId);
		product.setCategory(category);

		String fileNameWithTimestamp = null;
		try {
			fileNameWithTimestamp = fileService.uploadImage(productImagesFolderPath, file);
		} catch (IOException e) {
			throw new CustomException("Error Occurred While Uploading Image !!", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		product.setProductImage(fileNameWithTimestamp);
		Product createdProduct = productRepository.save(product);
		return createdProduct;
	}

	@Override
	public Product updateProduct(Product product) {
		return null;
	}

	@Override
	public Optional<Product> getProductByIdOptional(int productId) {
		return productRepository.findById(productId);
	}

	@Override
	public Product getProductById(int productId) {
		Product foundProduct = getProductByIdOptional(productId)
				.orElseThrow(() -> new CustomException("Product Not Found With Id : " + productId, HttpStatus.NOT_FOUND));
		return foundProduct;
	}

	@Override
	public List<Product> getAllProducts() {
		List<Product> allProducts = productRepository.findAll();
		return allProducts;
	}

	@Override
	public Product deleteProductById(int productId) {
		Product foundProduct = getProductById(productId);
		boolean imageDeleted = fileService.deleteImage(productImagesFolderPath, foundProduct.getProductImage());
		if (imageDeleted) {
			productRepository.delete(foundProduct);
		} else {
			throw new CustomException("Error While Deleting Image,[ Might Be Open/In-Use Else Where ]",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return foundProduct;
	}

	@Override
	public List<Product> getAllProductsByCategoryId(int categoryId) {
		if (categoryId == 0)
			return getAllProducts();

		Category category = categoryService.getCategoryById(categoryId);
		List<Product> products = productRepository.findProductByCategory(category);
		return products;
	}
}
