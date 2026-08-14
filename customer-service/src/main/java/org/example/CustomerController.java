package org.example;


import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import org.example.ProductResponseDTO;

@RestController
@RequestMapping({"/customers"})
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{id}/products")
    public List<ProductResponseDTO> getCustomerProducts(@PathVariable Long id) {
        return customerService.getCustomerProducts(id);
    }

    @GetMapping
    public List<CustomerDTO> getClients() {
        return customerService.getClients();
    }

    @PostMapping("/agregar")
    public CustomerDTO agregar(@Valid @RequestBody CustomerDTO addedCustomer) {
        return customerService.addClient(addedCustomer);
    }

    @GetMapping("/{id}")
    public CustomerDTO getClientById(@PathVariable Long id) {
        return customerService.getClientById(id);
    }

    @PutMapping("editar/{id}")
    public CustomerDTO updateClient(@PathVariable Long id, @Valid @RequestBody CustomerDTO updatedCustomer) {
        return customerService.updateClient(id, updatedCustomer);
    }

    @DeleteMapping("/eliminar/{id}")
    public void deleteClientById(@PathVariable Long id) {
        customerService.deleteById(id);
    }
}
