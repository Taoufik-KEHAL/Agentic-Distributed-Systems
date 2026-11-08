package ma.enset.inventoryservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import ma.enset.inventoryservice.entities.Product;
import ma.enset.inventoryservice.repositories.ProductRepository;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

@Bean
CommandLineRunner start(ProductRepository productRepository,
                        RepositoryRestConfiguration restConfiguration) {
    restConfiguration.exposeIdsFor(Product.class);
    return args -> {
        productRepository.save(Product.builder().name("TV").price(12000d).quantity(12).build());
        productRepository.save(Product.builder().name("Smartphone").price(1300d).quantity(4).build());
        productRepository.save(Product.builder().name("CableUsb").price(143d).quantity(22).build());
        productRepository.findAll().forEach(System.out::println);
    };

						}


}
