package ma.enset.billingservice.services;

import ma.enset.billingservice.entities.Bill;
import ma.enset.billingservice.feign.CustomerRestClient;
import ma.enset.billingservice.feign.ProductRestClient;
import ma.enset.billingservice.repositories.BillRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author
 **/
@Service
@Transactional(readOnly = true)
public class BillService {

    private final BillRepository billRepository;
    private final CustomerRestClient customerRestClient;
    private final ProductRestClient productRestClient;

    public BillService(BillRepository billRepository, CustomerRestClient customerRestClient, ProductRestClient productRestClient) {
        this.billRepository = billRepository;
        this.customerRestClient = customerRestClient;
        this.productRestClient = productRestClient;
    }

    public Bill getFullBill(Long id){
        Bill bill = billRepository.findById(id).orElseThrow();
        completeBill(bill);
        return bill;
    }

    public List<Bill> getFullBillsByCustomerId(Long customerId  ){
        List<Bill> bills = billRepository.findByCustomerId(customerId);
        bills.forEach(bill -> completeBill(bill));
        return bills;
    }

    private void completeBill(Bill bill){
        bill.setCustomer(customerRestClient.getCustomerById(bill.getCustomerId()));
        bill.getProductItems().forEach(item ->
               item.setProduct(productRestClient.getProductById(item.getProductId())) );
    }
}