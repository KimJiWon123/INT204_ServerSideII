package sit.int204.classicmodelsservice.controllers;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import sit.int204.classicmodelsservice.dtos.PageDTO;
import sit.int204.classicmodelsservice.dtos.VerySimpleProductDTO;
import sit.int204.classicmodelsservice.entities.Product;
import sit.int204.classicmodelsservice.exceptions.ErrorResponse;
import sit.int204.classicmodelsservice.exceptions.ItemNotFoundException;
import sit.int204.classicmodelsservice.repositories.ProductRepository;
import sit.int204.classicmodelsservice.services.ListMapper;
import sit.int204.classicmodelsservice.services.ProductService;

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

    @GetMapping("/queryByExample")
    public ResponseEntity<Object> findByExample() {
        return ResponseEntity.ok(service.getProductByExample());
    }

    @GetMapping("")
    public ResponseEntity<Object> findAllProducts(
            @RequestParam(defaultValue = "0") Double lower,
            @RequestParam(defaultValue = "0") Double upper,
            @RequestParam(defaultValue = "") String partOfProductName,
            @RequestParam(defaultValue = "") String[] sortBy,
            @RequestParam(defaultValue = "ASC") String[] direction,
            @RequestParam(defaultValue = "0") @Min(0) int pageNo,
            @RequestParam(defaultValue = "10") @Min(10) @Max(20) int pageSize) {
        Page<Product> productPage = service.getProducts(partOfProductName, lower, upper,
                sortBy, direction, pageNo, pageSize);
        PageDTO<VerySimpleProductDTO> pageDto = listMapper.toPageDTO(productPage, VerySimpleProductDTO.class, modelMapper);
        return ResponseEntity.ok(pageDto);
    }

    @GetMapping("/product-line/{id}")
    public ResponseEntity<Object> getProductsByCategory(@PathVariable String id) {
        List<Product> productList = service.getProductsByCategory(id);
        List<VerySimpleProductDTO> vsp = productList.stream().map(p -> modelMapper.
                map(p, VerySimpleProductDTO.class)).collect(Collectors.toList());
        return ResponseEntity.ok(vsp);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable String id) {
        return ResponseEntity.ok(service.getProductById(id));
    }

//    @ExceptionHandler(ItemNotFoundException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public ResponseEntity<ErrorResponse> handleItemNotFound(ItemNotFoundException exception,
//                                                            WebRequest request) {
//        ErrorResponse er = new ErrorResponse(HttpStatus.NOT_FOUND.value(),
//                exception.getMessage(), request.getDescription(false));
//        er.addValidationError("field #1", "Error message #1");
//        er.addValidationError("field #2", "Error message #2");
//        er.addValidationError("field #3", "Error message #3");
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(er);
//    }
}
