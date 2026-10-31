package ma.enset.comptes.services;

import ma.enset.comptes.dtos.CompteRequestDTO;
import ma.enset.comptes.dtos.CompteResponseDTO;
import ma.enset.comptes.entities.Compte;
import ma.enset.comptes.exceptions.CompteNotFoundException;
import ma.enset.comptes.mappers.CompteMapper;
import ma.enset.comptes.repositories.CompteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class CompteServiceImpl implements CompteService {

    private final CompteRepository compteRepository;
    private final CompteMapper compteMapper;

    public CompteServiceImpl(CompteRepository compteRepository, CompteMapper compteMapper) {
        this.compteRepository = compteRepository;
        this.compteMapper = compteMapper;
    }

    @Override
    public List<CompteResponseDTO> getAll() {
        return compteRepository.findAll()
                .stream()
                .map(compteMapper::fromCompte)
                .toList();
    }

    @Override
    public CompteResponseDTO getById(Long id) {
        Compte compte = compteRepository.findById(id)
                .orElseThrow(() -> new CompteNotFoundException(id));
        return compteMapper.fromCompte(compte);
    }

    @Override
    public CompteResponseDTO save(CompteRequestDTO request) {
        Compte compte = compteMapper.fromCompteRequestDTO(request);
        compte.setDateCreation(LocalDate.now());
        return compteMapper.fromCompte(compteRepository.save(compte));
    }

    @Override
    public CompteResponseDTO update(Long id, CompteRequestDTO request) {
        Compte existing = compteRepository.findById(id)
                .orElseThrow(() -> new CompteNotFoundException(id));
        if (request.getSolde() != null) existing.setSolde(request.getSolde());
        if (request.getDevise() != null) existing.setDevise(request.getDevise());
        if (request.getType() != null) existing.setType(request.getType());
        return compteMapper.fromCompte(compteRepository.save(existing));
    }

    @Override
    public void delete(Long id) {
        if (!compteRepository.existsById(id)) throw new CompteNotFoundException(id);
        compteRepository.deleteById(id);
    }
}