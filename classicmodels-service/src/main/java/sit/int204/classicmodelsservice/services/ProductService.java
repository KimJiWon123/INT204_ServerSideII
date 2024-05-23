package sit.int204.classicmodelsservice.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import sit.int204.classicmodelsservice.dtos.VerySimpleProductDTO;
import sit.int204.classicmodelsservice.entities.Product;
import sit.int204.classicmodelsservice.exceptions.ItemNotFoundException;
import sit.int204.classicmodelsservice.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.ignoreCase;

@Service
public class ProductService {
    @Autowired
    ProductRepository repository;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    ListMapper listMapper;
    public List<Product> getProductByExample() {
        Product productExample = new Product();
        productExample.setProductLine("Classic Cars");
        productExample.setProductDescription("classic");
        productExample.setProductName("19");
        //productExample.setProductVendor("Gear");
        ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withMatcher("productName", ignoreCase().contains())
                .withMatcher("productDescription", ignoreCase().contains())
                .withMatcher("productVendor", ignoreCase().startsWith());
        List<Product> productList = repository.findAll(Example.of(productExample, matcher));
        return productList;
    }

    public List<Product> getProductsByCategory(String category) {
//        return repository.findByProductLineStartingWith(category);
        return repository.getProductByCategory(category + "%");
    }

    public Page<Product> getProducts(String partOfProduct, Double lower, Double upper,
                                     String[] sortBy, String[] direction, int pageNo, int pageSize) {
        if (lower <= 0 && upper <= 0) {
            upper = repository.findFirstByOrderByPriceDesc().getPrice();
        }
        if (lower > upper) {
            double tmp = lower;
            lower = upper;
            upper = tmp;
        }
//        Sort.Order sortOrder = null;
        if (sortBy != null && sortBy.length > 0) {
            List<Sort.Order> sortOrderList = new ArrayList<>();
            for (int i = 0; i < sortBy.length; i++) {
                sortOrderList.add(new Sort.Order(Sort.Direction.valueOf(direction[i].toUpperCase()), sortBy[i]));
            }
            Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortOrderList));
            return repository.findByPriceBetweenAndProductNameContains(lower, upper, partOfProduct, pageable);
        } else {
            Pageable pageable = PageRequest.of(pageNo, pageSize);
            return repository.findByPriceBetweenAndProductNameContains(lower, upper, partOfProduct, pageable);
        }
    }

    public List<Product> getProducts(String partOfProduct, Double lower, Double upper,
                                     String[] sortBy, String[] direction) {
        if (lower <= 0 && upper <= 0) {
            upper = repository.findFirstByOrderByPriceDesc().getPrice();
        }
        if (lower > upper) {
            double tmp = lower;
            lower = upper;
            upper = tmp;
        }
//        Sort.Order sortOrder = null;
        if (sortBy != null && sortBy.length > 0) {
            List<Sort.Order> sortOrderList = new ArrayList<>();
            for (int i = 0; i < sortBy.length; i++) {
                sortOrderList.add(new Sort.Order(Sort.Direction.valueOf(direction[i].toUpperCase()), sortBy[i]));
            }
            return repository.findByPriceBetweenAndProductNameContains(lower, upper, partOfProduct, Sort.by(sortOrderList));
        } else {
            return repository.findByPriceBetweenAndProductNameContains(lower, upper, partOfProduct);
        }
    }

    public List<Product> getProducts(String partOfProduct, Double lower, Double upper) {
        return getProducts(partOfProduct, lower, upper, null, null);
    }

    public Product getProductById(String productCode) {
        return repository.findById(productCode).orElseThrow(() ->
                new ItemNotFoundException("Product code: " +
                        productCode + " does not exists !!!"));
    }
}
