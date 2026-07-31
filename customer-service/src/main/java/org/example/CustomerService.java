package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerMapper customerMapper;

    List<CustomerDTO> getClients() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::toDto)
                .toList();
    }

    public Optional<CustomerDTO> getClientById(Long id) {
        return customerRepository.findById(id)
                .map(customerMapper::toDto);
    }

    public CustomerDTO addClient(CustomerDTO addedCustomer) {
        Customer savedCustomer = customerRepository.save(customerMapper.toEntity(addedCustomer));
        return customerMapper.toDto(savedCustomer);
    }
    
    public void deleteById(Long id) {customerRepository.deleteById(id);}
}
