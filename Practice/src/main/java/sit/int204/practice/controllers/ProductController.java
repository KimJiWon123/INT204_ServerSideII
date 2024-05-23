package sit.int204.practice.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sit.int204.practice.DTO.PageDTO;
import sit.int204.practice.DTO.ProductDTO;
import sit.int204.practice.entities.Product;
import sit.int204.practice.services.ListMapper;
import sit.int204.practice.services.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService service;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    ListMapper listMapper;

    @GetMapping("")
    public ResponseEntity<Object> findAllProducts(
            @RequestParam(defaultValue = "") String productName,
            @RequestParam(defaultValue = "0") Double lower,
            @RequestParam(defaultValue = "0") Double upper,
            @RequestParam(defaultValue = "") String partOfProductName,
            @RequestParam(defaultValue = "") String[] sortBy,
            @RequestParam(defaultValue = "ASC") String[] direction,
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "0") int pageSize) {

        Page<Product> productPage = service.getProducts(partOfProductName, lower, upper,
                sortBy, direction, pageNo, pageSize);
        PageDTO<ProductDTO> pageDto = listMapper.toPageDTO(productPage, ProductDTO.class, modelMapper);
        return ResponseEntity.ok(pageDto);
    }

    @GetMapping("/product-line/{id}")
    public ResponseEntity<Object> getProductsByCategory(@PathVariable String id) {
        List<Product> productList = service.getProductsByCategory(id);
        List<ProductDTO> vsp = productList.stream().map(p -> modelMapper.
                map(p, ProductDTO.class)).collect(Collectors.toList());
        return ResponseEntity.ok(vsp);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable String id) {
        return ResponseEntity.ok(service.getProductById(id));
    }
//
//    @ExceptionHandler(ItemNotFoundException.class)
//    @ResponseStatus(code = HttpStatus.
//            NOT_FOUND)
//    public ResponseEntity<ErrorResponse> handleItemNotFound(ItemNotFoundException ex, WebRequest request) {
//        ErrorResponse er = new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage(),
//                request.getDescription(false));
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(er);
//    }
}