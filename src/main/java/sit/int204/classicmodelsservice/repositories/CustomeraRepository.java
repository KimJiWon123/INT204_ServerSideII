package sit.int204.classicmodelsservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sit.int204.classicmodelsservice.entities.Customera;

import java.util.List;

public interface CustomeraRepository extends JpaRepository<Customera,Long> {
    List<Customera> findByFirstNameContains(String param);
}
