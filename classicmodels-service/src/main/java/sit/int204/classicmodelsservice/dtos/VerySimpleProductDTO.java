package sit.int204.classicmodelsservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VerySimpleProductDTO {
    private String productCode;
    private String productName;
    private Double price;
}
