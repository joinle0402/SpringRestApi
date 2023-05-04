package com.johnsmith.SpringCrudRestApi.services.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.johnsmith.SpringCrudRestApi.dto.ProductRequest;
import com.johnsmith.SpringCrudRestApi.exceptions.ApiException;
import com.johnsmith.SpringCrudRestApi.models.Product;
import com.johnsmith.SpringCrudRestApi.repositories.ProductRepository;
import com.johnsmith.SpringCrudRestApi.services.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ModelMapper modelMapper;
    private final ProductRepository productRepository;

    @Override
    public Product create(ProductRequest productRequest) {
        Optional<Product> foundProductByName = this.productRepository.findByName(productRequest.getName());
        if (foundProductByName.isPresent()) {
            throw new ApiException(HttpStatus.CONFLICT, "Product name has already been!");
        }

        Product product = modelMapper.map(productRequest, Product.class);
        return this.productRepository.save(product);
    }

    @Override
    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    @Override
    public Product findById(Long id) {
        Optional<Product> optionalProduct = this.productRepository.findById(id);

        if (optionalProduct.isEmpty()) {
            throw new ApiException(HttpStatus.NOT_FOUND, "Product not found!");
        }

        return optionalProduct.get();
    }

    @Override
    public Product updateById(Long id, ProductRequest productRequest) {
        Optional<Product> foundProductById = this.productRepository.findById(id);
        if (foundProductById.isEmpty()) {
            throw new ApiException(HttpStatus.NOT_FOUND, "Product not found!");
        }

        Optional<Product> foundProductByName = this.productRepository.findByName(productRequest.getName());
        if (foundProductByName.isPresent()
                && !foundProductById.get().getName().equalsIgnoreCase(productRequest.getName())) {
            throw new ApiException(HttpStatus.CONFLICT, "Product name has already been!");
        }

        Product productToUpdate = foundProductById.get();
        productToUpdate.setName(productRequest.getName());
        productToUpdate.setPrice(productRequest.getPrice());
        return this.productRepository.save(productToUpdate);
    }

    @Override
    public void deleteById(Long id) {
        Optional<Product> optionalProduct = this.productRepository.findById(id);

        if (optionalProduct.isEmpty()) {
            throw new ApiException(HttpStatus.NOT_FOUND, "Product not found!");
        } else {
            Product productToDelete = optionalProduct.get();
            this.productRepository.delete(productToDelete);
        }
    }

}
