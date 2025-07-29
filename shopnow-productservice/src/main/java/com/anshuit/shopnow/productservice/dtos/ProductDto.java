package com.anshuit.shopnow.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
	private int productId;
	private String productName;
	private String productDescription;
	private String productImage;
	private double productPrice;
	private CategoryDto category;
}
