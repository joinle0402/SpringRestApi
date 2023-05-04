package com.johnsmith.SpringCrudRestApi.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.johnsmith.SpringCrudRestApi.dto.ApiResponse;
import com.johnsmith.SpringCrudRestApi.dto.ProductRequest;
import com.johnsmith.SpringCrudRestApi.models.Product;
import com.johnsmith.SpringCrudRestApi.services.ProductService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {
	private final ProductService productService;
	
	@PostMapping
	public ResponseEntity<ApiResponse> create(@RequestBody @Valid ProductRequest productRequest) {
		Product created = this.productService.create(productRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(
			new ApiResponse(HttpStatus.CREATED, "Created new product!", created)
		);
	}
	
	@GetMapping
	public ResponseEntity<ApiResponse> findAll() {
		List<Product> products = this.productService.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(
			new ApiResponse(HttpStatus.OK, "Find all products!", products)
		);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse> findById(@PathVariable Long id) {
		Product product = this.productService.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(
			new ApiResponse(HttpStatus.OK, "Find product by id!", product)
		);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse> updateById(@PathVariable Long id, @RequestBody @Valid ProductRequest productRequest) {
		Product updated = this.productService.updateById(id, productRequest);
		return ResponseEntity.status(HttpStatus.OK).body(
			new ApiResponse(HttpStatus.OK, "Update product by id!", updated)
		);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteById(@PathVariable Long id) {
		this.productService.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body(
			new ApiResponse(HttpStatus.OK, "Delete product by id!", "")
		);
	}
	
}
