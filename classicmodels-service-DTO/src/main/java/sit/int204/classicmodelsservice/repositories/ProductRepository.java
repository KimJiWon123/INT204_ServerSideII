package sit.int204.classicmodelsservice.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sit.int204.classicmodelsservice.entities.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {
    List<Product> findByPriceBetweenAndProductNameContains(Double lower, Double upper, String partOfName);
    List<Product> findByPriceBetween(Double lower, Double upper);
    List<Product> findByProductNameContains(String name);
    List<Product> findByProductLineStartingWith(String line);
    @Query("select p from Product p where p.price >= :lowerPrice and p.price < :upperPrice and p.productName like :name")
    List<Product> findByPriceAndName(Double lowerPrice, Double upperPrice, String name);

    List<Product> findByPriceBetweenAndProductNameContains(Double lower, Double upper, String partOfName, Sort sort);
    Product findFirstByOrderByPriceDesc();
    Page<Product> findByPriceBetweenAndProductNameContains(Double lower, Double upper, String partOfName, Pageable pageable);
}
