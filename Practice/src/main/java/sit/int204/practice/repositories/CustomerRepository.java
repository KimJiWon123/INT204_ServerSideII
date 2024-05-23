package sit.int204.practice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sit.int204.practice.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
