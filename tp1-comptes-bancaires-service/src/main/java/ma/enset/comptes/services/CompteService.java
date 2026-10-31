package ma.enset.comptes.services;

import ma.enset.comptes.dtos.CompteRequestDTO;
import ma.enset.comptes.dtos.CompteResponseDTO;

import java.util.List;

public interface CompteService {
    List<CompteResponseDTO> getAll();
    CompteResponseDTO getById(Long id);
    CompteResponseDTO save(CompteRequestDTO request);
    CompteResponseDTO update(Long id, CompteRequestDTO request);
    void delete(Long id);
}