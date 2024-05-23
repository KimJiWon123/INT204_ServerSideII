package sit.int204.practice.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomersDTO {
    private String customerName;
    private String contactFirstName;
    private String contactLastName;
    private String phone;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private Double creditLimit;
}
