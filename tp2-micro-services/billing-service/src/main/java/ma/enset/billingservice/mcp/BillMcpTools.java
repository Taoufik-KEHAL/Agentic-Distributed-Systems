package ma.enset.billingservice.mcp;

import ma.enset.billingservice.entities.Bill;
import ma.enset.billingservice.services.BillService;
import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.ai.mcp.annotation.McpToolParam;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author
 **/

@Component
public class BillMcpTools {
    private final BillService billService;

    public BillMcpTools(BillService billService) {
        this.billService = billService;
    }

    @McpTool(name = "getFullBillById",
            description = "Renvoie une facture complète à partir de son identifiant : date de facturation, "
                    + "client (id, nom, email) et lignes de produits (produit, quantité achetée, "
                    + "prix unitaire au moment de la vente)")
    public Bill getFullBillById(
            @McpToolParam(description = "Identifiant de la facture", required = true) Long id) {
        return billService.getFullBill(id);
    }

    @McpTool(name = "getBillsByCustomerId",
            description = "Renvoie toutes les factures complètes d'un client à partir de l'identifiant du client")
    public List<Bill> getBillsByCustomerId(
            @McpToolParam(description = "Identifiant du client", required = true) Long customerId) {
        return billService.getFullBillsByCustomerId(customerId);
    }
}
