package com.johnsmith.springrestapi.services;

import com.johnsmith.springrestapi.models.Product;

import java.util.List;


public interface ProductService {
	Product create(ProductRequest productRequest);
	List<Product> findAll();
	Product findById(Long id);
	Product updateById(Long id, ProductRequest productRequest);
	void deleteById(Long id);
}
