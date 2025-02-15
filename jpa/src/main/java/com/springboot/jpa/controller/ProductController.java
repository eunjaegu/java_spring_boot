package com.springboot.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.jpa.data.dto.ChangeProductNameDto;
import com.springboot.jpa.data.dto.ProductDto;
import com.springboot.jpa.data.dto.ProductResponseDto;
import com.springboot.jpa.data.service.ProductService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/product")
public class ProductController {

	private final ProductService productService;
	
	@Autowired
	public ProductController(ProductService productService) {
		this.productService=productService;
	}
	
	@GetMapping()
	public ResponseEntity<ProductResponseDto> getproduct(Long number){
		ProductResponseDto productResponseDto = productService.getProduct(number);
		
		return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
	}
	
	@PostMapping()
	public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductDto productDto){
		ProductResponseDto productResponseDto=productService.saveProduct(productDto);
		
		return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
	}
	
	 @PutMapping()
	    public ResponseEntity<ProductResponseDto> changeProductName(
	        @RequestBody ChangeProductNameDto changeProductNameDto) throws Exception {
	        ProductResponseDto productResponseDto = productService.changeProductName(
	                changeProductNameDto.getNumber(),
	                changeProductNameDto.getName());
	        
	        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
	    }

	    @DeleteMapping()
	    public ResponseEntity<String> deleteProduct(Long number) throws Exception {
	        productService.deleteProduct(number);
	       
	        return ResponseEntity.status(HttpStatus.OK).body("정상적으로 삭제되었습니다.");
	    }
}
