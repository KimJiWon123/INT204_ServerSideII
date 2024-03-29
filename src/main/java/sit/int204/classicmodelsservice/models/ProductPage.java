package sit.int204.classicmodelsservice.models;

import lombok.Getter;
import lombok.Setter;
import sit.int204.classicmodelsservice.entities.Product;

import java.util.List;

@Getter
@Setter
public class ProductPage {
    private List<Product> productList;
    private int pageNumber;
    private int pageSize;
    private int totalElements;
    private int totalPages;
}
