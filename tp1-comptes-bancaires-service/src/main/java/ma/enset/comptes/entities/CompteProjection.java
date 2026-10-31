package ma.enset.comptes.entities;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "solde", types = Compte.class)
public interface CompteProjection {
    Long getId();
    Double getSolde();
}