package com.walmart.palindrome.integration_test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoException;
import com.walmart.palindrome.Application;
import com.walmart.palindrome.model.Product;
import com.walmart.palindrome.repository.ProductRepository;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.File;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class ProductControllerTest {

    @MockBean
    private ProductRepository productRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void testMethodFindByIdAndReturn200() throws Exception {

        File file = new File(
                "src/test/resources/json/find_by_id_200.json");

        Product productFromJson = mapper.readValue(file, Product.class);

        when(productRepository.findById("1")).thenReturn(Optional.of(productFromJson));

        mockMvc.perform(get("/products/1"))
                .andExpect(jsonPath("$.id", Matchers.is(productFromJson.getId())))
                .andExpect(jsonPath("$.brand", Matchers.is(productFromJson.getBrand())))
                .andExpect(jsonPath("$.description", Matchers.is(productFromJson.getDescription())))
                .andExpect(jsonPath("$.price", Matchers.is(productFromJson.getPrice())))
                .andExpect(status().isOk());
    }

    @Test
    public void testMethodFindByIdAndReturn404() throws Exception {

        when(productRepository.findById("10")).thenReturn(Optional.empty());

        mockMvc.perform(get("/products/10"))
                .andExpect(jsonPath("$.message", Matchers.is("Product by id 10 not found")))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testMethodFindByIdAndReturn409() throws Exception {

        when(productRepository.findById("1")).thenThrow(new MongoException(""));

        mockMvc.perform(get("/products/1"))
                .andExpect(jsonPath("$.message", Matchers.is("It is not possible to process the request")))
                .andExpect(status().isConflict());
    }

    @Test
    public void testMethodFindByCriteriaWhenCriteriaIsNotPalindromeAndReturn200() throws Exception {
        File file = new File(
                "src/test/resources/json/find_by_criteria_when_criteria_is_not_palindrome_200.json");

        List<Product> productsFromJson = mapper.readValue(file, new TypeReference<>() {
        });

        when(productRepository.findByDescriptionRegexOrBrandRegex(anyString(), anyString())).thenReturn(productsFromJson);

        mockMvc.perform(get("/products/search?criteria=eqr"))
                .andExpect(jsonPath("$.length()", Matchers.is(2)))
                .andExpect(jsonPath("$[0].id", Matchers.is(productsFromJson.get(0).getId())))
                .andExpect(jsonPath("$[0].brand", Matchers.is(productsFromJson.get(0).getBrand())))
                .andExpect(jsonPath("$[0].description", Matchers.is(productsFromJson.get(0).getDescription())))
                .andExpect(jsonPath("$[0].price", Matchers.is(productsFromJson.get(0).getPrice())))
                .andExpect(jsonPath("$[1].id", Matchers.is(productsFromJson.get(1).getId())))
                .andExpect(jsonPath("$[1].brand", Matchers.is(productsFromJson.get(1).getBrand())))
                .andExpect(jsonPath("$[1].description", Matchers.is(productsFromJson.get(1).getDescription())))
                .andExpect(jsonPath("$[1].price", Matchers.is(productsFromJson.get(1).getPrice())))
                .andExpect(status().isOk());
    }

    @Test
    public void testMethodFindByCriteriaWhenCriteriaIsPalindromeAndReturn200() throws Exception {
        File file = new File(
                "src/test/resources/json/find_by_criteria_when_criteria_is_palindrome_200.json");

        List<Product> productsFromJson = mapper.readValue(file, new TypeReference<>() {
        });

        when(productRepository.findByDescriptionRegexOrBrandRegex(anyString(), anyString())).thenReturn(productsFromJson);

        mockMvc.perform(get("/products/search?criteria=abba"))
                .andExpect(jsonPath("$.length()", Matchers.is(2)))
                .andExpect(jsonPath("$[0].id", Matchers.is(productsFromJson.get(0).getId())))
                .andExpect(jsonPath("$[0].brand", Matchers.is(productsFromJson.get(0).getBrand())))
                .andExpect(jsonPath("$[0].description", Matchers.is(productsFromJson.get(0).getDescription())))
                .andExpect(jsonPath("$[0].price", Matchers.is(productsFromJson.get(0).getPrice() / 2)))
                .andExpect(jsonPath("$[1].id", Matchers.is(productsFromJson.get(1).getId())))
                .andExpect(jsonPath("$[1].brand", Matchers.is(productsFromJson.get(1).getBrand())))
                .andExpect(jsonPath("$[1].description", Matchers.is(productsFromJson.get(1).getDescription())))
                .andExpect(jsonPath("$[1].price", Matchers.is(productsFromJson.get(1).getPrice() / 2)))
                .andExpect(status().isOk());
    }

    @Test
    public void testMethodFindByCriteriaWhenFieldCriteriaIsNotPresentAndReturn400() throws Exception {

        mockMvc.perform(get("/products/search"))
                .andExpect(jsonPath("$.errors[0]", Matchers.is("The field criteria is required")))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testMethodFindByCriteriaWhenFieldCriteriaIsEmptyAndReturn400() throws Exception {

        mockMvc.perform(get("/products/search?criteria="))
                .andExpect(jsonPath("$.errors[0]", Matchers.is("The field criteria must have a minimum of 3 characters")))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testMethodFindByCriteriaWhenFieldCriteriaHasLess3CharactersAndReturn400() throws Exception {

        mockMvc.perform(get("/products/search?criteria=ab"))
                .andExpect(jsonPath("$.errors[0]", Matchers.is("The field criteria must have a minimum of 3 characters")))
                .andExpect(status().isBadRequest());
    }
}
