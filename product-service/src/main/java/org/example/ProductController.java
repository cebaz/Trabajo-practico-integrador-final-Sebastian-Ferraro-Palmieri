package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public List<ProductDTO> getAccounts() {
        return productService.getAccounts();
    }

    @GetMapping("/products/customer/{customerId}")
    public List<ProductDTO> getProductsByCustomerId(@PathVariable Long customerId) {
        return productService.getProductsByCustomerId(customerId);
    }

    @GetMapping("/products/{id}")
    public Optional<ProductDTO> getAccountById(@PathVariable Long id) {
        return productService.getAccountById(id);
    }

    @DeleteMapping("/products/eliminar/{id}")
    public void deleteAccountById(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @PostMapping("/products/accounts")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountDTO createAccount(@RequestBody AccountDTO dto) {
        return productService.createAccount(dto);
    }

    @PostMapping("/products/credit-cards")
    @ResponseStatus(HttpStatus.CREATED)
    public CreditCardDTO createCreditCard(@RequestBody CreditCardDTO dto) {return productService.createCreditCard(dto);}

    @PostMapping("/products/loans")
    @ResponseStatus(HttpStatus.CREATED)
    public LoanDTO createLoan(@RequestBody LoanDTO dto) {
        return productService.createLoan(dto);
    }

    @PostMapping("/products/investments")
    @ResponseStatus(HttpStatus.CREATED)
    public InvestmentDTO createInvestment(@RequestBody InvestmentDTO dto) {return productService.createInvestment(dto);}
}
