package com.example.ecommerce.repositories.sql;

import com.example.ecommerce.domain.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductSQLRepository extends JpaRepository<Product, Long> {
}
