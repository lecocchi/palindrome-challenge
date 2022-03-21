package com.walmart.palindrome.unit_test.service;

import com.mongodb.MongoException;
import com.walmart.palindrome.dto.ProductDTO;
import com.walmart.palindrome.exception.ProductException;
import com.walmart.palindrome.exception.ProductNotFoundException;
import com.walmart.palindrome.model.Product;
import com.walmart.palindrome.repository.ProductRepository;
import com.walmart.palindrome.service.ProductService;
import com.walmart.palindrome.utils.StringUtils;
import org.junit.Test;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductServiceTest {

    private ProductRepository productRepositoryMock;

    @Test
    public void testMethodFindByIdIsOK() {

        Product productMock = new Product("1", "ooy eqrceli", "rlñlw brhrka", "www.lider.cl/catalogo/images/whiteLineIcon.svg", 498724);

        productRepositoryMock = new ProductRepository() {
            @Override
            public Product save(Product product) {
                return null;
            }

            @Override
            public Optional<Product> findById(String id) {
                return Optional.of(productMock);
            }

            @Override
            public List<Product> findByDescriptionRegexOrBrandRegex(String description, String brand) {
                return null;
            }

            @Override
            public <S extends Product> List<S> saveAll(Iterable<S> iterable) {
                return null;
            }

            @Override
            public List<Product> findAll() {
                return null;
            }

            @Override
            public List<Product> findAll(Sort sort) {
                return null;
            }

            @Override
            public <S extends Product> S insert(S s) {
                return null;
            }

            @Override
            public <S extends Product> List<S> insert(Iterable<S> iterable) {
                return null;
            }

            @Override
            public <S extends Product> List<S> findAll(Example<S> example) {
                return null;
            }

            @Override
            public <S extends Product> List<S> findAll(Example<S> example, Sort sort) {
                return null;
            }

            @Override
            public Page<Product> findAll(Pageable pageable) {
                return null;
            }

            @Override
            public Optional<Product> findById(Integer integer) {
                return Optional.empty();
            }

            @Override
            public boolean existsById(Integer integer) {
                return false;
            }

            @Override
            public Iterable<Product> findAllById(Iterable<Integer> iterable) {
                return null;
            }

            @Override
            public long count() {
                return 0;
            }

            @Override
            public void deleteById(Integer integer) {

            }

            @Override
            public void delete(Product product) {

            }

            @Override
            public void deleteAllById(Iterable<? extends Integer> iterable) {

            }

            @Override
            public void deleteAll(Iterable<? extends Product> iterable) {

            }

            @Override
            public void deleteAll() {

            }

            @Override
            public <S extends Product> Optional<S> findOne(Example<S> example) {
                return Optional.empty();
            }

            @Override
            public <S extends Product> Page<S> findAll(Example<S> example, Pageable pageable) {
                return null;
            }

            @Override
            public <S extends Product> long count(Example<S> example) {
                return 0;
            }

            @Override
            public <S extends Product> boolean exists(Example<S> example) {
                return false;
            }
        };

        ProductService productService = new ProductService(productRepositoryMock, new StringUtils());

        ProductDTO productFindDTO = productService.findById("1");

        assertEquals(productFindDTO.getId(), "1");
        assertEquals(productFindDTO.getBrand(), "ooy eqrceli");
        assertEquals(productFindDTO.getDescription(), "rlñlw brhrka");
        assertEquals(productFindDTO.getImage(), "www.lider.cl/catalogo/images/whiteLineIcon.svg");
        assertEquals(productFindDTO.getPrice(), 498724);

    }

    @Test(expected = ProductNotFoundException.class)
    public void testMethodFindByIdThrowProductNotFoundException() {
        productRepositoryMock = new ProductRepository() {
            @Override
            public Product save(Product product) {
                return null;
            }

            @Override
            public Optional<Product> findById(String id) {
                return Optional.empty();
            }

            @Override
            public List<Product> findByDescriptionRegexOrBrandRegex(String description, String brand) {
                return null;
            }

            @Override
            public <S extends Product> List<S> saveAll(Iterable<S> iterable) {
                return null;
            }

            @Override
            public List<Product> findAll() {
                return null;
            }

            @Override
            public List<Product> findAll(Sort sort) {
                return null;
            }

            @Override
            public <S extends Product> S insert(S s) {
                return null;
            }

            @Override
            public <S extends Product> List<S> insert(Iterable<S> iterable) {
                return null;
            }

            @Override
            public <S extends Product> List<S> findAll(Example<S> example) {
                return null;
            }

            @Override
            public <S extends Product> List<S> findAll(Example<S> example, Sort sort) {
                return null;
            }

            @Override
            public Page<Product> findAll(Pageable pageable) {
                return null;
            }

            @Override
            public Optional<Product> findById(Integer integer) {
                return Optional.empty();
            }

            @Override
            public boolean existsById(Integer integer) {
                return false;
            }

            @Override
            public Iterable<Product> findAllById(Iterable<Integer> iterable) {
                return null;
            }

            @Override
            public long count() {
                return 0;
            }

            @Override
            public void deleteById(Integer integer) {

            }

            @Override
            public void delete(Product product) {

            }

            @Override
            public void deleteAllById(Iterable<? extends Integer> iterable) {

            }

            @Override
            public void deleteAll(Iterable<? extends Product> iterable) {

            }

            @Override
            public void deleteAll() {

            }

            @Override
            public <S extends Product> Optional<S> findOne(Example<S> example) {
                return Optional.empty();
            }

            @Override
            public <S extends Product> Page<S> findAll(Example<S> example, Pageable pageable) {
                return null;
            }

            @Override
            public <S extends Product> long count(Example<S> example) {
                return 0;
            }

            @Override
            public <S extends Product> boolean exists(Example<S> example) {
                return false;
            }
        };

        ProductService productService = new ProductService(productRepositoryMock, new StringUtils());

        productService.findById("10");

    }

    @Test(expected = ProductException.class)
    public void testMethodFindByIdThrowProductException() {
        productRepositoryMock = new ProductRepository() {
            @Override
            public Product save(Product product) {
                return null;
            }

            @Override
            public Optional<Product> findById(String id) {
                throw new MongoException("");
            }

            @Override
            public List<Product> findByDescriptionRegexOrBrandRegex(String description, String brand) {
                return null;
            }

            @Override
            public <S extends Product> List<S> saveAll(Iterable<S> iterable) {
                return null;
            }

            @Override
            public List<Product> findAll() {
                return null;
            }

            @Override
            public List<Product> findAll(Sort sort) {
                return null;
            }

            @Override
            public <S extends Product> S insert(S s) {
                return null;
            }

            @Override
            public <S extends Product> List<S> insert(Iterable<S> iterable) {
                return null;
            }

            @Override
            public <S extends Product> List<S> findAll(Example<S> example) {
                return null;
            }

            @Override
            public <S extends Product> List<S> findAll(Example<S> example, Sort sort) {
                return null;
            }

            @Override
            public Page<Product> findAll(Pageable pageable) {
                return null;
            }

            @Override
            public Optional<Product> findById(Integer integer) {
                return Optional.empty();
            }

            @Override
            public boolean existsById(Integer integer) {
                return false;
            }

            @Override
            public Iterable<Product> findAllById(Iterable<Integer> iterable) {
                return null;
            }

            @Override
            public long count() {
                return 0;
            }

            @Override
            public void deleteById(Integer integer) {

            }

            @Override
            public void delete(Product product) {

            }

            @Override
            public void deleteAllById(Iterable<? extends Integer> iterable) {

            }

            @Override
            public void deleteAll(Iterable<? extends Product> iterable) {

            }

            @Override
            public void deleteAll() {

            }

            @Override
            public <S extends Product> Optional<S> findOne(Example<S> example) {
                return Optional.empty();
            }

            @Override
            public <S extends Product> Page<S> findAll(Example<S> example, Pageable pageable) {
                return null;
            }

            @Override
            public <S extends Product> long count(Example<S> example) {
                return 0;
            }

            @Override
            public <S extends Product> boolean exists(Example<S> example) {
                return false;
            }
        };

        ProductService productService = new ProductService(productRepositoryMock, new StringUtils());

        productService.findById("10");

    }

    @Test
    public void testMethodfindByCriteriaWhenCriteriaIsNotPalindromeAndIsOk() {
        productRepositoryMock = new ProductRepository() {
            @Override
            public Product save(Product product) {
                return null;
            }

            @Override
            public Optional<Product> findById(String id) {
                return Optional.empty();
            }

            @Override
            public List<Product> findByDescriptionRegexOrBrandRegex(String description, String brand) {
                return Arrays.asList(new Product("1", "ooy eqrceli", "rlñlw brhrka", "www.lider.cl/catalogo/images/whiteLineIcon.svg", 498724));
            }

            @Override
            public <S extends Product> List<S> saveAll(Iterable<S> iterable) {
                return null;
            }

            @Override
            public List<Product> findAll() {
                return null;
            }

            @Override
            public List<Product> findAll(Sort sort) {
                return null;
            }

            @Override
            public <S extends Product> S insert(S s) {
                return null;
            }

            @Override
            public <S extends Product> List<S> insert(Iterable<S> iterable) {
                return null;
            }

            @Override
            public <S extends Product> List<S> findAll(Example<S> example) {
                return null;
            }

            @Override
            public <S extends Product> List<S> findAll(Example<S> example, Sort sort) {
                return null;
            }

            @Override
            public Page<Product> findAll(Pageable pageable) {
                return null;
            }

            @Override
            public Optional<Product> findById(Integer integer) {
                return Optional.empty();
            }

            @Override
            public boolean existsById(Integer integer) {
                return false;
            }

            @Override
            public Iterable<Product> findAllById(Iterable<Integer> iterable) {
                return null;
            }

            @Override
            public long count() {
                return 0;
            }

            @Override
            public void deleteById(Integer integer) {

            }

            @Override
            public void delete(Product product) {

            }

            @Override
            public void deleteAllById(Iterable<? extends Integer> iterable) {

            }

            @Override
            public void deleteAll(Iterable<? extends Product> iterable) {

            }

            @Override
            public void deleteAll() {

            }

            @Override
            public <S extends Product> Optional<S> findOne(Example<S> example) {
                return Optional.empty();
            }

            @Override
            public <S extends Product> Page<S> findAll(Example<S> example, Pageable pageable) {
                return null;
            }

            @Override
            public <S extends Product> long count(Example<S> example) {
                return 0;
            }

            @Override
            public <S extends Product> boolean exists(Example<S> example) {
                return false;
            }
        };

        ProductService productService = new ProductService(productRepositoryMock, new StringUtils());

        List<ProductDTO> productDTOS = productService.findByCriteria("ooy");

        ProductDTO productDTO = productDTOS.get(0);

        assertEquals(productDTOS.size(), 1);
        assertEquals(productDTO.getId(), "1");
        assertEquals(productDTO.getBrand(), "ooy eqrceli");
        assertEquals(productDTO.getDescription(), "rlñlw brhrka");
        assertEquals(productDTO.getImage(), "www.lider.cl/catalogo/images/whiteLineIcon.svg");
        assertEquals(productDTO.getPrice(), 498724);
    }

    @Test
    public void testMethodfindByCriteriaWhenCriteriaIsPalindromeAndIsOk() {
        productRepositoryMock = new ProductRepository() {
            @Override
            public Product save(Product product) {
                return null;
            }

            @Override
            public Optional<Product> findById(String id) {
                return Optional.empty();
            }

            @Override
            public List<Product> findByDescriptionRegexOrBrandRegex(String description, String brand) {
                return Arrays.asList(new Product("1", "ooy eqabali", "rlñlw brhrka", "www.lider.cl/catalogo/images/whiteLineIcon.svg", 498724));
            }

            @Override
            public <S extends Product> List<S> saveAll(Iterable<S> iterable) {
                return null;
            }

            @Override
            public List<Product> findAll() {
                return null;
            }

            @Override
            public List<Product> findAll(Sort sort) {
                return null;
            }

            @Override
            public <S extends Product> S insert(S s) {
                return null;
            }

            @Override
            public <S extends Product> List<S> insert(Iterable<S> iterable) {
                return null;
            }

            @Override
            public <S extends Product> List<S> findAll(Example<S> example) {
                return null;
            }

            @Override
            public <S extends Product> List<S> findAll(Example<S> example, Sort sort) {
                return null;
            }

            @Override
            public Page<Product> findAll(Pageable pageable) {
                return null;
            }

            @Override
            public Optional<Product> findById(Integer integer) {
                return Optional.empty();
            }

            @Override
            public boolean existsById(Integer integer) {
                return false;
            }

            @Override
            public Iterable<Product> findAllById(Iterable<Integer> iterable) {
                return null;
            }

            @Override
            public long count() {
                return 0;
            }

            @Override
            public void deleteById(Integer integer) {

            }

            @Override
            public void delete(Product product) {

            }

            @Override
            public void deleteAllById(Iterable<? extends Integer> iterable) {

            }

            @Override
            public void deleteAll(Iterable<? extends Product> iterable) {

            }

            @Override
            public void deleteAll() {

            }

            @Override
            public <S extends Product> Optional<S> findOne(Example<S> example) {
                return Optional.empty();
            }

            @Override
            public <S extends Product> Page<S> findAll(Example<S> example, Pageable pageable) {
                return null;
            }

            @Override
            public <S extends Product> long count(Example<S> example) {
                return 0;
            }

            @Override
            public <S extends Product> boolean exists(Example<S> example) {
                return false;
            }
        };

        ProductService productService = new ProductService(productRepositoryMock, new StringUtils());

        List<ProductDTO> productDTOS = productService.findByCriteria("aba");

        ProductDTO productDTO = productDTOS.get(0);

        assertEquals(productDTOS.size(), 1);
        assertEquals(productDTO.getId(), "1");
        assertEquals(productDTO.getBrand(), "ooy eqabali");
        assertEquals(productDTO.getDescription(), "rlñlw brhrka");
        assertEquals(productDTO.getImage(), "www.lider.cl/catalogo/images/whiteLineIcon.svg");
        assertEquals(productDTO.getPrice(), 249362);
    }

    @Test(expected = ProductException.class)
    public void testMethodfindByCriteriaThrowProductException() {
        productRepositoryMock = new ProductRepository() {
            @Override
            public Product save(Product product) {
                return null;
            }

            @Override
            public Optional<Product> findById(String id) {
                return Optional.empty();
            }

            @Override
            public List<Product> findByDescriptionRegexOrBrandRegex(String description, String brand) {
                throw new MongoException("");
            }

            @Override
            public <S extends Product> List<S> saveAll(Iterable<S> iterable) {
                return null;
            }

            @Override
            public List<Product> findAll() {
                return null;
            }

            @Override
            public List<Product> findAll(Sort sort) {
                return null;
            }

            @Override
            public <S extends Product> S insert(S s) {
                return null;
            }

            @Override
            public <S extends Product> List<S> insert(Iterable<S> iterable) {
                return null;
            }

            @Override
            public <S extends Product> List<S> findAll(Example<S> example) {
                return null;
            }

            @Override
            public <S extends Product> List<S> findAll(Example<S> example, Sort sort) {
                return null;
            }

            @Override
            public Page<Product> findAll(Pageable pageable) {
                return null;
            }

            @Override
            public Optional<Product> findById(Integer integer) {
                return Optional.empty();
            }

            @Override
            public boolean existsById(Integer integer) {
                return false;
            }

            @Override
            public Iterable<Product> findAllById(Iterable<Integer> iterable) {
                return null;
            }

            @Override
            public long count() {
                return 0;
            }

            @Override
            public void deleteById(Integer integer) {

            }

            @Override
            public void delete(Product product) {

            }

            @Override
            public void deleteAllById(Iterable<? extends Integer> iterable) {

            }

            @Override
            public void deleteAll(Iterable<? extends Product> iterable) {

            }

            @Override
            public void deleteAll() {

            }

            @Override
            public <S extends Product> Optional<S> findOne(Example<S> example) {
                return Optional.empty();
            }

            @Override
            public <S extends Product> Page<S> findAll(Example<S> example, Pageable pageable) {
                return null;
            }

            @Override
            public <S extends Product> long count(Example<S> example) {
                return 0;
            }

            @Override
            public <S extends Product> boolean exists(Example<S> example) {
                return false;
            }
        };

        ProductService productService = new ProductService(productRepositoryMock, new StringUtils());

        productService.findByCriteria("ooy");

    }

}
