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
import java.util.Optional;

@RestController
@RequestMapping("/accounts")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public List<ProductDto> getAccounts() {
        return productService.getAccounts();
    }

    @PostMapping("/agregar")
    public ProductDto agregar(@RequestBody ProductDto addedProduct) {
        return productService.addAccount(addedProduct);
    }

    @GetMapping("/{id}")
    public Optional<ProductDto> getAccountById(@PathVariable Long id) {
        return productService.getAccountById(id);
    }

    @DeleteMapping("/eliminar/{id}")
    public void deleteAccountById(@PathVariable Long id) {
        productService.deleteById(id);
    }
}

