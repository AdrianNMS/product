package com.product.product.models.repository;

import com.product.product.models.documents.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product,String> {
}
