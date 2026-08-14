package org.example;

import org.example.DTOs.AccountDTO;
import org.example.DTOs.CreditCardDTO;
import org.example.DTOs.InvestmentDTO;
import org.example.DTOs.LoanDTO;
import org.example.DTOs.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    static final String TYPE_ACCOUNT = "ACCOUNT";
    static final String TYPE_CREDIT_CARD = "CREDIT_CARD";
    static final String TYPE_LOAN = "LOAN";
    static final String TYPE_INVESTMENT = "INVESTMENT";

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductMapper productMapper;


    List<ProductDTO> getAccounts() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toDto)
                .toList();
    }

    public List<ProductDTO> getProductsByCustomerId(Long customerId) {
        return productRepository.findByCustomerId(customerId)
                .stream()
                .map(productMapper::toDto)
                .toList();
    }

    public ProductDTO getAccountById(Long id) {
        return productRepository.findById(id)
                .map(productMapper::toDto)
                .orElseThrow(() -> new org.example.exception.ProductNotFoundException("Producto no encontrado"));
    }

    public ProductDTO addAccount(ProductDTO addedClient) {
        if (addedClient == null) return null;
        return switch (addedClient) {
            case AccountDTO dto -> createAccount(dto);
            case CreditCardDTO dto -> createCreditCard(dto);
            case LoanDTO dto -> createLoan(dto);
            case InvestmentDTO dto -> createInvestment(dto);
            default -> throw new IllegalArgumentException(
                    "Tipo de producto no soportado: " + addedClient.getClass().getName());
        };
    }

    public void deleteById(Long id) {
        if (!productRepository.existsById(id)) {
            throw new org.example.exception.ProductNotFoundException("Producto no encontrado");
        }
        productRepository.deleteById(id);
    }


    public AccountDTO createAccount(AccountDTO dto) {
        Account entity = productMapper.toAccountEntity(dto);
        applyDefaultType(entity, TYPE_ACCOUNT);
        return productMapper.toAccountDto(productRepository.save(entity));
    }

    public CreditCardDTO createCreditCard(CreditCardDTO dto) {
        CreditCard entity = productMapper.toCreditCardEntity(dto);
        applyDefaultType(entity, TYPE_CREDIT_CARD);
        return productMapper.toCreditCardDto(productRepository.save(entity));
    }

    public LoanDTO createLoan(LoanDTO dto) {
        Loan entity = productMapper.toLoanEntity(dto);
        applyDefaultType(entity, TYPE_LOAN);
        return productMapper.toLoanDto(productRepository.save(entity));
    }

    public InvestmentDTO createInvestment(InvestmentDTO dto) {
        Investment entity = productMapper.toInvestmentEntity(dto);
        applyDefaultType(entity, TYPE_INVESTMENT);
        return productMapper.toInvestmentDto(productRepository.save(entity));
    }

    private void applyDefaultType(Product entity, String defaultType) {
        if (entity.getType() == null || entity.getType().isBlank()) {
            entity.setType(defaultType);
        }
    }
}
