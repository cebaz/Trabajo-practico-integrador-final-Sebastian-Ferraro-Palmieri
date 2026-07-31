package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    List<CustomerDTO> getClients() {
        return customerRepository.findAll()
                .stream()
                .map(CustomerDTO::fromEntity)
                .toList();
    }

    public Optional<CustomerDTO> getClientById(Long id) {
        return customerRepository.findById(id)
                .map(CustomerDTO::fromEntity);
    }

    public CustomerDTO addClient(CustomerDTO addedCustomer) {
        Customer savedCustomer = customerRepository.save(addedCustomer.toEntity());
        return CustomerDTO.fromEntity(savedCustomer);
    }
    
    public void deleteById(Long id) {customerRepository.deleteById(id);}
}
