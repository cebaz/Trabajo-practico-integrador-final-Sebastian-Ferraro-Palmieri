package org.example;

import org.example.exception.CustomerNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import org.example.ProductResponseDTO;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final ProductClient productClient;
    private final CustomerMapper customerMapper;

    public CustomerService(
            CustomerRepository customerRepository,
            ProductClient productClient,
            CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.productClient = productClient;
        this.customerMapper = customerMapper;
    }

    List<CustomerDTO> getClients() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::toDto)
                .toList();
    }

    public CustomerDTO getClientById(Long id) {
        return customerRepository.findById(id)
                .map(customerMapper::toDto)
                .orElseThrow(() -> new CustomerNotFoundException("Cliente no encontrado"));
    }

    public CustomerDTO addClient(CustomerDTO addedCustomer) {
        Customer savedCustomer = customerRepository.save(customerMapper.toEntity(addedCustomer));
        return customerMapper.toDto(savedCustomer);
    }

    public CustomerDTO updateClient(Long id, CustomerDTO updatedCustomer) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Cliente no encontrado"));

        updatedCustomer.setId(id);
        Customer customerToSave = customerMapper.toEntity(updatedCustomer);
        Customer savedCustomer = customerRepository.save(customerToSave);
        return customerMapper.toDto(savedCustomer);
    }

    public List<ProductResponseDTO> getCustomerProducts(Long id) {
        customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Cliente no encontrado"));
        return productClient.getProductsByCustomerId(id);
    }

    public void deleteById(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new CustomerNotFoundException("Cliente no encontrado");
        }
        customerRepository.deleteById(id);
    }
}
