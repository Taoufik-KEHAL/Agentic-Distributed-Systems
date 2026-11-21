package ma.enset.billingservice.entities;


import jakarta.persistence.*;
import lombok.*;
import ma.enset.billingservice.model.Customer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Bill {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate billingDate;
    private Long customerId;
    @Transient
    private Customer customer;

    @OneToMany(mappedBy = "bill", cascade = CascadeType.ALL)
    @Builder.Default
    private List<ProductItem> productItems = new ArrayList<>();
}