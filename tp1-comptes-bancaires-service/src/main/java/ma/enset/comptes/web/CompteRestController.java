package ma.enset.comptes.web;

import ma.enset.comptes.dtos.CompteRequestDTO;
import ma.enset.comptes.dtos.CompteResponseDTO;
import ma.enset.comptes.entities.Compte;
import ma.enset.comptes.mappers.CompteMapper;
import ma.enset.comptes.repositories.CompteRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CompteRestController {

    private final CompteRepository compteRepository;
    private final CompteMapper compteMapper;

    public CompteRestController(CompteRepository compteRepository, CompteMapper compteMapper) {
        this.compteRepository = compteRepository;
        this.compteMapper = compteMapper;
    }

    @GetMapping("/comptes")
    public List<CompteResponseDTO> getAll() {
        return compteRepository.findAll()
                .stream()
                .map(compteMapper::fromCompte)
                .toList();
    }

    @GetMapping("/comptes/{id}")
    public CompteResponseDTO getById(@PathVariable Long id) {
        Compte compte = compteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compte " + id + " introuvable"));
        return compteMapper.fromCompte(compte);
    }

    @PostMapping("/comptes")
    public CompteResponseDTO save(@RequestBody CompteRequestDTO request) {
        Compte compte = compteMapper.fromCompteRequestDTO(request);
        compte.setDateCreation(LocalDate.now());
        return compteMapper.fromCompte(compteRepository.save(compte));
    }

    @PutMapping("/comptes/{id}")
    public CompteResponseDTO update(@PathVariable Long id, @RequestBody CompteRequestDTO request) {
        Compte existing = compteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compte " + id + " introuvable"));
        if (request.getSolde() != null) existing.setSolde(request.getSolde());
        if (request.getDevise() != null) existing.setDevise(request.getDevise());
        if (request.getType() != null) existing.setType(request.getType());
        return compteMapper.fromCompte(compteRepository.save(existing));
    }

    @DeleteMapping("/comptes/{id}")
    public void delete(@PathVariable Long id) {
        compteRepository.deleteById(id);
    }
}