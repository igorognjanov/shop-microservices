package com.microservices.product.service;

import com.microservices.product.dto.ProductRequest;
import com.microservices.product.dto.ProductResponse;
import com.microservices.product.model.Product;
import com.microservices.product.repository.ProductRepository;
import java.util.List;
import java.util.stream.*;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponse createProduct(ProductRequest request) {
        Product product = new Product(request.name(), request.description(), request.price());
        //log.info("Product created succesfully");
        return mapProductToResponse(productRepository.save(product));
    }

    public List<ProductResponse> getAllProducts() {
        return this.productRepository.findAll()
            .stream().map(this::mapProductToResponse).collect(Collectors.toList());
    }

    private ProductResponse mapProductToResponse(Product product) {
        return new ProductResponse(product.getId(), product.getName(), product.getDescription(), product.getPrice());
    }
}
