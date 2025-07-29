package com.anshuit.shopnow.productservice.controllers;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

//import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.anshuit.shopnow.productservice.dtos.ProductDto;
import com.anshuit.shopnow.productservice.entities.Product;
import com.anshuit.shopnow.productservice.exceptions.CustomException;
import com.anshuit.shopnow.productservice.services.DataTransferService;
import com.anshuit.shopnow.productservice.services.FileService;
import com.anshuit.shopnow.productservice.services.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class ProductController {

	@Value("${shopnow.product-service.images.product-images.path}")
	private String productImagesFolderPath;

	@Autowired
	private ProductService productService;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private FileService fileService;

	@Autowired
	private DataTransferService dataTransferService;

	@PostMapping("/products/category/{categoryId}")
	public ResponseEntity<ProductDto> createProductWithImage(@RequestParam("product") String product,
			@PathVariable("categoryId") int categoryId, @RequestParam("productImage") MultipartFile file) {

		Product newProduct = null;
		try {
			// CONVERTING STRING FORM OF JSON INTO ENTITY CLASS
			newProduct = objectMapper.readValue(product, Product.class);
		} catch (JsonProcessingException e) {
			throw new CustomException("Error While Parsing JSON String To Product.class Entity",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		Product createdProduct = productService.createProduct(newProduct, categoryId, file);
		ProductDto createdProductDto = dataTransferService.mapProductToProductDto(createdProduct);

		return new ResponseEntity<>(createdProductDto, HttpStatus.CREATED);
	}

	// SERVE PRODUCT IMAGE
	@GetMapping(value = "/images/serveImage/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
	public void serveImage(@PathVariable("imageName") String imageName, HttpServletResponse response) {
		try {
			InputStream is = fileService.serveImage(productImagesFolderPath, imageName);
			response.setContentType(MediaType.IMAGE_JPEG_VALUE);
			StreamUtils.copy(is, response.getOutputStream());
		} catch (FileNotFoundException e) {
			throw new CustomException("File Not Found With The Name : " + imageName, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@GetMapping("/products/category/{categoryId}")
	public ResponseEntity<List<ProductDto>> getAllProductsByCategoryId(@PathVariable("categoryId") int categoryId) {
		List<Product> products = productService.getAllProductsByCategoryId(categoryId);
		List<ProductDto> productsDto = products.stream()
				.map(product -> dataTransferService.mapProductToProductDto(product)).toList();
		return new ResponseEntity<>(productsDto, HttpStatus.OK);
	}

	@GetMapping("/products")
	public ResponseEntity<List<ProductDto>> getAllProducts() {
		List<Product> allProducts = productService.getAllProducts();
		List<ProductDto> allProductsDto = allProducts.stream()
				.map(product -> dataTransferService.mapProductToProductDto(product)).toList();
		return new ResponseEntity<>(allProductsDto, HttpStatus.OK);
	}

	@GetMapping("/products/{productId}")
	public ResponseEntity<ProductDto> getProductById(@PathVariable("productId") int productId) {
		Product foundProduct = productService.getProductById(productId);
		ProductDto foundProductDto = dataTransferService.mapProductToProductDto(foundProduct);
		return new ResponseEntity<>(foundProductDto, HttpStatus.OK);
	}

	@DeleteMapping("/products/{productId}")
	public ResponseEntity<ProductDto> deleteProductById(@PathVariable("productId") int productId) {
		Product deletedProduct = productService.deleteProductById(productId);
		ProductDto deletedProductDto = dataTransferService.mapProductToProductDto(deletedProduct);
		return new ResponseEntity<>(deletedProductDto, HttpStatus.OK);
	}
}
