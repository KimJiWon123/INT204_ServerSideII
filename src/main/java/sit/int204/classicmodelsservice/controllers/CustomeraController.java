package sit.int204.classicmodelsservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sit.int204.classicmodelsservice.entities.Customera;
import sit.int204.classicmodelsservice.services.CustomeraService;

import java.util.List;

@RestController
@RequestMapping("/customeras")
public class CustomeraController {
    @Autowired
    CustomeraService service;

    @PostMapping("")
    public List<Customera> createCustomers(@RequestBody List<Customera> customeras) {
        return service.insertCustomeras(customeras);
    }
    @GetMapping("")
    public List<Customera> findAllCustomer(@RequestParam(required = false) String filterString) {
        return service.findAllCustomera(filterString);
    }
}
