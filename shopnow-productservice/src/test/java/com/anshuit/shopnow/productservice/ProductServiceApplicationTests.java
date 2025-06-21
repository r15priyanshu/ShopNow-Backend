package com.anshuit.shopnow.productservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.anshuit.shopnow.productservice.services.FileService;

@SpringBootTest
class ProductServiceApplicationTests {

	@Autowired
	FileService fileService;
	
	@Test
	void test() {
		
	}

}
