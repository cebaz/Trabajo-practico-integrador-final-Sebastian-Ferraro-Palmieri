package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    List<Product> getAccounts(){return productRepository.findAll();};

    public Optional<Product> getAccountById(Long id) {
        return productRepository.findById(id);
    }

    public Product addAccount(Product addedClient) {
        return productRepository.save(addedClient);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}

