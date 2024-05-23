package sit.int204.practice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "customers")
public class Customer {
    @Id
    @Column(name = "customerNumber")
    private Integer customerNumber;
    @Column(name = "customerName")
    private String customerName;
    @Column(name = "contactFirstName")
    private String contactFirstName;
    @Column(name = "contactLastName")
    private String contactLastName;
    @Column(name = "phone")
    private String phone;
    @Column(name = "addressLine1")
    private String addressLine1;
    @Column(name = "addressLine2")
    private String addressLine2;
    @Column(name = "city")
    private String city;
    @Column(name = "state")
    private String state;
    @Column(name = "postalCode")
    private String postalCode;
    @Column(name = "country")
    private String country;
    @Column(name = "creditLimit")
    private Double creditLimit;

    // Join to Employee
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "salesRepEmployeeNumber")
    private Employee salesRepEmployee;
}
