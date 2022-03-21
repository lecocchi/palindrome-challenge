package com.walmart.palindrome.repository;

import com.walmart.palindrome.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends MongoRepository<Product, Integer> {
    Product save(Product product);

    Optional<Product> findById(String id);

    List<Product> findByDescriptionRegexOrBrandRegex(String description, String brand);

}
