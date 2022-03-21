package com.walmart.palindrome.service;

import com.walmart.palindrome.dto.ProductDTO;
import com.walmart.palindrome.exception.ProductException;
import com.walmart.palindrome.exception.ProductNotFoundException;
import com.walmart.palindrome.model.Product;
import com.walmart.palindrome.repository.ProductRepository;
import com.walmart.palindrome.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final StringUtils utils;

    @Autowired
    public ProductService(final ProductRepository productRepository, StringUtils utils) {
        this.productRepository = productRepository;
        this.utils = utils;
    }

    public ProductDTO findById(String id) {

        try {
            return productRepository.findById(id)
                    .map(p -> new ProductDTO(
                            p.getId(),
                            p.getBrand(),
                            p.getDescription(),
                            p.getImage(),
                            p.getPrice()))
                    .orElseThrow(() -> new ProductNotFoundException("Product by id " + id + " not found"));
        } catch (ProductNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new ProductException(e.getMessage());
        }
    }

    public List<ProductDTO> findByCriteria(String criteria) {
        try {

            criteria = criteria.toLowerCase().trim();

            List<Product> products = productRepository.findByDescriptionRegexOrBrandRegex(criteria, criteria);

            boolean isCriteriaPalindrome = utils.isPalindrome(criteria);

            return products.stream()
                    .map(p -> new ProductDTO(
                            p.getId(),
                            p.getBrand(),
                            p.getDescription(),
                            p.getImage(),
                            isCriteriaPalindrome ? p.getPrice() / 2 : p.getPrice()
                    )).collect(Collectors.toList());
        } catch (Exception e) {
            throw new ProductException(e.getMessage());
        }
    }
}
