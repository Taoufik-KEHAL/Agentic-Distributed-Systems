package ma.enset.comptes.web;

import ma.enset.comptes.entities.Compte;
import ma.enset.comptes.repositories.CompteRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CompteRestController {

    private final CompteRepository compteRepository;

    public CompteRestController(CompteRepository compteRepository) {
        this.compteRepository = compteRepository;
    }

    @GetMapping("/comptes")
    public List<Compte> getAll() {
        return compteRepository.findAll();
    }

    @GetMapping("/comptes/{id}")
    public Compte getById(@PathVariable Long id) {
        return compteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compte " + id + " introuvable"));
    }

    @PostMapping("/comptes")
    public Compte save(@RequestBody Compte compte) {
        if (compte.getDateCreation() == null) compte.setDateCreation(LocalDate.now());
        return compteRepository.save(compte);
    }

    @PutMapping("/comptes/{id}")
    public Compte update(@PathVariable Long id, @RequestBody Compte compte) {
        Compte existing = compteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compte " + id + " introuvable"));
        if (compte.getSolde() != null) existing.setSolde(compte.getSolde());
        if (compte.getDevise() != null) existing.setDevise(compte.getDevise());
        if (compte.getType() != null) existing.setType(compte.getType());
        return compteRepository.save(existing);
    }

    @DeleteMapping("/comptes/{id}")
    public void delete(@PathVariable Long id) {
        compteRepository.deleteById(id);
    }
}