package ma.enset.comptes.dtos;

import lombok.*;
import ma.enset.comptes.entities.TypeCompte;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class CompteRequestDTO {
    private Double solde;
    private String devise;
    private TypeCompte type;
}