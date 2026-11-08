package ma.enset.inventoryservice.entities;

import jakarta.persistence.*;
import lombok.*;



@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder @ToString

public class Product{
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double price;
    private Integer quantity;



}