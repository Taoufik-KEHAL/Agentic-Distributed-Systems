package ma.enset.comptes.dtos;

import lombok.*;
import ma.enset.comptes.entities.TypeCompte;

import java.time.LocalDate;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class CompteResponseDTO {
    private Long id;
    private LocalDate dateCreation;
    private Double solde;
    private String devise;
    private TypeCompte type;
}