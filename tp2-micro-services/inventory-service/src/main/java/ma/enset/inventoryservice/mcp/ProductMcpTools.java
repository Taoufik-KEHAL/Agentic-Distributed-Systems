package ma.enset.inventoryservice.mcp;

/**
 * @author
 **/

import ma.enset.inventoryservice.entities.Product;
import ma.enset.inventoryservice.repositories.ProductRepository;
import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.ai.mcp.annotation.McpToolParam;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductMcpTools {
    private final ProductRepository productRepository;

    public ProductMcpTools(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @McpTool(name="getAllProducts",
            description ="Renvoie la liste de tous les produits avec leur id, nom, prix unitaire et quantité disponible en stock")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @McpTool(name = "getProductById",
            description = "Renvoie un produit (id, nom, prix unitaire, quantité disponible en stock) à partir de son identifiant")
    public Product getProductById(
            @McpToolParam(description = "Identifiant du produit", required = true) Long id) {
        return productRepository.findById(id).orElse(null);
    }

}
