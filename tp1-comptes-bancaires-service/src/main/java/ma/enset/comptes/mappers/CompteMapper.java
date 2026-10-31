package ma.enset.comptes.mappers;

import ma.enset.comptes.dtos.CompteRequestDTO;
import ma.enset.comptes.dtos.CompteResponseDTO;
import ma.enset.comptes.entities.Compte;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class CompteMapper {

    public CompteResponseDTO fromCompte(Compte compte) {
        CompteResponseDTO dto = new CompteResponseDTO();
        BeanUtils.copyProperties(compte, dto);
        return dto;
    }

    public Compte fromCompteRequestDTO(CompteRequestDTO dto) {
        Compte compte = new Compte();
        BeanUtils.copyProperties(dto, compte);
        return compte;
    }
}