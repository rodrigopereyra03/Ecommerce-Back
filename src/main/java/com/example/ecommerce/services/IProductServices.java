package com.example.ecommerce.services;


import com.example.ecommerce.api.dto.ProductDto;

import java.util.List;

public interface IProductServices {

    ProductDto createProduct(ProductDto productDto);

    List<ProductDto> getAllProducts();

    ProductDto getProductById(Long id);

    ProductDto updateProduct(ProductDto productDto);

    String deleteProduct(Long id);

    List<ProductDto> searchProducts(String name, String category, Double minPrice, Double maxPrice);

    void updateProductStock(Long id, int newStock);
}
