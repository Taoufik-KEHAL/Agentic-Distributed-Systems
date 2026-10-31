package ma.enset.comptes.web;

import ma.enset.comptes.dtos.CompteRequestDTO;
import ma.enset.comptes.dtos.CompteResponseDTO;
import ma.enset.comptes.services.CompteService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CompteGraphQLController {

    private final CompteService compteService;

    public CompteGraphQLController(CompteService compteService) {
        this.compteService = compteService;
    }

    @QueryMapping
    public List<CompteResponseDTO> comptes() {
        return compteService.getAll();
    }

    @QueryMapping
    public CompteResponseDTO compteById(@Argument Long id) {
        return compteService.getById(id);
    }

    @MutationMapping
    public CompteResponseDTO saveCompte(@Argument CompteRequestDTO compte) {
        return compteService.save(compte);
    }

    @MutationMapping
    public CompteResponseDTO updateCompte(@Argument Long id, @Argument CompteRequestDTO compte) {
        return compteService.update(id, compte);
    }

    @MutationMapping
    public boolean deleteCompte(@Argument Long id) {
        compteService.delete(id);
        return true;
    }
}