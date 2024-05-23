package sit.int204.classicmodelsservice.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sit.int204.classicmodelsservice.dtos.NewCustomerDto;
import sit.int204.classicmodelsservice.dtos.SimpleCustomerDTO;
import sit.int204.classicmodelsservice.entities.Customer;
import sit.int204.classicmodelsservice.entities.Employee;
import sit.int204.classicmodelsservice.entities.Order;
import sit.int204.classicmodelsservice.services.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerService service;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/{id}")
    public ResponseEntity<Object> getCustomerById(@PathVariable Integer id) {
        Customer customer = service.findByID(id);
        SimpleCustomerDTO simpleCustomer = modelMapper.map(customer, SimpleCustomerDTO.class);
        return ResponseEntity.ok(simpleCustomer);
    }

    @GetMapping("/{id}/orders")
    public List<Order> getCustomerOrder(@PathVariable Integer id) {
//        System.out.println("id = "+ id);
        return service.findByID(id).getOrderList();
    }

    @GetMapping
    public List<NewCustomerDto> getCustomers() {
        return service.getAllCustomers();
    }

    @PostMapping("")
    public NewCustomerDto createCustomer(@Valid @RequestBody NewCustomerDto newCustomer) {
        return service.createCustomer(newCustomer);
    }
}
