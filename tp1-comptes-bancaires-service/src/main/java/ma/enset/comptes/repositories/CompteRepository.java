package ma.enset.comptes.repositories;

import ma.enset.comptes.entities.Compte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompteRepository extends JpaRepository<Compte, Long> {
}