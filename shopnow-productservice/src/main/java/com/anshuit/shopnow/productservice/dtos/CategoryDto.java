package com.anshuit.shopnow.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDto {
	public int categoryId;
	public String categoryName;
	public String categoryDescription;
}