package sit.int204.classicmodelsservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SimpleEmployeeDTO {
    private String lastName;
    private String firstName;
    private String officeCity;
    public String getName() {
        return firstName + ' ' + lastName;
    }
}