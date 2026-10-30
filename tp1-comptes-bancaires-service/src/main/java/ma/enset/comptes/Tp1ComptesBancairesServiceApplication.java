package ma.enset.comptes;

import ma.enset.comptes.entities.Compte;
import ma.enset.comptes.entities.TypeCompte;
import ma.enset.comptes.repositories.CompteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class Tp1ComptesBancairesServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(Tp1ComptesBancairesServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(CompteRepository compteRepository) {
        return args -> {
            for (int i = 0; i < 5; i++) {
                Compte compte = Compte.builder()
                        .dateCreation(LocalDate.now())
                        .solde(1000 + Math.random() * 90000)
                        .devise("MAD")
                        .type(Math.random() > 0.5 ? TypeCompte.COURANT : TypeCompte.EPARGNE)
                        .build();
                compteRepository.save(compte);
            }
            compteRepository.findAll().forEach(System.out::println);
        };
    }
}