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

    @JsonIgnore
    private SimpleEmployeeDTO sales;
    public String getSalesPerson() {
        return sales==null?"=" : sales.getFullName()+
                " - "+ sales.getOfficeCity() + " | " + "JobTitle: " + sales.getJobTitle();
    }
}
