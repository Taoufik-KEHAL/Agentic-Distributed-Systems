package ma.enset.billingservice.web;

import ma.enset.billingservice.entities.Bill;
import ma.enset.billingservice.services.BillService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BillRestController {

    private final BillService billService;

    public BillRestController(BillService billService) {
        this.billService = billService;
    }

    @GetMapping("/fullBill/{id}")
    public Bill getFullBill(@PathVariable Long id) {
        return billService.getFullBill(id);
    }
}