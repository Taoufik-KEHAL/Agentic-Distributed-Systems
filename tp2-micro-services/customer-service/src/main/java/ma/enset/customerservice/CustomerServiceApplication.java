package ma.enset.customerservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import ma.enset.customerservice.entities.Customer;
import ma.enset.customerservice.repositories.CustomerRepository;

@SpringBootApplication
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

@Bean
CommandLineRunner start(CustomerRepository customerRepository,
                        RepositoryRestConfiguration restConfiguration) {
    restConfiguration.exposeIdsFor(Customer.class);
    return args -> {
        customerRepository.save(Customer.builder().name("Taoufik").email("taoufik@gmail.com").build());
        customerRepository.save(Customer.builder().name("Sara").email("sara@gmail.com").build());
        customerRepository.save(Customer.builder().name("Yassine").email("yassine@gmail.com").build());
        customerRepository.findAll().forEach(System.out::println);
    };
}


}
