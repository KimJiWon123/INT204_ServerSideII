package sit.int204.classicmodelsservice.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.springframework.format.datetime.DateFormatter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Data
@Table(name = "orders")
public class Order {
    @Id
    private Integer orderNumber;
    private Date orderDate;
    private Date requiredDate;
    private Date shippedDate;
    private String status;
    private String comments;
    private Integer customerNumber;
    static SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
    public String getOrderDate() {
        return sdf.format(orderDate);
    }
}
