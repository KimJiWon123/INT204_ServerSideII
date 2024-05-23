package sit.int204.practice.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sit.int204.practice.DTO.CustomerDTO;
import sit.int204.practice.DTO.CustomersDTO;
import sit.int204.practice.entities.Customer;
import sit.int204.practice.services.CustomerService;
import sit.int204.practice.services.ListMapper;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerService service;
    @Autowired
    private ListMapper listMapper;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("")
    public List<Object> getCustomers() {
        return service.getCustomers().stream()
                .map(customer -> modelMapper.map(customer, CustomerDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getCustomerById(@PathVariable Integer id) {
        Customer customer = service.findByID(id);
        CustomerDTO simpleCustomer = modelMapper.map(customer, CustomerDTO.class);
        return ResponseEntity.ok(simpleCustomer);
    }
}
