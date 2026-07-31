package org.example;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class CustomerController {

    private final ProductClient productClient;
    private final CustomerRepository customerRepository;

    public CustomerController(ProductClient productClient, CustomerRepository customerRepository) {
        this.productClient = productClient;
        this.customerRepository = customerRepository;
    }

    @GetMapping("/{id}/products")
    public List<Map<String, Object>> getCustomerProducts(@PathVariable Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Cliente no encontrado"));

        return productClient.getAccounts();
    }


    @Autowired
    private CustomerService customerService;

    @GetMapping
    public List<CustomerDTO> getClients() {
        return customerService.getClients();
    }

    @PostMapping("/agregar")
    public CustomerDTO agregar(@RequestBody CustomerDTO addedCustomer) {
        return customerService.addClient(addedCustomer);
    }

    @GetMapping("/{id}")
    public Optional<CustomerDTO> getClientById(@PathVariable Long id) {
        return customerService.getClientById(id);
    }

    @DeleteMapping("/eliminar/{id}")
    public void deleteClientById(@PathVariable Long id) {
        customerService.deleteById(id);
    }
}
