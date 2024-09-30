package com.example.ecommerce.api.controllers;

import com.example.ecommerce.api.dto.ProductDto;
import com.example.ecommerce.services.IProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(name = "/api")
public class ProductController {

    @Autowired
    private IProductServices iProductServices;

    @PostMapping(name = "/product")
    private ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto dto){
        ProductDto createdProduct = iProductServices.createProduct(dto);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> products = iProductServices.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {
        ProductDto product = iProductServices.getProductById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PutMapping(name = "/product")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto){
        return ResponseEntity.status(HttpStatus.OK).body(iProductServices.updateProduct(productDto));
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        iProductServices.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
