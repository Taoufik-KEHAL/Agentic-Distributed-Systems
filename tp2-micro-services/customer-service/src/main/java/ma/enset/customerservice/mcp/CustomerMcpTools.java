package ma.enset.customerservice.mcp;

/**
 * @author
 **/


import ma.enset.customerservice.entities.Customer;
import ma.enset.customerservice.repositories.CustomerRepository;
import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.ai.mcp.annotation.McpToolParam;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerMcpTools {

    private final CustomerRepository customerRepository;

    public CustomerMcpTools(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @McpTool(name = "getAllCustomers",
            description = "Renvoie la liste de tous les clients avec leur id, nom et email")
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @McpTool(name = "getCustomerById",
            description = "Renvoie un client à partir de son identifiant")
    public Customer getCustomerById(
            @McpToolParam(description = "Identifiant du client", required = true) Long id) {
        return customerRepository.findById(id).orElse(null);
    }

}