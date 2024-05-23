package sit.int204.classicmodelsservice.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sit.int204.classicmodelsservice.entities.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {
    List<Product> findByPriceBetweenAndProductNameContains(Double lower, Double upoer, String productName);
    List<Product> findByPriceBetweenAndProductNameContains(Double lower, Double upoer, String productName, Sort sort);
    Page<Product> findByPriceBetweenAndProductNameContains(Double lower, Double upoer, String productName, Pageable pageable);
    List<Product> findByProductNameContains(String productName);
    List<Product> findByProductLineStartingWith(String productLine);
    Product findFirstByOrderByPriceDesc();

    @Query("select p from Product p where p.productLine like :category")
    List<Product> getProductByCategory(String category);
}
