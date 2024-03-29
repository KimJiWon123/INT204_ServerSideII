package sit.int204.classicmodelsservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sit.int204.classicmodelsservice.entities.Customera;
import sit.int204.classicmodelsservice.repositories.CustomeraRepository;

import java.util.List;

@Service
public class CustomeraService {
    @Autowired
    CustomeraRepository customeraRepository;
    public List<Customera> insertCustomeras(List<Customera> customeras) {
        return customeraRepository.saveAll(customeras);
    }
    public List<Customera> findAllCustomera() {
        return findAllCustomera(null);
    }
    public List<Customera> findAllCustomera(String name) {
        if (name==null || name.isEmpty()) {
            return customeraRepository.findAll();
        } else {
            return customeraRepository.findByFirstNameContains(name);
        }
    }
}
