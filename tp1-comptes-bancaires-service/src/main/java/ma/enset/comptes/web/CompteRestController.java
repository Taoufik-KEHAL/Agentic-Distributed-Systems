package ma.enset.comptes.web;

import ma.enset.comptes.dtos.CompteRequestDTO;
import ma.enset.comptes.dtos.CompteResponseDTO;
import ma.enset.comptes.services.CompteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CompteRestController {

    private final CompteService compteService;

    public CompteRestController(CompteService compteService) {
        this.compteService = compteService;
    }

    @GetMapping("/comptes")
    public List<CompteResponseDTO> getAll() {
        return compteService.getAll();
    }

    @GetMapping("/comptes/{id}")
    public CompteResponseDTO getById(@PathVariable Long id) {
        return compteService.getById(id);
    }

    @PostMapping("/comptes")
    public CompteResponseDTO save(@RequestBody CompteRequestDTO request) {
        return compteService.save(request);
    }

    @PutMapping("/comptes/{id}")
    public CompteResponseDTO update(@PathVariable Long id, @RequestBody CompteRequestDTO request) {
        return compteService.update(id, request);
    }

    @DeleteMapping("/comptes/{id}")
    public void delete(@PathVariable Long id) {
        compteService.delete(id);
    }
}