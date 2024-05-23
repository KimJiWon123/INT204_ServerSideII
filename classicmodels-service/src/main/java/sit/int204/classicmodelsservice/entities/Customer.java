package sit.int204.classicmodelsservice.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "customers")
public class Customer {
    @Id
    private Integer customerNumber;
    private String customerName;
    private String contactLastName;
    private String contactFirstName;
    private String phone;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String postalCode;
    private String country;
//    private Integer salesRepEmployeeNumber;
    @ManyToOne
    @JoinColumn(name = "salesRepEmployeeNumber")
    private Employee salesRepEmployee;

    private Double creditLimit;
    @OneToMany(mappedBy = "customerNumber")
    private List<Order> orderList;

    private String password;
    private String role;
}
