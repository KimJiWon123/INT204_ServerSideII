package sit.int204.practice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "employees")
public class Employee {
    @Id
    private Integer employeeNumber;
    private String firstName;
    private String lastName;
    private String extension;
    private String email;
    private String jobTitle;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "reportsTo")
    private Employee employees;
}
