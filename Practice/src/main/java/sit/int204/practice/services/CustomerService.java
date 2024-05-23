package sit.int204.practice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sit.int204.practice.entities.Customer;
import sit.int204.practice.repositories.CustomerRepository;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository repository;

    public Customer findByID(Integer id) {
        return repository.findById(id).orElseThrow(
                ()->new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Customer number '"+ id + "' does not exist !!!!"));
    }

//    public Page<Customer> getCustomers(int page, int size) {
//        return repository.findAll(PageRequest.of(page, size));
//    }

    public List<Customer> getCustomers() {
        return repository.findAll();
    }
}
