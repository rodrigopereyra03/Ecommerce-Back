package com.example.ecommerce.services.impl;

import com.example.ecommerce.api.dto.ProductDto;
import com.example.ecommerce.api.mappers.ProductMapper;
import com.example.ecommerce.domain.exceptions.ProductNotFoundException;
import com.example.ecommerce.domain.models.Product;
import com.example.ecommerce.repositories.IProductRepository;
import com.example.ecommerce.services.IProductServices;

import java.util.List;
import java.util.Optional;

public class ProductServiceImpl implements IProductServices {

    private final IProductRepository repository;

    public ProductServiceImpl(IProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        Product product = ProductMapper.dtoToProduct(productDto);
        return ProductMapper.productToDto(repository.save(product));
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = repository.findAll();
        return products.stream()
                .map(ProductMapper::productToDto)
                .toList();
    }

    @Override
    public ProductDto getProductById(Long id) {
        return ProductMapper.productToDto(repository.findById(id));
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto) {
        Optional<Product> product = Optional.ofNullable(repository.findById(productDto.getId()));

        if(product.isPresent()){
            Product entity = product.get();
            if(productDto.getName()!=null){
                entity.setName(productDto.getName());
            }
            if(productDto.getDescription()!=null){
                entity.setDescription(productDto.getDescription());
            }
            if(productDto.getImages()!=null){
                entity.setImages(productDto.getImages());
            }
            if(productDto.getPrice()>=0){
                entity.setPrice(productDto.getPrice());
            }
            Product saved = repository.save(entity);
            return ProductMapper.productToDto(saved);
        }else{
            throw new ProductNotFoundException("Product not found with id");
        }
        
    }

    @Override
    public String deleteProduct(Long id) {
        if(repository.existsById(id)){
            repository.deleteById(id);
            return "The product has been successfully deleted";
        }else {
            return "The Product not found with id: " + id;}
    }

    @Override
    public List<ProductDto> searchProducts(String name, String category, Double minPrice, Double maxPrice) {
        return List.of();
    }

    @Override
    public void updateProductStock(Long id, int newStock) {

    }
}
