package ma.enset.inventoryservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ma.enset.inventoryservice.entities.Product;

@RepositoryRestResource
public interface ProductRepository extends JpaRepository<Product, Long> {
}