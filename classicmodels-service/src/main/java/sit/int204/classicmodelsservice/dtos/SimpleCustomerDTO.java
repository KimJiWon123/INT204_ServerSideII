package sit.int204.classicmodelsservice.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SimpleCustomerDTO {
    private String customerName;
    private String phone;
    private String city;
    private String country;
//    private String salesFirstName;
//    private String salesLastName;
    @JsonIgnore
    private SimpleEmployeeDTO sales;
    public String getSalePerson() {
        return sales==null? "--" : sales.getName() + " - "+ sales.getOfficeCity();
    }
}
