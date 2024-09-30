package com.example.ecommerce.repositories;

import com.example.ecommerce.domain.models.Product;

import java.util.List;

public interface IProductRepository {
    Product save(Product product);

    Product findById(Long id);

    List<Product> findAll();

    void deleteById(Long id);

    List<Product> findByNameContainingIgnoreCase(String name);

    List<Product> findByPriceBetween(Double minPrice, Double maxPrice);

    boolean existsById(Long id);
}
