package com.walmart.palindrome.controller;

import com.walmart.palindrome.dto.ErrorArrayMessageDTO;
import com.walmart.palindrome.dto.ErrorMessageDTO;
import com.walmart.palindrome.dto.ProductDTO;
import com.walmart.palindrome.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;

@RestController
@RequestMapping(value = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = "Product")
@Validated
public class ProductController {

    private final ProductService productService;

    @Autowired
    ProductController(final ProductService productService) {
        this.productService = productService;
    }

    @ApiOperation(value = "Get Product by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = ProductDTO.class),
            @ApiResponse(code = 400, message = "Bad Request", response = ErrorArrayMessageDTO.class),
            @ApiResponse(code = 404, message = "Product Not Found", response = ErrorMessageDTO.class),
            @ApiResponse(code = 409, message = "Conflict", response = ErrorMessageDTO.class) })
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDTO findById(@PathVariable("id") String id) {
        return productService.findById(id);
    }

    @ApiOperation(value = "Get list of Products by criteria ", tags = "Product")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = ProductDTO.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Bad Request", response = ErrorArrayMessageDTO.class),
            @ApiResponse(code = 409, message = "Conflict", response = ErrorMessageDTO.class) })
    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDTO> findByCriteria(@Valid @Size(min = 3, message = "The field criteria must have a minimum of 3 characters") @RequestParam("criteria") String criteria) {
        return productService.findByCriteria(criteria);
    }
}
