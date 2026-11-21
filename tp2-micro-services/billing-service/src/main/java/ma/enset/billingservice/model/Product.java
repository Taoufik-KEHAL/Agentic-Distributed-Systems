package ma.enset.billingservice.model;

import lombok.Data;

@Data
public class Product {
    private Long id;
    private String name;
    private Double price;
    private Integer quantity;
}