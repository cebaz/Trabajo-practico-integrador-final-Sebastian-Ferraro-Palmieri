package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductMapper productMapper;

    List<ProductDto> getAccounts() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toDto)
                .toList();
    }

    public Optional<ProductDto> getAccountById(Long id) {
        return productRepository.findById(id)
                .map(productMapper::toDto);
    }

    public ProductDto addAccount(ProductDto addedClient) {
        Product savedProduct = productRepository.save(productMapper.toEntity(addedClient));
        return productMapper.toDto(savedProduct);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
